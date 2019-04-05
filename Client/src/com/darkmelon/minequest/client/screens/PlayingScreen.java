package com.darkmelon.minequest.client.screens;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.client.rendering.guis.GuiSlot;

public class PlayingScreen extends GuiScreen {

	private static Texture hotbarTexture = Texture.loadAsset("guis/hotbar");
	
	@Override
	public void initGuis() {

		int wWidth = MineQuest.instance.getWindow().getWidth();
		
		//Add hotbar slots
		for(int x = 0; x < 9; x++) {
			addGui(new GuiSlot(wWidth / 2 - ((144 * 5 / 2) - (40 + x * 80)), 45, 20, 1, 1, MineQuest.instance.getPlayer().getInventory(), x));
		}
	}

	@Override
	public void onRender(Tessellator t) {
		
		int wWidth = MineQuest.instance.getWindow().getWidth();

		//Draw hotbar
		hotbarTexture.bind();
		
		t.rect.rectUV(0, 0, 1 - (17 / 162.0f), 1);
		t.rect.rect(wWidth / 2 - (145.0f * 5 / 2), 5, 0, 145 * 5, 16 * 5);	
		
		t.rect.rectUV(1 - (17 / 162.0f), 0, 1, 1);
		t.rect.rect(wWidth / 2 - (145.0f * 5 / 2) + MineQuest.instance.getPlayer().getSelectedSlot() * 80, 5, 1, 17 * 5, 16 * 5);	
		
		t.render();
		
		Texture.unbind();
	}
}
