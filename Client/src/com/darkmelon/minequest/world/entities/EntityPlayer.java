package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.client.input.MouseButton;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.screens.PlayerInventoryScreen;
import com.darkmelon.minequest.client.screens.PlayingScreen;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.AABB;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.BlockHit;
import com.darkmelon.minequest.world.Inventory;
import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class EntityPlayer extends EntityLiving {

	private float speed = 0.1f;
	private float sensitivity = 1;
	private float camRx = 0;
	
	private int selectedSlot;
	
	private float jumpForce = 0.2f;
	
	private Timer breakingTime;
	private Timer mouseButtonTimer;
	
	private BlockHit breakingBlock;
	
	public EntityPlayer(float x, float y, float z) {
		super(x, y, z, 20);
		
		this.camRx = 0;
		this.mouseButtonTimer = new Timer();
		
		this.selectedSlot = 0;
		setInventory(new Inventory(9 * 5));
		
		getInventory().add(new ItemStack(Item.apple, 5));
		
		this.breakingTime = new Timer();
	}

	
	@Override
	public void onUpdate(World world) {
		
		MineQuest.instance.getSoundSystem().setListenerPosition(x, y, z);
		MineQuest.instance.getSoundSystem().setListenerAngle(ry);
		
		this.vz = 0;
		this.vx = 0;
				
		final Input input = MineQuest.instance.getInput();
		
		if(input.getKeyDown(KeyCode.KEY_E)) {
			if(MineQuest.instance.currentScreen() instanceof PlayerInventoryScreen) {
				MineQuest.instance.showScreen(new PlayingScreen());
			}else {			
				MineQuest.instance.showScreen(new PlayerInventoryScreen());
			}
		}
		
		if(!MineQuest.instance.paused) {
			
			this.ry += input.getMouseDX() * sensitivity;
			this.camRx -= input.getMouseDY() * sensitivity;
			
			camRx = Maths.clamp(camRx, -90, 90);
			
			selectedSlot -= input.getScrollDY();
			
			if(selectedSlot >= 9) {
				selectedSlot -= 9;
			}else if(selectedSlot < 0) {
				selectedSlot += 9;
			}
			
			if(input.getKeyDown(KeyCode.KEY_G)) {
				setHealth(getHealth() - 1);
			}
			
			if(input.getKey(KeyCode.KEY_W)) {
				moveFront(-speed);
			}
			if(input.getKey(KeyCode.KEY_A)) {
				moveRight(-speed);
			}
			if(input.getKey(KeyCode.KEY_S)) {
				moveFront(speed);
			}
			if(input.getKey(KeyCode.KEY_D)) {
				moveRight(speed);
			}
			
			if(input.getKey(KeyCode.KEY_SPACE) && onGround) {
				vy += jumpForce;
			}
			
			vy -= World.GRAVITY_FORCE;
			
			
			
			if(!input.getMouseButton(MouseButton.LEFT)) {
				breakingTime.reset();
				breakingBlock = null;
			}
			
			if(input.getMouseButton(MouseButton.LEFT)) {
				
				BlockHit hit = world.pick((int)x - 8, (int)y - 8, (int)z - 8, (int)x + 8, (int)y + 8, (int)z + 8, this);
				if(hit != null) {
					if(breakingTime.getTimeMilli() >= world.getBlock(hit.x, hit.y, hit.z).getBreakingTime()) {
//						getInventory().add(new ItemStack(world.getBlock(hit.x, hit.y, hit.z), 1));
						world.breakBlock(hit.x, hit.y, hit.z, this);
						breakingBlock = null;
						breakingTime.reset();
					}else {
						if(breakingBlock == null || (breakingBlock.x != hit.x || breakingBlock.y != hit.y || breakingBlock.z != breakingBlock.z)) {
							breakingTime.reset();
						}
						
						breakingBlock = hit;
					}
				}
			}else if(input.getMouseButton(MouseButton.RIGHT)) {
				
				if(mouseButtonTimer.getTimeMilli() >= 100) {
					
					if(getInventory().getItemStack(selectedSlot).getItem() instanceof Block) {
						
						BlockHit hit = world.pick((int)x - 8, (int)y - 8, (int)z - 8, (int)x + 8, (int)y + 8, (int)z + 8, this);
						if(hit != null) {
							AABB blockHitbox = new AABB();
							Block.oakWood.getHitbox(blockHitbox);
							blockHitbox.move(hit.x + Utils.x(hit.face), hit.y + Utils.y(hit.face), hit.z + Utils.z(hit.face));
							
							AABB hitbox = new AABB();
							getHitbox(hitbox);
							hitbox.move(x, y, z);
							
							if(!hitbox.collide(blockHitbox)) {
								world.placeBlock(hit.x + Utils.x(hit.face), hit.y + Utils.y(hit.face), hit.z + Utils.z(hit.face), (Block)getInventory().getItemStack(selectedSlot).getItem(), this);
								getInventory().getItemStack(selectedSlot).setCount(getInventory().getItemStack(selectedSlot).getCount() - 1);
							}
						}
						
					}	
					
					getInventory().getItemStack(selectedSlot).getItem().onUse(getInventory().getItemStack(selectedSlot), this);
					
					mouseButtonTimer.reset();
				}
			}
		}else {
			this.vy = 0;
		}
	}
	
	@Override
	public void onRender(Tessellator t, EntityPlayer player, World world) {
		
		if(breakingBlock != null) {
			
			Block.atlas.bind();
			
			int offset = (int)Maths.clamp((int)(breakingTime.getTimeMilli() / (float)world.getBlock(breakingBlock.x, breakingBlock.y, breakingBlock.z).getBreakingTime() * 4), 0, 4);
			
			t.cube.setAllFaces(offset / 16.0f, 1 - 1 / 16.0f, (offset + 1) / 16.0f, 1);
			t.cube.cube(-0.501f, -0.501f, -0.501f, 0.501f, 0.501f, 0.501f, breakingBlock.x, breakingBlock.y, breakingBlock.z);
			t.render();
			
			Texture.unbind();		
		}
	}
	
	
	public int getSelectedSlot() {
		return selectedSlot;
	}
	
	public float getCamXRotation() {
		return camRx;
	}
	
	@Override
	public void getHitbox(AABB dest) {
		dest.set(-0.25f, -1.5f, -0.25f, 0.25f, 0.25f, 0.25f);
	}
}
