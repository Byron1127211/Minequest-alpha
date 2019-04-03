package com.darkmelon.minequest.client.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;

import com.darkmelon.minequest.client.rendering.Window;

public class Input {
	private static final int MAX_KEYS = 349;
	private static final int MAX_MOUSE_BUTTONS = 3;
	
	private boolean[] keys;
	private boolean[] mouseButtons;
	
	private float mDX, mDY;
	private float mPosX, mPosY;
	private float lastMPosX, lastMPosY;
	
	private float scrollDX, scrollDY;
	
	private double[] xBuffer = new double[1];
	private double[] yBuffer = new double[1];
	
	private Window window;
	
	public Input(Window window) {
		
		this.window = window;
		this.keys = new boolean[MAX_KEYS];
		this.mouseButtons = new boolean[MAX_MOUSE_BUTTONS];
		
		GLFW.glfwSetKeyCallback(window.getID(), new GLFWKeyCallbackI() {

			@Override
			public void invoke(long id, int key, int scancode, int action, int mods) {
				
				if(window.getID() == id) {
					setKey(key, action);
				}
			}
		});
		
		GLFW.glfwSetMouseButtonCallback(window.getID(), new GLFWMouseButtonCallbackI() {
			
			@Override
			public void invoke(long id, int button, int action, int mods) {
				
				if(window.getID() == id) {
					setMouseButton(button, action);
				}
			}
		});
		
		GLFW.glfwSetScrollCallback(window.getID(), new GLFWScrollCallbackI() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				
				scrollDX = (float)xoffset;
				scrollDY = (float)yoffset;
			}
		});
	}
	
	public void update() {
		
		lastMPosX = mPosX;
		lastMPosY = mPosY;
		GLFW.glfwGetCursorPos(window.getID(), xBuffer, yBuffer);
		mPosX = (float)xBuffer[0];
		mPosY = window.getHeight() - (float)yBuffer[0];
		mDX = mPosX - lastMPosX;
		mDY = mPosY - lastMPosY;
		scrollDY = 0;
		scrollDX = 0;
	}
	
	public void hideCursor(boolean hide) {
		
		GLFW.glfwSetInputMode(window.getID(), GLFW.GLFW_CURSOR, hide ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
	}
	
	public boolean getKey(int key) {
		if(key >= 0 && key < MAX_KEYS) {
			return keys[key];
		}
		return false;
	}
	
	public boolean getMouseButton(int button) {
		if(button >= 0 && button < MAX_MOUSE_BUTTONS) {
			return mouseButtons[button];
		}
		return false;
	}
	
	private void setKey(int key, int action) {
		if(key != GLFW.GLFW_KEY_UNKNOWN && key < MAX_KEYS) {
			this.keys[key] = action != GLFW.GLFW_RELEASE;	
		}
	}
	
	private void setMouseButton(int button, int action) {
		if(button >= 0 && button < MAX_MOUSE_BUTTONS) {
			this.mouseButtons[button] = action != GLFW.GLFW_RELEASE;	
		}
	}
	
	public float getMouseDX() {
		return mDX;
	}
	
	public float getMouseDY() {
		return mDY;
	}
	
	public float getMouseX() {
		return mPosX;
	}
	
	public float getMouseY() {
		return mPosY;
	}
	
	public float getScrollDX() {
		return scrollDX;
	}
	
	public float getScrollDY() {
		return scrollDY;
	}
}
