package com.darkmelon.minequest.utils;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;

public class Utils {
	
	public static FloatBuffer toFloatBuffer(List<Float> list) {
		
		final FloatBuffer buffer = BufferUtils.createFloatBuffer(list.size());
		for(int i = 0; i < list.size(); i++) {
			buffer.put(list.get(i));
		}
		buffer.flip();
		return buffer;
	}
}
