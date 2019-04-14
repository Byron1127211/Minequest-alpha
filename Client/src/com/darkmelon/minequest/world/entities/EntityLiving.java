package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.utils.maths.Maths;

public abstract class EntityLiving extends Entity {

	private final int MAX_HEALTH;
	private int health;
	
	public EntityLiving(float x, float y, float z, int health) {
		super(x, y, z);

		this.MAX_HEALTH = health;
		this.health = MAX_HEALTH;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return MAX_HEALTH;
	}
	
	public void setHealth(int health) {
		this.health = (int)Maths.clamp(health, 0, MAX_HEALTH);
	}
}
