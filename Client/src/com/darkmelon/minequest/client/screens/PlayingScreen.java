package com.darkmelon.minequest.client.screens;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.ChunkRenderer;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

public class PlayingScreen extends GuiScreen {

	@Override
	public void initGuis() {

		
	}

	@Override
	public void onRender(Tessellator t) {
		
		drawHotbar(t);
	}
	
	public void drawHotbar(Tessellator t) {
		
		int wWidth = MineQuest.instance.getWindow().getWidth();
		
		//Draw items in hotbar
		ChunkRenderer.atlas.bind();
		
		for(int i = 0; i < 9; i++) {
			
			Item item = MineQuest.instance.getPlayer().getHotbar().getItem(i);
			if(item.getID() != Block.air.getID()) {
				GL11.glPushMatrix();
				item.renderInInventory(t, wWidth / 2.0f - ((144 * 5 / 2.0f) - (40 + i * 80)), 45, 2);
				t.render();
				GL11.glPopMatrix();
			}
		}
		
		
		//Draw hotbar
		Texture hotbarTexture = Texture.loadAsset("guis/hotbar");
		hotbarTexture.bind();
		
		t.rect.rectUV(0, 0, 1 - (17 / 162.0f), 1);
		t.rect.rect(wWidth / 2.0f - (145.0f * 5 / 2.0f), 5, 0, 145 * 5, 16 * 5);	
		
		t.rect.rectUV(1 - (17 / 162.0f), 0, 1, 1);
		t.rect.rect(wWidth / 2.0f - (145.0f * 5 / 2.0f) + MineQuest.instance.getPlayer().getSelectedSlot() * 80, 5, 1, 17 * 5, 16 * 5);	
		
		t.render();
		
		Texture.unbind();
	}
}
