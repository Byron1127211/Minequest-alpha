package com.darkmelon.minequest.src.items;

public class Item {
	private static Item[] items = new Item[256];
	
	private byte id;
	
	public Item() {

	}
	
	public byte getID() {
		return id;
	}
	
	public Item getItem(byte id) {
		return items[id];
	}
	
	public static void register(Item item, int id) {
		item.id = (byte)(id - 128);
		items[item.id + 128] = item;
	}
}
