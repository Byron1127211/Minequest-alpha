package com.darkmelon.minequest.client.rendering;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {

	private long id;
	private int width, height;
	
	public Window(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		
		glfwInit();
		id = glfwCreateWindow(width, height, title, 0, 0);
		glfwMakeContextCurrent(id);
		GL.createCapabilities();
		GL11.glViewport(0, 0, width, height);
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.5f, 0.8f, 1.0f, 1.0f);
	}
	
	public void update() {
		
		glfwPollEvents();
		glfwSwapBuffers(id);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(id);
	}
	
	public void close() {
		glfwDestroyWindow(id);
		glfwTerminate();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
