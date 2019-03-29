package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.utils.Utils;

public class BlockGrass extends Block {

	public BlockGrass() {
	
	}
	
	public int getTexture(int i) {
		
		switch (i) {
		case Utils.TOP:
			return 3;
		case Utils.BOTTOM:
			return 4;
		default:
			return 2;
		}
	}
}
