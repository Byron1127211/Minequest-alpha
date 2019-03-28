package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.World;

public class Player extends Entity {

	private float speed = 0.05f;
	private float sensitivity = 1;
	private float camRx = 0;
	
	public Player(float x, float y, float z) {
		super(x, y, z);
		
		this.camRx = 0;
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
	}
	
	public float getCamXRotation() {
		return camRx;
	}
}
