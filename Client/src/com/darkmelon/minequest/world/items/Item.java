package com.darkmelon.minequest.world.items;

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
}
