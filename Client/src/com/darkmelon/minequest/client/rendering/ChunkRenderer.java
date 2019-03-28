package com.darkmelon.minequest.client.rendering;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.chunk.Chunk;

public class ChunkRenderer {

	public static final Texture atlas = Texture.loadAsset("terrain/atlas");
	
	public void render(Chunk[] chunks) {
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		atlas.bind();
		
		GL11.glPushMatrix();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		Maths.perspective(1, (float)MineQuest.instance.getWindow().getWidth() / (float)MineQuest.instance.getWindow().getHeight(), 0.1f, 1000);
		
		for(Chunk chunk : chunks) {
			GL11.glCallList(chunk.getModel().getList(0));
		}
		
		GL11.glPopMatrix();
		
		atlas.unbind();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
}
