package com.darkmelon.minequest.client.rendering;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import com.darkmelon.minequest.src.utils.Debug;

public class Texture {
	public static Texture boundTexture = null;
	private static int[] loadingWidth = new int[1], loadingHeight = new int[1];
	
	private int id;
	private int width, height;
	
	public Texture(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public void bind() {
		GL11.glBindTexture(GL_TEXTURE_2D, id);
		boundTexture = this;
	}
	
	public static void unbind() {
		GL11.glBindTexture(GL_TEXTURE_2D, 0);
		boundTexture = null;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private static Texture loadTexture(String dir) {
		
		ByteBuffer image = STBImage.stbi_load(dir, loadingWidth, loadingHeight, new int[1], 4);
		if(image == null) {
			Debug.err("Could not load texture at directory : \"" + dir + "\"");
			return null;
		}
		
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, loadingWidth[0], loadingHeight[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		return new Texture(id, loadingWidth[0], loadingHeight[0]);
	}
	
	public static Texture loadAsset(String file) {
		return loadTexture("resources/assets/minequest/textures/" + file + ".png");
	}
}
