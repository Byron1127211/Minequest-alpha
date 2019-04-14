package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.audio.Sound;
import com.darkmelon.minequest.world.Sounds;

public class BlockDirt extends Block {

	@Override
	public int getTexture(int i) {
		return 4; 
	}
	
	public Sound getBlockBreakingSound() {
		return Sounds.dirtBreaking;
	}
}
