package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.World;

public abstract class Entity {

	public float x, y, z;
	public float rx, ry, rz;
	protected float vx, vy, vz;
	
	public Entity(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = 0;
		this.vy = 0;
		this.vz = 0;
	}
	
	public abstract void onUpdate(World world);
	
	public void moveFront(float amount) {
		
		this.vz += amount * (float)Math.cos(Math.toRadians(ry));
		this.vx -= amount * (float)Math.sin(Math.toRadians(ry));
	}
	
	public void moveRight(float amount) {
		
		this.vx += amount * (float)Math.cos(Math.toRadians(ry));
		this.vz += amount * (float)Math.sin(Math.toRadians(ry));
	}
	
	public final void update(World world) {
		
		onUpdate(world);
		
		rx = Maths.clampRotation(rx);
		ry = Maths.clampRotation(ry);
		rz = Maths.clampRotation(rz);
		
		this.x += vx;
		this.y += vy;
		this.z += vz;
	}
}
