package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Model;
import com.darkmelon.minequest.utils.Utils;
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
		
		
		int texture = getTexture(Utils.FRONT);
		model.cube.setFace(Utils.FRONT, texture, 0, 1, 1);
		model.cube.setFace(Utils.BACK, 0, 0, 1, 1);
		model.cube.setFace(Utils.TOP, 0, 0, 1, 1);
		model.cube.setFace(Utils.BOTTOM, 0, 0, 1, 1);
		model.cube.setFace(Utils.RIGHT, 0, 0, 1, 1);
		model.cube.setFace(Utils.LEFT, 0, 0, 1, 1);
		
		model.cube.cube(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, x, y, z);
		
//		//Front
//		model.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
//		model.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
//		model.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
//		model.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
//		
//		//Back
//		model.vertex(x - 0.5f, y - 0.5f, z - 0.5f);	
//		model.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
//		
//		//Top
//		model.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
//		model.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
//		
//		//Bottom
//		model.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
//		model.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
//		
//		//Right
//		model.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
//		model.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
//		
//		//Left
//		model.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
//		model.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
//		model.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
//		model.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
	}
}
