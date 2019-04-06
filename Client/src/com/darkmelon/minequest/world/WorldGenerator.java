package com.darkmelon.minequest.world;

import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.blocks.Block;

public class WorldGenerator {

	private World world;
	
	public WorldGenerator(World world) {
		this.world = world;
	}
	
	public void generateChunk(int cx, int cz) {
	
		for(int x = cx * 16; x < cx * 16 + 16; x++) {
			
			for(int z = cz * 16; z < cz * 16 + 16; z++) {
				
				int y = (int)(Maths.noise(x, z, 0.03f, 8)) + (int)(Maths.noise(x, z, 0.08f, 4)) + 60;
				y = (int)Maths.clamp(y, 0, 255);

				world.setBlock(x, y, z, Block.grass);
				if(Maths.random.nextInt(100) == 0) {
					
					generateTree(x, y, z);
				}
				
				for(int j = y - 1; j >= 0; j--) {
//					if(y - j < 5) {
//						world.setBlock(x, j, z, Block.dirt);
//					}else {
						world.setBlock(x, j, z, Block.stone);
//					}
				}
			}
		}
	}
	
	public void generateTree(int x, int y, int z) {
		
		for(int lx = -2; lx <= 2; lx++) {
			
			for(int ly = 0; ly <= 1; ly++) {
				
				for(int lz = -2; lz <= 2; lz++) {
					
					world.setBlock(x + lx, y + 4 + ly, z + lz, Block.leaves);
				}
			}
		}
		
		for(int lx = -1; lx <= 1; lx++) {
			
			for(int lz = -1; lz <= 1; lz++) {
				
				world.setBlock(x + lx, y + 6, z + lz, Block.leaves);
			}
		}
		
		world.setBlock(x, y + 7, z, Block.leaves);
		
		world.setBlock(x, y + 1, z, Block.oakWood);
		world.setBlock(x, y + 2, z, Block.oakWood);
		world.setBlock(x, y + 3, z, Block.oakWood);
		world.setBlock(x, y + 4, z, Block.oakWood);
		world.setBlock(x, y + 5, z, Block.oakWood);
		
	}
}
