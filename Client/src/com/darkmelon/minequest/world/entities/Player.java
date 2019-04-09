package com.darkmelon.minequest.world.entities;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.client.input.MouseButton;
import com.darkmelon.minequest.client.rendering.Tessellator;
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

public class Player extends Entity {

	private float speed = 0.1f;
	private float sensitivity = 1;
	private float camRx = 0;
	
	private Inventory inventory;
	private int selectedSlot;
	
	private float jumpForce = 0.2f;
	
	private Timer breakingTime;
	private Timer mouseButtonTimer;
	
	private BlockHit breakingBlock;
	
	public Player(float x, float y, float z) {
		super(x, y, z, 20);
		
		this.camRx = 0;
		this.mouseButtonTimer = new Timer();
		
		this.selectedSlot = 0;
		this.inventory = new Inventory(9 * 5);
		this.inventory.add(new ItemStack(Block.grass, 1));
		this.inventory.add(new ItemStack(Block.stone, 1));
		this.inventory.add(new ItemStack(Block.oakWood, 1));
		this.inventory.add(new ItemStack(Block.leaves, 1));
		this.inventory.add(new ItemStack(Item.apple, 5));
		
		for(int i = 9; i < 9 * 5; i++) {
			
			if(i % 2 == 0) {
				
				this.inventory.setItemStack(i, new ItemStack(Block.stone, 1));
			}else {
				this.inventory.setItemStack(i, new ItemStack(Block.grass, 1));
			}
		}
		
		this.breakingTime = new Timer();
	}

	@Override
	public void onUpdate(World world) {
		
		MineQuest.instance.getSoundSystem().setListenerPosition(x, y, z);
		MineQuest.instance.getSoundSystem().setListenerAngle(ry);
		
		this.vz = 0;
		this.vx = 0;
				
		final Input input = MineQuest.instance.getInput();
		
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
					inventory.add(new ItemStack(world.getBlock(hit.x, hit.y, hit.z), 1));
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
				
				if(inventory.getItemStack(selectedSlot).getItem() instanceof Block) {
					
					BlockHit hit = world.pick((int)x - 8, (int)y - 8, (int)z - 8, (int)x + 8, (int)y + 8, (int)z + 8, this);
					if(hit != null) {
						AABB blockHitbox = new AABB();
						Block.oakWood.getHitbox(blockHitbox);
						blockHitbox.move(hit.x + Utils.x(hit.face), hit.y + Utils.y(hit.face), hit.z + Utils.z(hit.face));
						
						AABB hitbox = new AABB();
						getHitbox(hitbox);
						hitbox.move(x, y, z);
						
						if(!hitbox.collide(blockHitbox)) {
							world.placeBlock(hit.x + Utils.x(hit.face), hit.y + Utils.y(hit.face), hit.z + Utils.z(hit.face), (Block)inventory.getItemStack(selectedSlot).getItem(), this);
							inventory.getItemStack(selectedSlot).setCount(inventory.getItemStack(selectedSlot).getCount() - 1);
						}
					}
					
				}	
				
				inventory.getItemStack(selectedSlot).getItem().onUse(inventory.getItemStack(selectedSlot), this);
				
				mouseButtonTimer.reset();
			}
		}
		
	}
	
	@Override
	public void onRender(Tessellator t, World world) {
		
		if(breakingBlock != null) {
			
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glCullFace(GL11.GL_BACK);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
			
			Block.atlas.bind();
			
			GL11.glPushMatrix();
			
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			Maths.perspective(1.5f, (float)MineQuest.instance.getWindow().getWidth() / (float)MineQuest.instance.getWindow().getHeight(), 0.1f, 1000);
			
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glRotatef(getCamXRotation(), 1, 0, 0);
			GL11.glRotatef(ry, 0, 1, 0);
			GL11.glTranslatef(-x, -y, -z);
			
			int offset = (int)Maths.clamp((int)(breakingTime.getTimeMilli() / (float)world.getBlock(breakingBlock.x, breakingBlock.y, breakingBlock.z).getBreakingTime() * 4), 0, 4);
			
			t.cube.setAllFaces(offset / 16.0f, 1 - 1 / 16.0f, (offset + 1) / 16.0f, 1);
			t.cube.cube(-0.501f, -0.501f, -0.501f, 0.501f, 0.501f, 0.501f, breakingBlock.x, breakingBlock.y, breakingBlock.z);
			t.render();
			
			GL11.glPopMatrix();
			
			Texture.unbind();
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_CULL_FACE);
			
			GL11.glDisable(GL11.GL_ALPHA_TEST);
		}
	}
	
	public int getSelectedSlot() {
		return selectedSlot;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public void getHitbox(AABB dest) {
		dest.set(-0.25f, -1.5f, -0.25f, 0.25f, 0.25f, 0.25f);
	}
	
	public float getCamXRotation() {
		return camRx;
	}
}
