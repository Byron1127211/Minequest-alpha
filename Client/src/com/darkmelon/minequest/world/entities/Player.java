package com.darkmelon.minequest.world.entities;

import org.lwjgl.glfw.GLFW;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.input.KeyCode;
import com.darkmelon.minequest.world.World;

public class Player extends Entity {

	private float speed = 0.05f;
	
	public Player(float x, float y, float z) {
		super(x, y, z);

	}

	@Override
	public void onUpdate(World world) {
		
		this.vz = 0;
		this.vy = 0;
		this.vx = 0;
		
		Input input = MineQuest.instance.getInput();
		
		if(input.getKey(GLFW.GLFW_KEY_W)) {
			vz -= speed;
		}
		if(input.getKey(GLFW.GLFW_KEY_A)) {
			vx -= speed;
		}
		if(input.getKey(GLFW.GLFW_KEY_S)) {
			vz += speed;
		}
		if(input.getKey(GLFW.GLFW_KEY_D)) {
			vx += speed;
		}
	}
}
