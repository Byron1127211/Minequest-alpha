package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.utils.maths.Maths;

public abstract class EntityLiving extends Entity {

	private final int MAX_HEALTH;
	private final int MAX_AIR;
	private int health;
	private int air;
	
	public EntityLiving(float x, float y, float z, int health, int air) {
		super(x, y, z);

		this.MAX_HEALTH = health;
		this.MAX_AIR = air;
		this.air = MAX_AIR;
		this.health = MAX_HEALTH;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return MAX_HEALTH;
	}
	
	public int getAir() {
		return air;
	}
	
	public int getMaxAir() {
		return MAX_AIR;
	}
	
	public void setHealth(int health) {
		this.health = (int)Maths.clamp(health, 0, MAX_HEALTH);
	}
	
	public void setAir(int air) {
		this.air = (int)Maths.clamp(air, 0, MAX_AIR);
	}
}
