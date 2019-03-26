package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;

public class Chunk {

	private boolean dirty;
	private byte[] blocks;
	
	public Chunk() {
		blocks = new byte[16 * 256 * 16];
	}
	
	public void update() {

		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 256; y++) {
				for(int z = 0; z < 16; z++) {
					if(getBlock(x, y, z) != Block.air.getID()) {
						Block.registry.get(getBlock(x, y, z)).render(x, y, z);
					}
				}
			}
		}
		
		this.dirty = false;
	}
	
	public byte getBlock(int x, int y, int z) {
		return blocks[x + y * 16 * 16 + z * 16];
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		if(getBlock(x, y, z) != block.getID()) {
			blocks[x + y * 16 * 16 + z * 16] = block.getID();
			this.dirty = true;
		}
	}
	
	public boolean isDirty() {
		return dirty;
	}
}
