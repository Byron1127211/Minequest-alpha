package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.world.ItemStack;

public class BlockStone extends Block {

	public BlockStone() {
		
	}
	
	@Override
	public int getTexture(int i) {
		return 1;
	}
	
	@Override
	public ItemStack getDrop() {
		return new ItemStack(Block.apple, 5);
	}
}
