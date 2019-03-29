package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.chunk.Chunk;

public class World {
	public static final int MAX_LOADED_CHUNKS = 32;
	
	private Chunk[] chunks;
	private Generation generation;
	
	public World() {
		chunks = new Chunk[MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS];
		
		generation = new Generation(this);
		
		for(int x = 0; x < MAX_LOADED_CHUNKS; x++) {
			for(int z = 0; z < MAX_LOADED_CHUNKS; z++) {
				chunks[x + z * MAX_LOADED_CHUNKS] = new Chunk(x, z);
				generation.generateChunk(x, z);
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
			return Block.stone;
		}
		return Block.registry.getItemAsBlock(chunk.getBlock(x - chunk.getX() * 16, y, z - chunk.getZ() * 16));
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		Chunk chunk = getChunk(x >> 4, z >> 4);
		if(chunk == null) {
			return;
		}
		chunk.setBlock(x - chunk.getX() * 16, y, z - chunk.getZ() * 16, block);
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
}
