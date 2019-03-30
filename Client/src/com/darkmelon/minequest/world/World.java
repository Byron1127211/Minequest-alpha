package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.chunk.Chunk;
import com.darkmelon.minequest.world.entities.Player;

public class World {
	public static final int MAX_LOADED_CHUNKS = 16;
	
	private Chunk[] chunks;
	private Generation generation;
	
	public World() {
		chunks = new Chunk[MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS];
		
		generation = new Generation(this);
		
		for(int x = 0; x < MAX_LOADED_CHUNKS; x++) {
			for(int z = 0; z < MAX_LOADED_CHUNKS; z++) {
				chunks[x + z * MAX_LOADED_CHUNKS] = new Chunk(x, z);
			}
		}
		
		for(int x = 0; x < MAX_LOADED_CHUNKS; x++) {
			for(int z = 0; z < MAX_LOADED_CHUNKS; z++) {
				generation.generateChunk(x, z);
			}
		}
	}
	
	public void tick(Player player) {
		for(int i = 0; i < MAX_LOADED_CHUNKS * MAX_LOADED_CHUNKS; i++) {
			if(chunks[i].isDirty()) {

				chunks[i].update(this);
				break;
			}
		}
		
		for(Chunk chunk : chunks) {
			
			if(chunk.getZ() * 16 - player.z < -MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.recreate(chunk.getX(), chunk.getZ() + MAX_LOADED_CHUNKS);
				generation.generateChunk(chunk.getX(), chunk.getZ());
			}
			
			if(chunk.getZ() * 16 - player.z > MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.recreate(chunk.getX(), chunk.getZ() - MAX_LOADED_CHUNKS);
				generation.generateChunk(chunk.getX(), chunk.getZ());
			}
			
			if(chunk.getX() * 16 - player.x < -MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.recreate(chunk.getX() + MAX_LOADED_CHUNKS, chunk.getZ());
				generation.generateChunk(chunk.getX(), chunk.getZ());
			}
			
			if(chunk.getX() * 16 - player.x > MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.recreate(chunk.getX() - MAX_LOADED_CHUNKS, chunk.getZ());
				generation.generateChunk(chunk.getX(), chunk.getZ());
			}
		}
	}
	
	public Chunk getChunk(int x, int z) {
		
//		int a = Math.abs((int)((x / MAX_LOADED_CHUNKS) - (x < 0 && x % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS);
//		int b = Math.abs((int)((z / MAX_LOADED_CHUNKS) - (z < 0 && z % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS);

		
		int a = -((x / MAX_LOADED_CHUNKS) - (x < 0 && x % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS;
		int b = -((z / MAX_LOADED_CHUNKS) - (z < 0 && z % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS;
		
		Chunk chunk = chunks[(x + a)
		                   + (z + b) * MAX_LOADED_CHUNKS];
		
		if(chunk.getX() == x && chunk.getZ() == z) {
			return chunk;
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
		Chunk schunk = chunk;
		
		int bx = x - chunk.getX() * 16, bz = z - chunk.getZ() * 16;
		
		if(bz == 0) {
			chunk = getChunk(x >> 4, (z - 1) >> 4);
			if(chunk != null) {

				chunk.setDirty(true);
			}
		}else if(bz == 15) {
			chunk = getChunk(x >> 4, (z + 1) >> 4);
			if(chunk != null) {
				chunk.setDirty(true);
			}
		}
		
		if(bx == 0) {
			chunk = getChunk((x - 1) >> 4, z >> 4);
			if(chunk != null) {
				chunk.setDirty(true);
			}
		}else if(bx == 15) {
			chunk = getChunk((x + 1) >> 4, z >> 4);
			if(chunk != null) {
				chunk.setDirty(true);
			}
		}
		
		schunk.setBlock(bx, y, bz, block);
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
}
