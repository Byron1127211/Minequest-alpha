package com.darkmelon.minequest.client.rendering;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.utils.Utils;

public class Tessellator {
	public static final Tessellator INSTANCE = new Tessellator();

	private static final int MAX_VERTICES = 100000; 
	
	private FloatBuffer positions, colors, uvs;
	private float r, g, b, u, v;
	private int vertexCount;
	
	public Cube cube = new Cube();
	public Rect rect = new Rect();
	
	public Tessellator() {
		
		this.vertexCount = 0;
		positions = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
		colors = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
		uvs = BufferUtils.createFloatBuffer(MAX_VERTICES * 2);
	}
	
	public void render() {

		positions.rewind();
		colors.rewind();
		uvs.rewind();
		
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, positions);
		GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, uvs);
		GL11.glColorPointer(3, GL11.GL_FLOAT, 0, colors);
		
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		GL11.glDrawArrays(GL11.GL_QUADS, 0, vertexCount);
		
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		
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
		
		if(vertexCount == MAX_VERTICES - 1) {
			render();
			return;
		}
		
		positions.put(x).put(y).put(z);
		
		colors.put(r).put(g).put(b);
		
		uvs.put(u).put(v);
		
		vertexCount++;
	}
	
	public void clear() {
		
		positions.clear();
		colors.clear();
		uvs.clear();
		
		positions.rewind();
		colors.rewind();
		uvs.rewind();

		this.r = 1;
		this.g = 1;
		this.b = 1;
		this.u = 0;
		this.v = 0;
		
		this.vertexCount = 0;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public class Rect {
		
		private float minU, minV, maxU, maxV;
		
		public void rectUV(float minU, float minV, float maxU, float maxV) {
			
			this.minU = minU;
			this.minV = minV;
			this.maxU = maxU;
			this.maxV = maxV;
		}
		
		public void rect(int x, int y, int z, int width, int height) {
			
//			color(1, 0, 0);
			uv(maxU, maxV);
			vertex(x + width, y, z);
			uv(maxU, minV);
			vertex(x + width, y + height, z);
			uv(minU, minV);
			vertex(x, y + height, z);
			uv(minU, maxV);
			vertex(x, y, z);
		}
	}
	
	public class Cube {
		
		private boolean[] faces;
		private float[] colors;
		private float[] uvs;
		
		public Cube() {
			faces = new boolean[6];
			uvs = new float[6 * 4];
			colors = new float[6 * 3];
			clear();
		}
		
		public void setFace(int face, float minU, float minV, float maxU, float maxV) {
			faces[face] = true;
			uvs[face * 4 + 0] = minU;
			uvs[face * 4 + 1] = minV;
			uvs[face * 4 + 2] = maxU;
			uvs[face * 4 + 3] = maxV;
		}
		
		public void cube(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float x, float y, float z) {
			
			float minU, minV, maxU, maxV;
			
			if(faces[Utils.FRONT]) {
				
				minU = uvs[Utils.FRONT * 4];
				minV = uvs[Utils.FRONT * 4 + 1];
				maxU = uvs[Utils.FRONT * 4 + 2];
				maxV = uvs[Utils.FRONT * 4 + 3];
				
				color(colors[Utils.FRONT * 3], colors[Utils.FRONT * 3 + 1], colors[Utils.FRONT * 3 + 2]);
				
				uv(maxU, maxV);
				vertex(maxX + x, minY + y, maxZ + z);
				uv(maxU, minV);
				vertex(maxX + x, maxY + y, maxZ + z);
				uv(minU, minV);
				vertex(minX + x, maxY + y, maxZ + z);
				uv(minU, maxV);
				vertex(minX + x, minY + y, maxZ + z);
			}
			
			if(faces[Utils.BACK]) {
				
				minU = uvs[Utils.BACK * 4];
				minV = uvs[Utils.BACK * 4 + 1];
				maxU = uvs[Utils.BACK * 4 + 2];
				maxV = uvs[Utils.BACK * 4 + 3];
				
				color(colors[Utils.BACK * 3], colors[Utils.BACK * 3 + 1], colors[Utils.BACK * 3 + 2]);
				
				uv(maxU , maxV );
				vertex(minX + x, minY + y, minZ + z);
				uv(maxU , minV );
				vertex(minX + x, maxY + y, minZ + z);
				uv(minU , minV );
				vertex(maxX + x, maxY + y, minZ + z);
				uv(minU , maxV );
				vertex(maxX + x, minY + y, minZ + z);
			}
			if(faces[Utils.TOP]) {
				
				minU = uvs[Utils.TOP * 4];
				minV = uvs[Utils.TOP * 4 + 1];
				maxU = uvs[Utils.TOP * 4 + 2];
				maxV = uvs[Utils.TOP * 4 + 3];
				
				color(colors[Utils.TOP * 3], colors[Utils.TOP * 3 + 1], colors[Utils.TOP * 3 + 2]);
				
				uv(maxU , maxV );
				vertex(minX + x, maxY + y, minZ + z);
				uv(maxU , minV );
				vertex(minX + x, maxY + y, maxZ + z);
				uv(minU , minV );
				vertex(maxX + x, maxY + y, maxZ + z);
				uv(minU , maxV );
				vertex(maxX + x, maxY + y, minZ + z);
			}
			if(faces[Utils.BOTTOM]) {
				
				minU = uvs[Utils.BOTTOM * 4];
				minV = uvs[Utils.BOTTOM * 4 + 1];
				maxU = uvs[Utils.BOTTOM * 4 + 2];
				maxV = uvs[Utils.BOTTOM * 4 + 3];
				
				color(colors[Utils.BOTTOM * 3], colors[Utils.BOTTOM * 3 + 1], colors[Utils.BOTTOM * 3 + 2]);
				
				uv(maxU , maxV );
				vertex(minX + x, minY + y, maxZ + z);
				uv(maxU , minV );
				vertex(minX + x, minY + y, minZ + z);
				uv(minU , minV );
				vertex(maxX + x, minY + y, minZ + z);
				uv(minU , maxV );
				vertex(maxX + x, minY + y, maxZ + z);
			}
			if(faces[Utils.RIGHT]) {
				
				minU = uvs[Utils.RIGHT * 4];
				minV = uvs[Utils.RIGHT * 4 + 1];
				maxU = uvs[Utils.RIGHT * 4 + 2];
				maxV = uvs[Utils.RIGHT * 4 + 3];
				
				color(colors[Utils.RIGHT * 3], colors[Utils.RIGHT * 3 + 1], colors[Utils.RIGHT * 3 + 2]);
				
				uv(maxU , maxV );
				vertex(maxX + x, minY + y, minZ + z);
				uv(maxU , minV );
				vertex(maxX + x, maxY + y, minZ + z);
				uv(minU , minV );
				vertex(maxX + x, maxY + y, maxZ + z);
				uv(minU , maxV );
				vertex(maxX + x, minY + y, maxZ + z);
			}
			if(faces[Utils.LEFT]) {

				minU = uvs[Utils.LEFT * 4];
				minV = uvs[Utils.LEFT * 4 + 1];
				maxU = uvs[Utils.LEFT * 4 + 2];
				maxV = uvs[Utils.LEFT * 4 + 3];
				
				color(colors[Utils.LEFT * 3], colors[Utils.LEFT * 3 + 1], colors[Utils.LEFT * 3 + 2]);
				
				uv(maxU , maxV );
				vertex(minX + x, minY + y, maxZ + z);
				uv(maxU , minV );
				vertex(minX + x, maxY + y, maxZ + z);
				uv(minU , minV );
				vertex(minX + x, maxY + y, minZ + z);
				uv(minU , maxV );
				vertex(minX + x, minY + y, minZ + z);
			}
			
			clear();
		}
	
		public void clear() {
			
			for(int i = 0; i < 6; i++) {
				
				uvs[i * 4] = 0;
				uvs[i * 4 + 1] = 0;
				uvs[i * 4 + 2] = 1;
				uvs[i * 4 + 3] = 1;
				colors[i * 3] = 1;
				colors[i * 3 + 1] = 1;
				colors[i * 3 + 2] = 1;
				faces[i] = false;
			}
		}
	}
}
