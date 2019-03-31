package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Tesselator;
import com.darkmelon.minequest.world.World;

public class BlockAir extends Block{

	public BlockAir() {
		
		
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
	
	public void render(Tesselator model, World world, int x, int y, int z) {
		
	}
	
	public boolean isSolid() {
		return false;
	}
}
