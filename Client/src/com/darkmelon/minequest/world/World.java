package com.darkmelon.minequest.world;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.rendering.Tesselator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.chunk.Chunk;
import com.darkmelon.minequest.world.entities.Entity;
import com.darkmelon.minequest.world.entities.Player;

public class World {
	public static final int MAX_LOADED_CHUNKS = 16;
	
	private Chunk[] chunks;
	
	private Generation generation;
	
	private IntBuffer selectionBuffer = BufferUtils.createIntBuffer(10000);
	private IntBuffer viewportBuffer = BufferUtils.createIntBuffer(16);
	
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
				if(!chunks[x + z * MAX_LOADED_CHUNKS].load()) {
					generation.generateChunk(x, z);
					chunks[x + z * MAX_LOADED_CHUNKS].save();
				}
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
				chunk.save();
				chunk.recreate(chunk.getX(), chunk.getZ() + MAX_LOADED_CHUNKS);
				if(!chunk.load()) {
					generation.generateChunk(chunk.getX(), chunk.getZ());
//					chunk.save();
				}
			}
			
			if(chunk.getZ() * 16 - player.z > MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.save();
				chunk.recreate(chunk.getX(), chunk.getZ() - MAX_LOADED_CHUNKS);
				if(!chunk.load()) {
					generation.generateChunk(chunk.getX(), chunk.getZ());
//					chunk.save();
				}
			}
			
			if(chunk.getX() * 16 - player.x < -MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.save();
				chunk.recreate(chunk.getX() + MAX_LOADED_CHUNKS, chunk.getZ());
				if(!chunk.load()) {
					generation.generateChunk(chunk.getX(), chunk.getZ());
//					chunk.save();
				}
			}
			
			if(chunk.getX() * 16 - player.x > MAX_LOADED_CHUNKS * 16 / 2) {
				chunk.save();
				chunk.recreate(chunk.getX() - MAX_LOADED_CHUNKS, chunk.getZ());
				if(!chunk.load()) {
					generation.generateChunk(chunk.getX(), chunk.getZ());
//					chunk.save();
				}
			}
		}
	}
	
	public void save() {
		for(Chunk chunk : chunks) {
			chunk.save();
		}
	}
	
	public Chunk getChunk(int x, int z) {
		
		int a = -((x / MAX_LOADED_CHUNKS) - (x < 0 && x % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS;
		int b = -((z / MAX_LOADED_CHUNKS) - (z < 0 && z % MAX_LOADED_CHUNKS != 0 ? 1 : 0)) * MAX_LOADED_CHUNKS;
		
		Chunk chunk = chunks[(x + a) + (z + b) * MAX_LOADED_CHUNKS];
		
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
		Chunk schunk = getChunk(x >> 4, z >> 4);
		if(schunk == null) {
			return;
		}
		Chunk chunk;
		
		int bx = x - schunk.getX() * 16, bz = z - schunk.getZ() * 16;
		
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
	
	public void breakBlock(int x, int y, int z, Entity breaker) {
		getBlock(x, y, z).onBreak(this, x, y, z, breaker);
		setBlock(x, y, z, Block.air);
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
	
	public BlockHit pick(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Player player) {
		
		GL11.glSelectBuffer(selectionBuffer);	
		GL11.glGetIntegerv(GL11.GL_VIEWPORT, viewportBuffer);
		
		float mouseX = MineQuest.instance.getWindow().getWidth() / 2.0f;
		float mouseY = MineQuest.instance.getWindow().getHeight() / 2.0f;
		
		GL11.glRenderMode(GL11.GL_SELECT);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		GL11.glPushMatrix();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPickMatrix(mouseX, mouseY, 1, 1, viewportBuffer);
		Maths.perspective(1.5f, (float)MineQuest.instance.getWindow().getWidth() / (float)MineQuest.instance.getWindow().getHeight(), 0.1f, 1000);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glRotatef(player.getCamXRotation(), 1, 0, 0);
		GL11.glRotatef(player.ry, 0, 1, 0);
		GL11.glTranslatef(-player.x, -player.y, -player.z);
		
		GL11.glInitNames();
		
		for(int x = minX; x <= maxX; x++) {
			GL11.glPushName(x);
			for(int y = minY; y <= maxY; y++) {
				GL11.glPushName(y);
				for(int z = minZ; z <= maxZ; z++) {
					GL11.glPushName(z);
					if(!getBlock(x, y, z).equals(Block.air)) {
						
						for(int f = 0; f < 6; f++) {
							
							GL11.glPushName(f);
							
							if(getBlock(x + (f == Utils.RIGHT ? 1 : (f == Utils.LEFT ? -1 : 0)), y + (f == Utils.TOP ? 1 : (f == Utils.BOTTOM ? -1 : 0)), z + (f == Utils.FRONT ? 1 : (f == Utils.BACK ? -1 : 0))) == Block.air) {
								
								Tesselator.INSTANCE.cube.setFace(f, 0, 0, 0, 0);
								Tesselator.INSTANCE.cube.cube(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, x, y, z);
								
								Tesselator.INSTANCE.render();
							}
							
							GL11.glPopName();
						}
					}
					GL11.glPopName();
				}
				GL11.glPopName();
			}
			GL11.glPopName();
		}

		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);

		int hits = GL11.glRenderMode(GL11.GL_RENDER);
		int x, y, z;
		int face = 0;
		
		if(hits == 0) {
			return null;
		}

		int near = 2000000000;
		int index = 3;
		
		for(int i = 0; i < hits; i++) {
			
			if(selectionBuffer.get(i * 7 + 2) < near) {
				
				near = selectionBuffer.get(i * 7 + 2);
				index = i * 7 + 3;
			}
		}
		
		x = selectionBuffer.get(index);
		y = selectionBuffer.get(index + 1);
		z = selectionBuffer.get(index + 2);
		face = selectionBuffer.get(index + 3);
		
		selectionBuffer.clear();
		viewportBuffer.clear();
		
		return new BlockHit(face, x, y, z);
	}
}
