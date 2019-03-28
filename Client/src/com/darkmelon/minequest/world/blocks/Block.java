package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Model;
import com.darkmelon.minequest.world.items.Item;

public class Block extends Item {
	
	public static final BlockRegistry registry = new BlockRegistry();
	 
	public static final Block air = new Block();
	public static final Block stone = new Block();
	
	static {
		registry.register((byte)-128, air);
		registry.register((byte)-127, stone);
	}
	
	public Block() {
		
	}
	
	public void render(Model model, int x, int y, int z) {
		
	}
}
