package com.darkmelon.minequest.client.rendering.guis;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;

public class Gui {

	public Texture texture;
	public int x, y, width, height;
	public int depth;
	
	public Gui(int x, int y, int depth, int width, int height, Texture texture) {
		
		this.x = x;
		this.y = y;
		this.depth = depth;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}
	
	public void render(Tessellator t) {
		
		t.rect.rect(x, y, 0, width, height);
	}
}
