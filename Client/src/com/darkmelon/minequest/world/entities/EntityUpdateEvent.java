package com.darkmelon.minequest.world.entities;

import com.darkmelon.minequest.world.World;

public interface EntityUpdateEvent {

	void invoke(World world, Entity entity);
}
