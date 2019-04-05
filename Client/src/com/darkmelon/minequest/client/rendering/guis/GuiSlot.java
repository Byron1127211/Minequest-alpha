package com.darkmelon.minequest.client.rendering.guis;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.world.Inventory;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class GuiSlot extends GuiButton {

	private Inventory inventory;
	private int slotIndex;
	
	public GuiSlot(int x, int y, int depth, int width, int height, Inventory inventory, int slotIndex) {
		super(x, y, depth, width, height);

		this.inventory = inventory;
		this.slotIndex = slotIndex;
	}

	@Override
	public void onClick() {

		
	}

	@Override
	public void render(Tessellator t) {

		Item item = inventory.getItemStack(slotIndex).getItem();
		if(item.getID() != Block.air.getID()) {
			if(item instanceof Block) {
				Block.atlas.bind();
			}else if(item instanceof Item) {
				Item.atlas.bind();
			}
			GL11.glPushMatrix();
			item.renderInInventory(t, x, y, 20);
			t.render();
			GL11.glPopMatrix();
		}
	}
}
