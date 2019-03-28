package com.darkmelon.minequest.world.items;

import com.darkmelon.minequest.world.Registry;
import com.darkmelon.minequest.world.blocks.Block;

public class ItemRegistry extends Registry<Byte, Item> {
	
	public void registerBlockAsItem(byte id, Block block) {
		register(id, block);
		block.register(id);
	}
	
	public Block getItemAsBlock(byte id) {
		if(get(id) instanceof Block) {
			return (Block)get(id);
		}
		
		return null;
	}
}
