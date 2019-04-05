package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;

public class Inventory {

	private ItemStack[] items;
	
	public Inventory(int slotCount) {
		
		items = new ItemStack[slotCount];
		for(int i = 0; i < slotCount; i++) {
			items[i] = new ItemStack(Block.air, (byte)0);
		}
	}
	
	public ItemStack getItemStack(int slot) {
		return items[slot];
	}
	
	public void setItemStack(int slot, ItemStack stack) {
		if(slot < items.length) {
			items[slot].setItem(stack.getItem());
			items[slot].setCount(stack.getCount());
		}
	}

	
	public boolean add(ItemStack stack) {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getItem().getID() == Block.air.getID()) {
				items[i].setItem(stack.getItem());
				items[i].setCount(stack.getCount());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isFull() {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getItem().getID() == Block.air.getID()) {
				return false;
			}
		}
		
		return true;
	}
}
