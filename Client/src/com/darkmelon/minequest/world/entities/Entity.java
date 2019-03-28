package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.world.World;

public abstract class Entity {

	public float x, y, z;
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
	
	public final void update(World world) {
		
		onUpdate(world);
		
		this.x += vx;
		this.y += vy;
		this.z += vz;
	}
}
