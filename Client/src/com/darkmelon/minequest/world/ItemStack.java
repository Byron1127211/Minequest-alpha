package com.darkmelon.minequest.world;

import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class ItemStack {

	private Item item;
	private int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		setCount(count);
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
		this.count = (int)Maths.clamp(i, 0, 99);
		if(count == 0) {
			item = Block.air;
		}
	}
	
	public void set(ItemStack stack) {
		setItem(stack.item);
		setCount(stack.count);
	}
}
