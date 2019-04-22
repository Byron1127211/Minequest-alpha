package com.darkmelon.minequest.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import com.darkmelon.minequest.utils.Debug;

public class Texture {

	private static Map<String, Texture> textures = new HashMap<>();
	private static IntBuffer loadingWidth = BufferUtils.createIntBuffer(1),
							loadingHeight = BufferUtils.createIntBuffer(1),
							loadingChannels = BufferUtils.createIntBuffer(1);
	
	public static Texture boundTexture = null;
	
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
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		boundTexture = this;
	}
	
	public static void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		boundTexture = null;
	}
	
	public static Texture loadTexture(String dir) {
	
		if(!textures.containsKey(dir)) {

			InputStream stream = Class.class.getResourceAsStream(dir);
			
			if(stream == null) {	
				Debug.err("Texture at directory : \"" + dir + "\" was not found");
				return null;
			}
			
			ByteBuffer imageBuffer = null;
			try {
				byte[] imageArray = IOUtils.toByteArray(stream);
				imageBuffer = BufferUtils.createByteBuffer(imageArray.length);
				imageBuffer.put(imageArray);
				imageBuffer.flip();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			ByteBuffer buffer = STBImage.stbi_load_from_memory(imageBuffer, loadingWidth, loadingHeight, loadingChannels, 0);
			
			if(buffer == null) {
				Debug.err("Texture at directory : \"" + dir + "\" could not be loaded");
				return null;
			}
			
			int textureID = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, loadingWidth.get(0), loadingHeight.get(0), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			
			Texture texture = new Texture(textureID, loadingWidth.get(0), loadingHeight.get(0));
			
			textures.put(dir, texture);
			
			return texture;
		}
		
		return textures.get(dir);
	}

	public static Texture loadAsset(String file) {
		return loadTexture("/assets/minequest/textures/" + file + ".png");
	}
}
