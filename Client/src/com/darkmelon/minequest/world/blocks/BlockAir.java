package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.world.World;

public class BlockAir extends Block{

	public BlockAir() {
		
		
	}
	
	@Override
	public boolean isTransparent() {
		return true;
	}
	
	public void render(Tessellator model, World world, int x, int y, int z) {
		
	}
	
	public boolean isSolid() {
		return false;
	}
}
