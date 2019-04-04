package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

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
	
	public boolean add(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i].getItem().getID() == Block.air.getID()) {
				items[i].setItem(item);
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
