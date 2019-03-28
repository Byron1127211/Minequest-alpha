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
}
