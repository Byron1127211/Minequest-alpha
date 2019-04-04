package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.client.input.MouseButton;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.AABB;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.BlockHit;
import com.darkmelon.minequest.world.Inventory;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class Player extends Entity {

	private float speed = 0.1f;
	private float sensitivity = 1;
	private float camRx = 0;
	
	private Inventory inventory;
	private int selectedSlot;
	
	private float jumpForce = 0.33f;
	
	private Timer mouseButtonTimer;
	
	public Player(float x, float y, float z) {
		super(x, y, z);
		
		this.camRx = 0;
		this.mouseButtonTimer = new Timer();
		
		this.selectedSlot = 0;
		this.inventory = new Inventory(9);
		this.inventory.add(Block.grass);
		this.inventory.add(Block.stone);
		this.inventory.add(Block.oakWood);
		this.inventory.add(Block.leaves);
		this.inventory.add(Item.apple);
	}

	@Override
	public void onUpdate(World world) {
		
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
		
		if(input.getMouseButton(MouseButton.LEFT)) {
			if(mouseButtonTimer.getTimeMilli() >= 100) {
				
				BlockHit hit = world.pick((int)x - 8, (int)y - 8, (int)z - 8, (int)x + 8, (int)y + 8, (int)z + 8, this);
				if(hit != null) {
					world.breakBlock(hit.x, hit.y, hit.z, this);
				}
				
				mouseButtonTimer.reset();
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
						}
					}
					
				}else if(inventory.getItemStack(selectedSlot).getItem() instanceof Item) {
					
					inventory.getItemStack(selectedSlot).getItem().onUse(inventory.getItemStack(selectedSlot), this);
				}
				
				mouseButtonTimer.reset();
			}
		}
		
		vy -= World.GRAVITY_FORCE;
	}
	
	public int getSelectedSlot() {
		return selectedSlot;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public void getHitbox(AABB dest) {
		dest.set(-0.25f, -1.5f, -0.25f, 0.25f, 0.4f, 0.25f);
	}
	
	public float getCamXRotation() {
		return camRx;
	}
}
