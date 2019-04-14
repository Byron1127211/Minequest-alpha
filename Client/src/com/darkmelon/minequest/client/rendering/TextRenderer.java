package com.darkmelon.minequest.client.rendering;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.fonts.Font;
import com.darkmelon.minequest.client.fonts.FontCharacter;

public class TextRenderer {
	
	public static void renderString(Tessellator t, String string, Font font, float x, float y, float depth, float size) {

		font.getAtlas().bind();
		
		int cursor = 0;
		
		for(int i = 0; i < string.length(); i++) {
			int id = string.charAt(i);
			
			FontCharacter chr = font.getCharacter(id);
			if(chr != null) {
				
				t.rect.rectUV((float)chr.x / font.getAtlas().getWidth(), (float)chr.y / font.getAtlas().getHeight(), (float)(chr.x + chr.width) / font.getAtlas().getWidth(), (float)(chr.y + chr.height) / font.getAtlas().getHeight());
				t.rect.rect(x + cursor * size + chr.xOffset * size, y - chr.yOffset * size - chr.height * size, depth, chr.width * size, chr.height * size);
				t.render();
				
				cursor += chr.xAdvance;
			}else {
				cursor += 20;
			}
		}
		
		Texture.unbind();
	}
}
