package com.darkmelon.minequest.world.items;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.entities.Entity;

public class Item {

	public static final Item apple = new ItemApple();
	
	public static final ItemRegistry registry = new ItemRegistry();
	
	public static final Texture atlas = Texture.loadAsset("items");
	
	static {
		registry.registerItem((byte)127, apple);
	}
	
	
	private byte id;
	
	public Item() {
	}
	
	public void register(byte id) {
		this.id = id;
	}
	
	public int getTexture(int i) {
		return 0;
	}

	public byte getID() {
		return id;
	}
	
	public void renderDrop(Tessellator t) {

		atlas.bind();
		
		float u, v;
		v = getTexture(0) >> 4;
		u = getTexture(0) - ((int)v << 4);
		
		t.rect.rectUV(u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		t.rect.rect(-0.25f, -0.25f, 0, 0.5f, 0.5f);

		t.render();
		
		Texture.unbind();
	}
	
	public void renderInInventory(Tessellator t, float x, float y, float depth) {
		
		atlas.bind();
		
		float u, v;
		v = getTexture(0) >> 4;
		u = getTexture(0) - ((int)v << 4);
		
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glRotatef(0, 0, 1, 0);
		GL11.glRotatef(0, 0, 0, 1);
		GL11.glTranslatef(x, y, depth);
		GL11.glScalef(60, 60, 60);
		
		t.rect.rectUV(u / 16.0f,  v / 16.0f, (u + 1) / 16.0f, (v + 1) / 16.0f);
		t.rect.rect(0.0f, 0.0f, 0, 1.0f, 1.0f);
		
		t.render();
		
		Texture.unbind();
	}
	
	public void onUse(ItemStack stack, Entity user) {}
	
	@Override
	public boolean equals(Object object) {
		if(object == this) {
			return true;
		}
		if(object instanceof Item) {
			if(((Item) object).getID() == id) {
				return true;
			}
		}
		return false;
	}
}
