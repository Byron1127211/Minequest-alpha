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

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.blocks.BlockFluid;

public class Chunk {

	private int lists;
	private byte[] blocks;
	private int x, z;
	private World world;
	
	public Chunk(int x, int z, World world) {
		
		this.world = world;
		lists = GL11.glGenLists(2);
		
		blocks = new byte[16 * 256 * 16];
		
		for(int i = 0; i < 16 * 256 * 16; i++) {
			blocks[i] = Block.air.getID();
		
		}

		this.x = x;
		this.z = z;
		
		world.updateChunk(this);
	}
	
	public void update() {

		Tessellator.INSTANCE.setTextureDimensions(Block.atlas.getWidth(), Block.atlas.getHeight());
		
		GL11.glNewList(lists, GL11.GL_COMPILE);
		
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 256; y++) {
				for(int z = 0; z < 16; z++) {
					Block block = Block.registry.getItemAsBlock(getBlock(x, y, z));
					if(block.getID() != Block.air.getID() && !(block instanceof BlockFluid)) {
						Block.registry.getItemAsBlock(getBlock(x, y, z)).render(Tessellator.INSTANCE, world, x + this.x * 16, y, z + this.z * 16);
					}
				}
			}
		}
		
		Tessellator.INSTANCE.render();
		
		Tessellator.INSTANCE.setTextureDimensions(Block.atlas.getWidth(), Block.atlas.getHeight());
		
		GL11.glEndList();
		
		GL11.glNewList(lists + 1, GL11.GL_COMPILE);
		
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y < 256; y++) {
				for(int z = 0; z < 16; z++) {
					Block block = Block.registry.getItemAsBlock(getBlock(x, y, z));
					if(block instanceof BlockFluid) {
						block.render(Tessellator.INSTANCE, world, x + this.x * 16, y, z + this.z * 16);
					}
				}
			}
		}
		
		Tessellator.INSTANCE.render();
		
		GL11.glEndList();
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
				world.updateChunk(this);
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

		world.updateChunk(this);
	}
	
	
	public boolean load(World world) {
		
		File file = new File("saves/world1/chunkData/" + x + "/ch" + z + ".dat");
		if(file.exists()) {
			try {
				final DataInputStream in = new DataInputStream(new GZIPInputStream(new FileInputStream(file)));
				in.readFully(this.blocks);
				in.close();
				
				Chunk chunk = world.getChunk(x + 1, z);
				if(chunk != null) world.updateChunk(chunk);
				chunk = world.getChunk(x - 1, z);
				if(chunk != null) world.updateChunk(chunk);
				chunk = world.getChunk(x, z + 1);
				if(chunk != null) world.updateChunk(chunk);
				chunk = world.getChunk(x, z - 1);
				if(chunk != null) world.updateChunk(chunk);
				
				return true;
			} catch (Exception e) {

				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	public void save() {
		
		File file = new File("saves/world1/chunkData/" + x + "/ch" + z + ".dat");
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			final DataOutputStream out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			out.write(blocks);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
