package com.darkmelon.minequest.world;

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
}
