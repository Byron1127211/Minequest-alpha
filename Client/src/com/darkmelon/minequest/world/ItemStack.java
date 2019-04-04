package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.items.Item;

public class ItemStack {

	private Item item;
	private byte count;
	
	public ItemStack(Item item, byte count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public byte getCount() {
		return count;
	}
	
	public void setCount(byte count) {
		this.count = count;
	}
}
