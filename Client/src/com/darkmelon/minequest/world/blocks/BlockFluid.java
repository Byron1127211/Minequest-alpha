package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.Entity;

public class BlockFluid extends Block {

	@Override
	public boolean isBreakable() {
		return false;
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	public void onEntityInFluid(Entity entity) { };
	public void onEntityHeadInFluid(Entity entity) {};
	
	@Override
	public void render(Tessellator t, World world, int x, int y, int z) {
		
		float u, v;
		Block block;
		
		block = world.getBlock(x, y, z + 1);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.FRONT) >> 4) << 4;
			u = getTexture(Utils.FRONT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.FRONT, u, v, u + 16, v + 16);
		}
		
		block = world.getBlock(x, y, z - 1);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.BACK) >> 4) << 4;
			u = getTexture(Utils.BACK) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.BACK, u, v, u + 16, v + 16);
		}
		
		block = world.getBlock(x, y + 1, z);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.TOP) >> 4) << 4;
			u = getTexture(Utils.TOP) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.TOP, u, v, u + 16, v + 16);
		}
		
		block = world.getBlock(x, y - 1, z);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.BOTTOM) >> 4) << 4;
			u = getTexture(Utils.BOTTOM) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.BOTTOM, u, v, u + 16, v + 16);
		}
		
		block = world.getBlock(x + 1, y, z);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.RIGHT) >> 4) << 4;
			u = getTexture(Utils.RIGHT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.RIGHT, u, v, u + 16, v + 16);
		}
		
		block = world.getBlock(x - 1, y, z);
		if(block.getID() != getID() && block.isTransparent()) {
			
			v = (getTexture(Utils.LEFT) >> 4) << 4;
			u = getTexture(Utils.LEFT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.LEFT, u, v, u + 16, v + 16);
		}
		
		t.cube.cube(-0.5f, -0.5f, -0.5f, 0.5f, 0.4f, 0.5f, x, y, z);
	}
}
