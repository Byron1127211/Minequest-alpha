package com.darkmelon.minequest.client.rendering;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.utils.maths.Maths;
import com.darkmelon.minequest.world.chunk.Chunk;
import com.darkmelon.minequest.world.entities.Player;

public class ChunkRenderer {

	public static final Texture atlas = Texture.loadAsset("terrain/atlas");
	
	public void render(Chunk[] chunks, Player player) {
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		atlas.bind();
		
		GL11.glPushMatrix();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		Maths.perspective(1, (float)MineQuest.instance.getWindow().getWidth() / (float)MineQuest.instance.getWindow().getHeight(), 0.1f, 1000);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glRotatef(player.getCamXRotation(), 1, 0, 0);
		GL11.glRotatef(player.ry, 0, 1, 0);
		GL11.glTranslatef(-player.x, -player.y, -player.z);
		
		for(Chunk chunk : chunks) {
			GL11.glTranslatef(chunk.getX() * 16, 0, chunk.getZ() * 16);
			GL11.glCallList(chunk.getModel().getList(0));
		}
		
		GL11.glPopMatrix();
		
		atlas.unbind();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
}
