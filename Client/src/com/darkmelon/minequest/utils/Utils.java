package com.darkmelon.minequest.utils;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.system.MemoryStack;

public class Utils {

	private static MemoryStack stack = MemoryStack.stackPush();
	
	public static FloatBuffer toFloatBuffer(List<Float> list) {
		
		final FloatBuffer buffer = stack.callocFloat(list.size());
		for(int i = 0; i < list.size(); i++) {
			buffer.put(list.get(i));
		}
		buffer.flip();
		return buffer;
	}
}
