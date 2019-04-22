package com.darkmelon.minequest.client.fonts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.utils.Debug;

public class Font {

	public static final Font minecraftia = Font.loadFont("minecraftia");
	
	private Map<Integer, FontCharacter> characters;
	private Texture atlas;
	
	public Font(Texture atlas, Map<Integer, FontCharacter> characters) {
		
		this.atlas = atlas;
		this.characters = characters;
	}
	
	public FontCharacter getCharacter(int id) {
		return characters.get(id);
	}
	
	public Texture getAtlas() {
		return atlas;
	}
	
	public static Font loadFont(String file)	{
		Map<Integer, FontCharacter> characters = new HashMap<>();
        
		try {
			String line = null;
			
			InputStream stream = Class.class.getResourceAsStream("/assets/minequest/fonts/" + file + ".fnt");
			if(stream == null) {
				
				Debug.err("Font at directory : \"" + "/assets/minequest/fonts/" + file + ".fnt" + "\" was not found");
				return null;
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

			while((line = bufferedReader.readLine()) != null) {
			    String[] words = line.split("\\s+");
			    

		    	if(words[0].contains("char") && !words[0].contains("chars")) {
		    		
		    		int id = Integer.parseInt(words[1].substring(3));
		    		int x = Integer.parseInt(words[2].substring(2));
		    		int y = Integer.parseInt(words[3].substring(2));
		    		int width = Integer.parseInt(words[4].substring(6));
		    		int height = Integer.parseInt(words[5].substring(7));
		    		int xOffset = Integer.parseInt(words[6].substring(8));
		    		int yOffset = Integer.parseInt(words[7].substring(8));
		    		int xAdvance = Integer.parseInt(words[8].substring(9));
		    		
		    		characters.put(id, new FontCharacter(id, x, y, width, height, xOffset, yOffset, xAdvance));
		    	}
			}

			bufferedReader.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}         

		return new Font(Texture.loadTexture("/assets/minequest/fonts/" + file + ".png"), characters);
	}
}
