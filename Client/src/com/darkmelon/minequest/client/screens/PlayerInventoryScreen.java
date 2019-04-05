package com.darkmelon.minequest.client.screens;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.client.rendering.guis.GuiSlot;
import com.darkmelon.minequest.world.blocks.Block;

public class PlayerInventoryScreen extends GuiScreen {

	private static Texture inventoryTexture = Texture.loadAsset("guis/inventory");
	private static Texture hotbarTexture = Texture.loadAsset("guis/hotbar");
	
	@Override
	public void initGuis() {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		int wHeight = MineQuest.instance.getWindow().getHeight();
		
		//Add inventory slots
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 4; y++) {
				addGui(new GuiSlot(wWidth / 2 - (145 * 6 / 2) + (50 + x * 96), wHeight / 2 - ((64 * 6) / 2) + (65 + y * 91), 20, 1, 1, MineQuest.instance.getPlayer().getInventory(), x + ((1 + y) * 9)));
			}
		}
		
		//Add hotbar slots
		for(int x = 0; x < 9; x++) {
			addGui(new GuiSlot(wWidth / 2 - ((144 * 5 / 2) - (40 + x * 80)), 45, 20, 1, 1, MineQuest.instance.getPlayer().getInventory(), x));
		}
		
		mouseItem.setItem(Block.apple);
	}

	@Override
	public void onRender(Tessellator t) {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		int wHeight = MineQuest.instance.getWindow().getHeight();
		
		//Draw inventory
		inventoryTexture.bind();
		
		t.rect.rectUV(0, 0, 1, 1);
		t.rect.rect(wWidth / 2 - ((145 * 6) / 2), wHeight / 2 - ((64 * 6) / 2), 0, 145 * 6, 64 * 6);
		t.render();
		
		Texture.unbind();
	
		//Draw hotbar
		hotbarTexture.bind();
		
		t.rect.rectUV(0, 0, 1 - (17 / 162.0f), 1);
		t.rect.rect(wWidth / 2.0f - (145.0f * 5 / 2.0f), 5, 0, 145 * 5, 16 * 5);	
		
		t.render();
		
		Texture.unbind();
	}
}
