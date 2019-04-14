package com.darkmelon.minequest.src.blocks;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.src.items.Item;

public class Block extends Item {

	private static final Block air = new BlockAir();
	private static final Block stone = new BlockStone();
	
	public Block() {
		
		Item.register(air, 0);
		Item.register(stone, 1);
	}
	
	public void render(Tessellator t, int x, int y, int z) {
		
	}
	
	public Block getBlock(byte id) {
		return (Block)getItem(id);
	}
}
