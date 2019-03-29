package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Tesselator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.items.Item;

public class Block extends Item {
	
	public static final Block air = new BlockAir();
	public static final Block stone = new BlockStone();
	public static final Block grass = new BlockGrass();
	
	static {
		registry.registerBlockAsItem((byte)-128, air);
		registry.registerBlockAsItem((byte)-127, stone);
		registry.registerBlockAsItem((byte)-126, grass);
	}
	
	private boolean transparent;
	
	public Block() {
		
		this.transparent = false;
	}
	
	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}
	
	public boolean isTransparent() {
		return transparent;
	}
	
	public void render(Tesselator t, World world, int x, int y, int z) {
		
		float v = getTexture(Utils.FRONT) / 16;
		float u = getTexture(Utils.FRONT) - (int)v * 16;
		
		if(world.getBlock(x, y, z + 1).isTransparent())
			t.cube.setFace(Utils.FRONT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		if(world.getBlock(x, y, z - 1).isTransparent())
			t.cube.setFace(Utils.BACK, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		if(world.getBlock(x, y + 1, z).isTransparent())
			t.cube.setFace(Utils.TOP, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		if(world.getBlock(x, y - 1, z).isTransparent())
			t.cube.setFace(Utils.BOTTOM, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		if(world.getBlock(x + 1, y, z).isTransparent())
			t.cube.setFace(Utils.RIGHT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		if(world.getBlock(x - 1, y, z).isTransparent())
			t.cube.setFace(Utils.LEFT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		t.cube.cube(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, x, y, z);
	}
}
