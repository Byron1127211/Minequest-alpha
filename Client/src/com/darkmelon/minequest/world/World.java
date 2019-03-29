package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.chunk.Chunk;

public class World {
	public static final int MAX_LOADED_CHUNKS = 16;
	
	private Chunk[] chunks;
	
	public World() {
		chunks = new Chunk[MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS];
		for(int x = 0; x < MAX_LOADED_CHUNKS; x++) {
			for(int z = 0; z < MAX_LOADED_CHUNKS; z++) {
				chunks[x + z * MAX_LOADED_CHUNKS] = new Chunk(x, z);
			}
		}
	}
	
	public void tick() {
		for(int i = 0; i < MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS; i++) {
			if(chunks[i].isDirty()) {
				chunks[i].update(this);
				break;
			}
		}
	}
	
	public Chunk getChunk(int x, int z) {
		if((x >= 0 && x < MAX_LOADED_CHUNKS) && (z >= 0 && z < MAX_LOADED_CHUNKS)) {
			
			return chunks[x + z * MAX_LOADED_CHUNKS];
		}
		return null;
	}
	
	public Block getBlock(int x, int y, int z) {
		Chunk chunk = getChunk(x >> 4, z >> 4);
		if(chunk == null) {
			return Block.air;
		}
		return Block.registry.getItemAsBlock(chunk.getBlock(x - chunk.getX() * 16, y, z - chunk.getZ() * 16));
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		getChunk(x >> 4, z >> 4).setBlock(x, y, z, block);
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
}
