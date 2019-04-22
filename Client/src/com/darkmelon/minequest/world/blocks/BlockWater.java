package com.darkmelon.minequest.world.blocks;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.guis.Gui;
import com.darkmelon.minequest.client.rendering.GuiScreenRenderer;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.Window;
import com.darkmelon.minequest.world.entities.Entity;
import com.darkmelon.minequest.world.entities.EntityPlayer;

public class BlockWater extends BlockFluid {

	public static Gui underwaterGUI = new Gui(0, 0, 0) {
		
		@Override
		public void render(Tessellator t) {
			
			Window window = MineQuest.instance.getWindow();
			Block.atlas.bind();
			
			t.setTextureDimensions(Block.atlas);
			t.rect.rectUV(96, 16, 96 + 16, 16 + 16);
			t.rect.rect(0, 0, 0, window.getWidth(), window.getHeight());
			t.render();
			
			Texture.unbind();
		}
	};
	
	@Override
	public int getTexture(int i) {
		return 22;
	}
	
	@Override
	public void onEntityHeadInFluid(Entity entity) {
		
		if(entity instanceof EntityPlayer) {
			GuiScreenRenderer.renderGui(underwaterGUI);
		}
		if(entity.vy < 0) {
			
			entity.vy /= 1.025f;
		}
	}
	
	@Override
	public void onEntityInFluid(Entity entity) {
		if(entity.vy < 0) {
			
			entity.vy /= 1.025f;
		}
	}
}
