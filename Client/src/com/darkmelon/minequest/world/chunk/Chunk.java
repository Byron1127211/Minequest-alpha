package com.darkmelon.minequest.world.chunk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
			blocks[i] = Block.air.getID();
		
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
		
		if(inRange(x, y, z)) {	
			return blocks[x + (y << 8) + (z << 4)];
		}
		
		return Block.stone.getID();
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		if(inRange(x, y, z)) {	
			if(getBlock(x, y, z) != block.getID()) {
				blocks[x + (y << 8) + (z << 4)] = block.getID();
				this.dirty = true;
			}
		}
	}
	
	public static boolean inRange(int x, int y, int z) {
		return x >= 0 && x < 16 && y >= 0 && y < 256 && z >= 0 && z < 16;
	}
	
	public void recreate(int x, int z) {
		
		for(int i = 0; i < 16 * 256 * 16; i++) {
			blocks[i] = Block.air.getID();
		}
		
		this.x = x;
		this.z = z;

		dirty = true;
	}
	
	
	public boolean load() {
		
		File file = new File("saves/world1/chunkData/ch" + x + "." + z + ".dat");
		if(file.exists()) {
			try {
				final DataInputStream in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)));
				in.readFully(this.blocks);
				in.close();
				return true;
			} catch (Exception e) {

				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	public void save() {
		
		File file = new File("saves/world1/chunkData/ch" + x + "." + z + ".dat");
		try {
			file.createNewFile();
			final DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			out.write(blocks);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
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
