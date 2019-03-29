package com.darkmelon.minequest.utils.maths;

import org.joml.SimplexNoise;
import org.lwjgl.opengl.GL11;

public class Maths {

	public static void perspective(float fov, float aspect, float near, float far) {
		
		float bottom = -near * (float) Math.tan(fov / 2);
	    float top = -bottom;
	    float left = aspect * bottom;
	    float right = -left;
	    GL11.glFrustum(left, right, bottom, top, near, far);
	}
	
	public static float clamp(float value, float min, float max) {
		return Math.min(max, Math.max(min, value));
	}
	
	public static float clampRotation(float angle) {
		
		if(angle >= 360) {
			angle -= 360;
		}else if(angle < 0) {
			angle += 360;
		}
		
		return angle;
	}
	
	public static float noise(float x, float z, float frequency, float amplitude) {
		return SimplexNoise.noise(x * frequency, z * frequency) * amplitude;
	}
}
