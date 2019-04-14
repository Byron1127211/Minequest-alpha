package com.darkmelon.minequest.src;

public class World {
	private static final int MAX_LOADED_CHUNKS = 16;
	
	private Chunk[] chunks;
	
	public World() {
		chunks = new Chunk[MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS];
		for(int x = 0; x < MAX_LOADED_CHUNKS; x++) {
			for(int z = 0; z < MAX_LOADED_CHUNKS; z++) {
				chunks[x + z * MAX_LOADED_CHUNKS] = new Chunk(x, z);
			}
		}
	}
	
	public Chunk getChunk(int x, int z) {
		return chunks[x + z * MAX_LOADED_CHUNKS];
	}
}
