package com.darkmelon.minequest.utils;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;

public class Utils {
	
	public static final int FRONT = 0, BACK = 1, TOP = 2, BOTTOM = 3, RIGHT = 4, LEFT = 5;
	
	public static FloatBuffer toFloatBuffer(List<Float> list) {
		
		final FloatBuffer buffer = BufferUtils.createFloatBuffer(list.size());
		for(int i = 0; i < list.size(); i++) {
			buffer.put(list.get(i));
		}
		buffer.flip();
		return buffer;
	}
	
	public static int x(int dir) {
		return dir == Utils.RIGHT ? 1 : (dir == Utils.LEFT ? -1 : 0);
	}
	
	public static int y(int dir) {
		return dir == Utils.TOP ? 1 : (dir == Utils.BOTTOM ? -1 : 0);
	}
	
	public static int z(int dir) {
		return dir == Utils.FRONT ? 1 : (dir == Utils.BACK ? -1 : 0);
	}
}
