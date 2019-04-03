package com.darkmelon.minequest.world.items;

import com.darkmelon.minequest.client.rendering.Tessellator;

public class Item {

	public static final ItemRegistry registry = new ItemRegistry();
	
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
	
	public void renderInInventory(Tessellator t, float x, float y, float depth) {
		
		t.render();
	}
	
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
