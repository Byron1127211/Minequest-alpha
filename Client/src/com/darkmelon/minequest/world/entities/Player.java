package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.client.input.MouseButton;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.BlockHit;
import com.darkmelon.minequest.world.World;

public class Player extends Entity {

	private float speed = 0.2f;
	private float sensitivity = 1;
	private float camRx = 0;
	
	private Timer mouseButtonTimer;
	
	public Player(float x, float y, float z) {
		super(x, y, z);
		
		this.camRx = 0;
		this.mouseButtonTimer = new Timer();
	}

	@Override
	public void onUpdate(World world) {
		
		this.vz = 0;
		this.vy = 0;
		this.vx = 0;
		
		final Input input = MineQuest.instance.getInput();
		
		this.ry += input.getMouseDX() * sensitivity;
		this.camRx += input.getMouseDY() * sensitivity;
		
		camRx = Maths.clamp(camRx, -90, 90);
		
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
		
		if(input.getKey(KeyCode.KEY_SPACE)) {
			vy += speed;
		}
		if(input.getKey(KeyCode.KEY_LEFT_SHIFT)) {
			vy -= speed;
		}
		
		if(input.getMouseButton(MouseButton.LEFT)) {
			if(mouseButtonTimer.getTimeMilli() >= 100) {
				
				BlockHit hit = world.pick((int)x - 8, (int)y - 8, (int)z - 8, (int)x + 8, (int)y + 8, (int)z + 8, this);
				if(hit != null) {
					world.breakBlock(hit.x, hit.y, hit.z, this);
				}
				
				mouseButtonTimer.reset();
			}
		}
	}
	
	public float getCamXRotation() {
		return camRx;
	}
}
