package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.utils.Utils;

public class BlockGrass extends Block {

	public BlockGrass() {
	
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
}
