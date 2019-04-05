package com.darkmelon.minequest.client.screens;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.client.rendering.guis.GuiSlot;

public class PlayingScreen extends GuiScreen {

	private static Texture hotbarTexture = Texture.loadAsset("guis/hotbar");
	private static final int SIZE = 5;
	
	@Override
	public void initGuis() {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		
		//Add hotbar slots
		for(int x = 0; x < 9; x++) {
		
			int i = wWidth / 2 - ((145 * SIZE) / 2) + (int)(SIZE * 2.6);
			int j = 5 + (int)(SIZE * 1.8);
		
			addGui(new GuiSlot(i + (x * 80), j, 20, MineQuest.instance.getPlayer().getInventory(), x, mouseItem));
		}
		
		MineQuest.instance.getInput().hideCursor(true);
		MineQuest.instance.paused = false;
	}

	@Override
	public void onRender(Tessellator t) {
		
		int wWidth = MineQuest.instance.getWindow().getWidth();

		//Draw hotbar
		hotbarTexture.bind();
		
		t.rect.rectUV(0, 0, 1 - (17 / 162.0f), 1);
		t.rect.rect(wWidth / 2 - (145 * SIZE / 2), 5, 0, 145 * SIZE, 16 * SIZE);	
		
		t.rect.rectUV(1 - (17 / 162.0f), 0, 1, 1);
		t.rect.rect(wWidth / 2 - (145.0f * 5 / 2) + MineQuest.instance.getPlayer().getSelectedSlot() * 80, 5, 1, 17 * 5, 16 * 5);	
		
		t.render();
		
		Texture.unbind();
	}
}
