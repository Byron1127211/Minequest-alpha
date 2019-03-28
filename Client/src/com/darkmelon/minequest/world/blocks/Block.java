package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Model;
import com.darkmelon.minequest.world.items.Item;

public class Block extends Item {
	
	public static final Block air = new BlockAir();
	public static final Block stone = new BlockStone();
	
	static {
		registry.registerBlockAsItem((byte)-128, air);
		registry.registerBlockAsItem((byte)-127, stone);
	}
	
	public Block() {
		
	}
	
	public void render(Model model, int x, int y, int z) {
		
		model.uv(1, 1);
		model.vertex(0.5f, -0.5f, -1.5f);
		model.uv(1, 0);
		model.vertex(0.5f, 0.5f, -1.5f);
		model.uv(0, 0);
		model.vertex(-0.5f, 0.5f, -1.5f);
		model.uv(0, 1);
		model.vertex(-0.5f, -0.5f, -1.5f);
	}
}
