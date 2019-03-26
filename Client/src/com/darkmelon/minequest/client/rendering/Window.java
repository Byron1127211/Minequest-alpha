package com.darkmelon.minequest.client.rendering;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {

	private String title;
	private int width, height;
	
	private long id;
	
	public Window(int width, int height, String title) {
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		glfwInit();
		id = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
		glfwMakeContextCurrent(id);
		glfwSwapInterval(0);
		
		GL.createCapabilities();
		
		GL11.glViewport(0, 0, this.width, this.height);
	}
	
	public void clear(float r, float g, float b) {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(r, g, b, 1);
	}
	
	public void update() {
		
		glfwSwapBuffers(this.id);
		glfwPollEvents();
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(this.id);
	}
	
	public void close() {
		glfwTerminate();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getTitle() {
		return title;
	}
}
