package com.darkmelon.minequest.client.rendering.guis;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;

public class GuiButton extends Gui {
	
	public GuiButton(int x, int y, int depth, int width, int height, Texture texture) {
		super(x, y, depth, width, height);

	}
	
	@Override
	public void render(Tessellator t) { 
		
		t.rect.rect(x, y, depth, width, height);
	}
	
	public void onHover() {}
	
	public void onClick() {}
}
