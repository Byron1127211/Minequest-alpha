package com.darkmelon.minequest.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.PNGDecoder;

import com.darkmelon.minequest.utils.Debug;

public class Texture {

	private static Map<String, Texture> textures = new HashMap<>();
	private static int lastBoundTexture = -99999999;
	
	private int id, width, height;

	public Texture(int id, int width, int height) {

		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void bind() {
		if(lastBoundTexture != id) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		}
	}
	
	public static void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		lastBoundTexture = -99999999;
	}
	
	public static Texture loadTexture(String dir) {
	
		if(!textures.containsKey(dir)) {

			try {
				
				InputStream stream = Class.class.getResourceAsStream(dir);
				if(stream == null) {
					
					Debug.err("Texture at directory : \"" + dir + "\" was not found");
					return null;
				}
				
				PNGDecoder decoder = new PNGDecoder(stream);
				ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
				decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.RGBA);
				buffer.flip();
				
				int textureID = GL11.glGenTextures();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
				
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
				
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
				
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
				
				Texture texture = new Texture(textureID, decoder.getWidth(), decoder.getHeight());
				
				textures.put(dir, texture);
				
				return texture;
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			return null;
		}
		
		return textures.get(dir);
	}

	public static Texture loadAsset(String file) {
		return loadTexture("/assets/minequest/textures/" + file + ".png");
	}
}
