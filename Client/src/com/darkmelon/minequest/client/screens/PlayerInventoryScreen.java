package com.darkmelon.minequest.client.screens;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.client.rendering.guis.GuiSlot;

public class PlayerInventoryScreen extends GuiScreen {

	private static Texture inventoryTexture = Texture.loadAsset("guis/inventory");
	private static Texture hotbarTexture = Texture.loadAsset("guis/hotbar");
	
	private static final int HOTBAR_SIZE = 5;
	private static final int INVENTORY_SIZE = 6;
	
	@Override
	public void initGuis() {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		int wHeight = MineQuest.instance.getWindow().getHeight();
		
		//Add inventory slots
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 4; y++) {
				
				int i = wWidth / 2 - (145 * INVENTORY_SIZE / 2) + (int)(INVENTORY_SIZE * 4);
				int j = wHeight / 2 - (INVENTORY_SIZE * 26);
				
				addGui(new GuiSlot(i + (x * 96), j + (y * 90), 20, MineQuest.instance.getPlayer().getInventory(), x + (1 + y) * 9, mouseItem));
			}
		}
		
		//Add hotbar slots
		for(int x = 0; x < 9; x++) {
		
			int i = wWidth / 2 - ((145 * HOTBAR_SIZE) / 2) + (int)(HOTBAR_SIZE * 2.6);
			int j = 5 + (int)(HOTBAR_SIZE * 1.8);
		
			addGui(new GuiSlot(i + (x * 80), j, 20, MineQuest.instance.getPlayer().getInventory(), x, mouseItem));
		}
		
		MineQuest.instance.getInput().hideCursor(false);
	}

	@Override
	public void onRender(Tessellator t) {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		int wHeight = MineQuest.instance.getWindow().getHeight();
		
		//Draw inventory
		inventoryTexture.bind();
		
		t.rect.rectUV(0, 0, 1, 1);
		t.rect.rect(wWidth / 2 - ((145 * INVENTORY_SIZE) / 2), wHeight / 2 - ((64 * INVENTORY_SIZE) / 2), 0, 145 * INVENTORY_SIZE, 64 * INVENTORY_SIZE);
		t.render();
		
		Texture.unbind();
	
		//Draw hotbar
		hotbarTexture.bind();
		
		t.rect.rectUV(0, 0, 1 - (17 / 162.0f), 1);
		t.rect.rect(wWidth / 2 - (145 * HOTBAR_SIZE / 2), 5, 0, 145 * HOTBAR_SIZE, 16 * HOTBAR_SIZE);
		
		t.render();
		
		Texture.unbind();
	}
}
