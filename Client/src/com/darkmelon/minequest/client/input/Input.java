package com.darkmelon.minequest.client.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import com.darkmelon.minequest.client.rendering.Window;

public class Input {
	private static final int MAX_KEYS = 349;
	
	private boolean[] keys;
	
	public Input(Window window) {
		
		keys = new boolean[MAX_KEYS];
		
		GLFW.glfwSetKeyCallback(window.getID(), new GLFWKeyCallbackI() {

			@Override
			public void invoke(long id, int key, int scancode, int action, int mods) {
				
				if(window.getID() == id) {
					setKey(key, action);
				}
			}
		});
	}
	
	public boolean getKey(int key) {
		return keys[key];
	}
	
	private void setKey(int key, int action) {
		if(key != GLFW.GLFW_KEY_UNKNOWN) {
			this.keys[key] = action != GLFW.GLFW_RELEASE;	
		}
	}
}
