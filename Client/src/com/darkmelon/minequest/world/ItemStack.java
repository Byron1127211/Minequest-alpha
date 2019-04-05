package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.items.Item;

public class ItemStack {

	private Item item;
	private int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int i) {
		this.count = i;
	}
	
	public void set(ItemStack stack) {
		setItem(stack.item);
		setCount(stack.count);
	}
}
