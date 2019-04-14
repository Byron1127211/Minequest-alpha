package com.darkmelon.minequest.world.blocks;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.audio.Sound;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.AABB;
import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.Entity;
import com.darkmelon.minequest.world.items.Item;

public class Block extends Item {
	
	public static final Block air = new BlockAir();
	public static final Block stone = new BlockStone();
	public static final Block grass = new BlockGrass();
	public static final Block oakWood = new BlockOakWood();
	public static final Block leaves = new BlockLeaves();
	public static final Block dirt = new BlockDirt();
	
	public static final Texture atlas = Texture.loadAsset("terrain/blocks");
	
	static {
		registry.registerBlockAsItem((byte)-128, air);
		registry.registerBlockAsItem((byte)-127, stone);
		registry.registerBlockAsItem((byte)-126, grass);
		registry.registerBlockAsItem((byte)-125, oakWood);
		registry.registerBlockAsItem((byte)-124, leaves);
		registry.registerBlockAsItem((byte)-123, dirt);
	}
	
	public Block() {
		

	}
	
	public long getBreakingTime() {
		return 300;
	}
	
	public boolean isTransparent() {
		return false;
	}
	
	public void onBreak(World world, int x, int y, int z, Entity breaker) { }
	public void onPlace(World world, int x, int y, int z, Entity placer) { }
	public Sound getBlockBreakingSound() { return null; }
	public ItemStack getDrop() { return new ItemStack(this, 1); }
	
	@Override
	public void renderDrop(Tessellator t) {
		
		atlas.bind();
		
		float u, v;
		
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
		
		t.cube.cube(-0.2f, -0.2f, -0.2f, 0.2f, 0.2f, 0.2f, 0, 0, 0);
		
		t.render();
		
		Texture.unbind();
	}
	
	@Override
	public void renderInInventory(Tessellator t, float x, float y, float depth) {
		
		atlas.bind();
		
		float u, v;
		
		GL11.glTranslatef(x, y + 14, depth);
		
		GL11.glRotatef(30, 1, 0, 0);
		GL11.glRotatef(45, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		
		GL11.glScalef(40, 40, 40);
		
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
		
		t.cube.cube(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0, 0, 0);
		
		t.render();
		
		Texture.unbind();
	}
	
	public void render(Tessellator t, World world, int x, int y, int z) {
		
		float u, v;
		
		if(world.getBlock(x, y, z + 1).isTransparent()) {
			
			v = (getTexture(Utils.FRONT) >> 4) << 4;
			u = getTexture(Utils.FRONT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.FRONT, u, v, u + 16, v + 16);
		}
		
		if(world.getBlock(x, y, z - 1).isTransparent()) {
			
			v = (getTexture(Utils.BACK) >> 4) << 4;
			u = getTexture(Utils.BACK) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.BACK, u, v, u + 16, v + 16);
		}
		
		if(world.getBlock(x, y + 1, z).isTransparent()) {
			
			v = (getTexture(Utils.TOP) >> 4) << 4;
			u = getTexture(Utils.TOP) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.TOP, u, v, u + 16, v + 16);
		}
		
		if(world.getBlock(x, y - 1, z).isTransparent()) {
			
			v = (getTexture(Utils.BOTTOM) >> 4) << 4;
			u = getTexture(Utils.BOTTOM) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.BOTTOM, u, v, u + 16, v + 16);
		}
		
		if(world.getBlock(x + 1, y, z).isTransparent()) {
			
			v = (getTexture(Utils.RIGHT) >> 4) << 4;
			u = getTexture(Utils.RIGHT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.RIGHT, u, v, u + 16, v + 16);
		}
		
		if(world.getBlock(x - 1, y, z).isTransparent()) {
			
			v = (getTexture(Utils.LEFT) >> 4) << 4;
			u = getTexture(Utils.LEFT) << 4 - ((int)v << 8);
			t.cube.setFace(Utils.LEFT, u, v, u + 16, v + 16);
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
