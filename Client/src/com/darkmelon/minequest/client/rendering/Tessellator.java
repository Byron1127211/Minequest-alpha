package com.darkmelon.minequest.client.rendering;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

public class Tessellator {
	public static final Tessellator INSTANCE = new Tessellator();
	private static final int MAX_VERTICES = 10000;
	
	private FloatBuffer positions, colors, uvs;
	private int vertexCount;
	private float u, v, r, g, b;
	
	public Tessellator() {
		positions = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
		colors = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
		uvs = BufferUtils.createFloatBuffer(MAX_VERTICES * 2);
	}
	
	public void render() {
		
		positions.flip();
		colors.flip();
		uvs.flip();
		
		glVertexPointer(3, GL_FLOAT, 0, positions);
		glColorPointer(3, GL_FLOAT, 0, colors);
		glTexCoordPointer(2, GL_FLOAT, 0, uvs);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		glDrawArrays(GL_QUADS, 0, vertexCount);
		
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		
		clear();
	}
	
	public void uv(float u, float v) {
		if(Texture.boundTexture != null) {
			this.u = u / Texture.boundTexture.getWidth();
			this.v = v / Texture.boundTexture.getHeight();
			return;
		}
		this.u = u;
		this.v = v;
	}
	
	public void color(float r, float g, float b) {
		this.r = r;
		this.b = b;
		this.g = g;
	}
	
	public void vertex(float x, float y, float z) {
		if(++vertexCount < MAX_VERTICES) {
			positions.put(x).put(y).put(z);
			colors.put(r).put(g).put(b);
			uvs.put(u).put(v);
		}
	}
	
	public void clear() {
		positions.clear();
		colors.clear();
		uvs.clear();
		u = 0;
		v = 0;
		r = 1;
		g = 1; 
		b = 1;
	}
}
