package com.darkmelon.minequest.world.chunk;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.rendering.Tesselator;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.blocks.Block;

public class Chunk {

	private int lists;
	private boolean dirty;
	private byte[] blocks;
	private int x, z;
	
	public Chunk(int x, int z) {
		
		lists = GL11.glGenLists(1);
		
		blocks = new byte[16 * 256 * 16];
		for(int i = 0; i < 16 * 256 * 16; i++) {
			blocks[i] = Block.stone.getID();
		
		}
		
		dirty = true;
		
		this.x = x;
		this.z = z;
	}
	
	public void update(World world) {

		GL11.glNewList(lists, GL11.GL_COMPILE);
		
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 256; y++) {
				for(int z = 0; z < 16; z++) {
					if(getBlock(x, y, z) != Block.air.getID()) {
						Block.registry.getItemAsBlock(getBlock(x, y, z)).render(Tesselator.INSTANCE, world, x + this.x * 16, y, z + this.z * 16);
					}
				}
			}
		}
		
		Tesselator.INSTANCE.render();
		
		GL11.glEndList();
		
		this.dirty = false;
	}
	
	public byte getBlock(int x, int y, int z) {
		
		if(x >= 0 && x < 16 && y >= 0 && y < 256 && z >= 0 && z < 16) {
			return blocks[x + (y << 8) + (z << 4)];
		}
		
		return Block.air.getID();
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

	public int getList(int index) {
		return lists + index;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
}
