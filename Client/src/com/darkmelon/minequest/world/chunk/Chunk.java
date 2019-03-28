package com.darkmelon.minequest.world.chunk;

import com.darkmelon.minequest.client.rendering.Model;
import com.darkmelon.minequest.world.blocks.Block;

public class Chunk {

	private Model model;
	private boolean dirty;
	private byte[] blocks;
	private int x, z;
	
	public Chunk(int x, int z) {
		
		model = new Model(1);
		
		blocks = new byte[16 * 256 * 16];
		for(int i = 0; i < 16 * 256 * 16; i++) {
			blocks[i] = Block.air.getID();
		
		}
		
		dirty = true;
		
		this.x = x;
		this.z = z;
	}
	
	public void update() {

//		for(int x = 0; x < 16; x++) {
//			for(int y = 0; y < 256; y++) {
//				for(int z = 0; z < 16; z++) {
//					if(getBlock(x, y, z) != Block.air.getID()) {
						Block.air.render(model, 0, 0, 0);
//					}
//				}
//			}
//		}
		
		model.create(0);
		
		this.dirty = false;
	}
	
	public byte getBlock(int x, int y, int z) {
		return blocks[x + (y << 8) + (z << 4)];
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		if(getBlock(x, y, z) != block.getID()) {
			blocks[x + (y << 8) + (z << 4)] = block.getID();
			this.dirty = true;
		}
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
	public Model getModel() {
		return model;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
}
