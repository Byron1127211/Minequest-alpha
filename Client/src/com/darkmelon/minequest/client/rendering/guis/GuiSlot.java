package com.darkmelon.minequest.client.rendering.guis;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.fonts.Font;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.TextRenderer;
import com.darkmelon.minequest.world.Inventory;
import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class GuiSlot extends GuiButton {

	private Inventory inventory;
	private ItemStack mouseItem;
	private int slotIndex;
	
	public GuiSlot(int x, int y, int depth, Inventory inventory, int slotIndex, ItemStack mouseItem) {
		super(x, y, depth, 57, 63);

		this.inventory = inventory;
		this.slotIndex = slotIndex;
		this.mouseItem = mouseItem;
	}

	@Override
	public void onClick() {
		
		if(mouseItem != null) {		
			ItemStack stack = new ItemStack(null, 0);
			stack.set(inventory.getItemStack(slotIndex));
			inventory.setItemStack(slotIndex, mouseItem);
			mouseItem.set(stack);
		}
	}

	@Override
	public void render(Tessellator t) {

		Item item = inventory.getItemStack(slotIndex).getItem();
		int count = inventory.getItemStack(slotIndex).getCount();
		if(item.getID() != Block.air.getID() && count != 0) {
			GL11.glPushMatrix();
			item.renderInInventory(t, x, y, 20);
			GL11.glPopMatrix();
			
			if(count != 1) {
				if(count >= 0 && count < 10) {
					TextRenderer.renderString(t, Integer.toString(inventory.getItemStack(slotIndex).getCount()), Font.minecraftia, x + 50, y + 25, 30, 0.6f);
				}else {
					TextRenderer.renderString(t, Integer.toString(inventory.getItemStack(slotIndex).getCount()), Font.minecraftia, x + 35, y + 25, 30, 0.6f);
				}
			}
		}
	}
}
