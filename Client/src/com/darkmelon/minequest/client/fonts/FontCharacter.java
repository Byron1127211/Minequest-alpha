package com.darkmelon.minequest.client.fonts;

public class FontCharacter {

	public int id;
	public int x, y;
	public int width, height;
	public int xOffset, yOffset;
	public int xAdvance;
	
	public FontCharacter(int id, int x, int y, int width, int height, int xOffset, int yOffset, int xAdvance) {
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.xAdvance = xAdvance;
	}
}
