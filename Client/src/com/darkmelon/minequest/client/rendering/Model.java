package com.darkmelon.minequest.client.rendering;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.utils.Utils;

public class Model {

	private int lists;
	private float r, g, b, u, v;
	private List<Float> positions, colors, uvs;
	
	public Model(int listsSize) {
		
		positions = new ArrayList<>();
		colors = new ArrayList<>();
		uvs = new ArrayList<>();
		clear();
		lists = GL11.glGenLists(listsSize);
	}
	
	public void create(int listIndex) {

		GL11.glNewList(lists + listIndex, GL11.GL_COMPILE);
		
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, Utils.toFloatBuffer(positions));
		GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, Utils.toFloatBuffer(uvs));
		GL11.glColorPointer(3, GL11.GL_FLOAT, 0, Utils.toFloatBuffer(colors));
		
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, positions.size() / 2);
		
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		
		GL11.glEndList();
		
		clear();
	}
	
	public void color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void uv(float u, float v) {
		this.u = u;
		this.v = v;
	}
	
	public void vertex(float x, float y, float z) {
		
		positions.add(x);
		positions.add(y);
		positions.add(z);
		
		colors.add(r);
		colors.add(g);
		colors.add(b);
		
		uvs.add(u);
		uvs.add(v);
	}
	
	public void clear() {
		
		positions.clear();
		colors.clear();
		uvs.clear();
//		System.gc();
		this.r = 1;
		this.g = 1;
		this.b = 1;
		this.u = 0;
		this.v = 0;
	}
	
	public int getList(int index) {
		return lists + index;
	}
}
