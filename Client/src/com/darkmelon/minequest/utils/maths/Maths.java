package com.darkmelon.minequest.utils.maths;

import org.lwjgl.opengl.GL11;

public class Maths {

	public static void perspective(float fov, float aspect, float near, float far) {
		
		float bottom = -near * (float) Math.tan(fov / 2);
	    float top = -bottom;
	    float left = aspect * bottom;
	    float right = -left;
	    GL11.glFrustum(left, right, bottom, top, near, far);
	}
}
