package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.utils.Utils;

public class BlockOakWood extends Block {

	@Override
	public int getTexture(int i) {
		
		switch(i) {
		case Utils.TOP:
			return 8;
		case Utils.BOTTOM:
			return 8;
		default:
			return 7;
		}
	}
}
