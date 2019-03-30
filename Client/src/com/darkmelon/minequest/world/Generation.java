package com.darkmelon.minequest.world;

import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.blocks.Block;

public class Generation {

	private World world;
	
	public Generation(World world) {
		this.world = world;
	}
	
	public void generateChunk(int cx, int cz) {
	
		for(int x = cx * 16; x < cx * 16 + 16; x++) {
			
			for(int z = cz * 16; z < cz * 16 + 16; z++) {
				
				int y = (int)(Maths.noise(x, z, 0.03f, 8)) + (int)(Maths.noise(x, z, 0.08f, 4)) + 60;
				y = (int)Maths.clamp(y, 0, 255);

				world.setBlock(x, y, z, Block.grass);
				for(int j = y - 1; j >= 0; j--) {
					world.setBlock(x, j, z, Block.stone);
				}
			}
		}
	}
}
