package com.darkmelon.minequest.client.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import com.darkmelon.minequest.client.rendering.Window;

public class Input {
	private static final int MAX_KEYS = 349;
	
	private boolean[] keys;
	
	private float mDX, mDY;
	private float mPosX, mPosY;
	private float lastMPosX, lastMPosY;
	
	private double[] xBuffer = new double[1];
	private double[] yBuffer = new double[1];
	
	private Window window;
	
	public Input(Window window) {
		
		this.window = window;
		this.keys = new boolean[MAX_KEYS];
		
		GLFW.glfwSetKeyCallback(window.getID(), new GLFWKeyCallbackI() {

			@Override
			public void invoke(long id, int key, int scancode, int action, int mods) {
				
				if(window.getID() == id) {
					setKey(key, action);
				}
			}
		});
	}
	
	public void update() {
		
		lastMPosX = mPosX;
		lastMPosY = mPosY;
		GLFW.glfwGetCursorPos(window.getID(), xBuffer, yBuffer);
		mPosX = (float)xBuffer[0];
		mPosY = (float)yBuffer[0];
		mDX = mPosX - lastMPosX;
		mDY = mPosY - lastMPosY;
	}
	
	public void hideCursor(boolean hide) {
		
		GLFW.glfwSetInputMode(window.getID(), GLFW.GLFW_CURSOR, hide ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
	}
	
	public boolean getKey(int key) {
		if(key > 0 && key < MAX_KEYS) {
			return keys[key];
		}
		return false;
	}
	
	private void setKey(int key, int action) {
		if(key != GLFW.GLFW_KEY_UNKNOWN) {
			this.keys[key] = action != GLFW.GLFW_RELEASE;	
		}
	}
	
	public float getMouseDX() {
		return mDX;
	}
	
	public float getMouseDY() {
		return mDY;
	}
}
