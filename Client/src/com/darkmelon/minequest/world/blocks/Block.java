package com.darkmelon.minequest.world.blocks;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.AABB;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.Entity;
import com.darkmelon.minequest.world.items.Item;

public class Block extends Item {
	
	public static final Block air = new BlockAir();
	public static final Block stone = new BlockStone();
	public static final Block grass = new BlockGrass();
	public static final Block oakWood = new BlockOakWood();
	public static final Block leaves = new BlockLeaves();
	
	public static final Texture atlas = Texture.loadAsset("terrain/blocks");
	
	static {
		registry.registerBlockAsItem((byte)-128, air);
		registry.registerBlockAsItem((byte)-127, stone);
		registry.registerBlockAsItem((byte)-126, grass);
		registry.registerBlockAsItem((byte)-125, oakWood);
		registry.registerBlockAsItem((byte)-124, leaves);
	}
	
	public Block() {
		

	}
	
	public boolean isTransparent() {
		return false;
	}
	
	public void onBreak(World world, int x, int y, int z, Entity breaker) { }
	public void onPlace(World world, int x, int y, int z, Entity placer) { }
	
	@Override
	public void renderInInventory(Tessellator t, float x, float y, float depth) {
		
		float u, v;
		
		GL11.glTranslatef(x, y, depth);
		
		GL11.glRotatef(30, 1, 0, 0);
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glScalef(20, 20, 20);
		
		v = getTexture(Utils.FRONT) >> 4;
		u = getTexture(Utils.FRONT) - ((int)v << 4);
		t.cube.setFace(Utils.FRONT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		v = getTexture(Utils.BACK) >> 4;
		u = getTexture(Utils.BACK) - ((int)v << 4);
		t.cube.setFace(Utils.BACK, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		v = getTexture(Utils.TOP) >> 4;
		u = getTexture(Utils.TOP) - ((int)v << 4);
		t.cube.setFace(Utils.TOP, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		v = getTexture(Utils.BOTTOM) >> 4;
		u = getTexture(Utils.BOTTOM) - ((int)v << 4);
		t.cube.setFace(Utils.BOTTOM, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		v = getTexture(Utils.RIGHT) >> 4;
		u = getTexture(Utils.RIGHT) - ((int)v << 4);
		t.cube.setFace(Utils.RIGHT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		v = getTexture(Utils.LEFT) >> 4;
		u = getTexture(Utils.LEFT) - ((int)v << 4);
		t.cube.setFace(Utils.LEFT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		
		t.cube.cube(-1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 0, 0, 0);
	}
	
	public void render(Tessellator t, World world, int x, int y, int z) {
		
		float u, v;
		
		if(world.getBlock(x, y, z + 1).isTransparent()) {
			
			v = getTexture(Utils.FRONT) >> 4;
			u = getTexture(Utils.FRONT) - ((int)v << 4);
			t.cube.setFace(Utils.FRONT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		
		if(world.getBlock(x, y, z - 1).isTransparent()) {
			
			v = getTexture(Utils.BACK) >> 4;
			u = getTexture(Utils.BACK) - ((int)v << 4);
			t.cube.setFace(Utils.BACK, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		
		if(world.getBlock(x, y + 1, z).isTransparent()) {
			
			v = getTexture(Utils.TOP) >> 4;
			u = getTexture(Utils.TOP) - ((int)v << 4);
			t.cube.setFace(Utils.TOP, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		if(world.getBlock(x, y - 1, z).isTransparent()) {
			
			v = getTexture(Utils.BOTTOM) >> 4;
			u = getTexture(Utils.BOTTOM) - ((int)v << 4);
			t.cube.setFace(Utils.BOTTOM, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		if(world.getBlock(x + 1, y, z).isTransparent()) {
			v = getTexture(Utils.RIGHT) >> 4;
			u = getTexture(Utils.RIGHT) - ((int)v << 4);
			t.cube.setFace(Utils.RIGHT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		if(world.getBlock(x - 1, y, z).isTransparent()) {
			
			v = getTexture(Utils.LEFT) >> 4;
			u = getTexture(Utils.LEFT) - ((int)v << 4);
			t.cube.setFace(Utils.LEFT, u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		}
		
		t.cube.cube(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, x, y, z);
	}
	
	public void getHitbox(AABB dest) {
		dest.set(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f);
	}
	
	public boolean isSolid() {
		return true;
	}
}
