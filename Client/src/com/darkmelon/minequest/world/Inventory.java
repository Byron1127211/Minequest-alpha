package com.darkmelon.minequest.world;

import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class Inventory {

	private byte[] items;
	
	public Inventory(int slotCount) {
		
		items = new byte[slotCount];
		for(int i = 0; i < slotCount; i++) {
			items[i] = Block.air.getID();
		}
	}
	
	public Item getItem(int slot) {
		return Item.registry.get(items[slot]);
	}
	
	public boolean add(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == Block.air.getID()) {
				items[i] = item.getID();
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isFull() {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == Block.air.getID()) {
				return false;
			}
		}
		
		return true;
	}
}
