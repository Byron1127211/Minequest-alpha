package com.darkmelon.minequest.src;

import static org.lwjgl.opengl.GL11.*;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.src.blocks.Block;

public class Chunk {

	private int x, z;
	private byte blocks[];
	private int list;
	
	public Chunk(int x, int z) {
		blocks = new byte[16 * 256 * 16];
		this.x = x;
		this.z = z;
		this.list = glGenLists(1);
	}
	
	public void update(Tessellator t, World world) {
		
		glNewList(list, GL_COMPILE);
		
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 256; y++) {
				for(int z = 0; z < 16; z++) {
					getBlock(x, y, z).render(t, x, y, z);
				}
			}
		}
		
		t.render();
		
		glEndList();
	}
	
	public Block getBlock(int x, int y, int z) {
		return Block.getBlock(blocks[(x) + (y << 8) + (z << 4)]);
	}

	public void setBlock(int x, int y, int z, Block block) {
		blocks[(x) + (y << 8) + (z << 4)] = block.getID();
	}
	
	public int getX() { 
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getList() {
		return list;
	}
}
