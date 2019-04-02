package com.darkmelon.minequest.client.rendering.guis;

import com.darkmelon.minequest.client.rendering.Tessellator;

public abstract class Gui {

	public int x, y, width, height;
	public int depth;
	
	public Gui(int x, int y, int depth, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.depth = depth;
		this.width = width;
		this.height = height;
	}
	
	public void onUpdate() {}
	
	public abstract void render(Tessellator t);
}
