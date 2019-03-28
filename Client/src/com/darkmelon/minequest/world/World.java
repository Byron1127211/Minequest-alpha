package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.chunk.Chunk;

public class World {
	private final int MAX_LOADED_CHUNKS = 16;
	
	private Chunk[] chunks;
	
	public World() {
		chunks = new Chunk[MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS];
		for(int i = 0; i < MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS; i++) {
			chunks[i] = new Chunk();
		}
	}
	
	public void tick() {
		for(int i = 0; i < MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS; i++) {
			if(chunks[i].isDirty()) {

				chunks[i].update();
			}
		}
	}
	
	public Chunk getChunk(int x, int z) {
		return chunks[x + z * MAX_LOADED_CHUNKS];
	}
	
	public Block getBlock(int x, int y, int z, Block block) {
		return Block.registry.getItemAsBlock(getChunk(x >> 4, z >> 4).getBlock(x, y, z));
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		getChunk(x >> 4, z >> 4).setBlock(x, y, z, block);
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
}
