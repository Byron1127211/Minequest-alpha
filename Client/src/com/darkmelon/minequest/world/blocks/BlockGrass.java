package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.audio.Sound;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.world.Sounds;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.Entity;

public class BlockGrass extends Block {

	public BlockGrass() {
	
	}
	
	@Override
	public void onBreak(World world, int x, int y, int z, Entity breaker) {
		

	}
	
	public int getTexture(int i) {

		switch (i) {
		case Utils.TOP:
			return 2;
		case Utils.BOTTOM:
			return 4;
		default:
			return 3;
		}
	}
	
	public Sound getBlockBreakingSound() {
		return Sounds.dirtBreaking;
	}
}
