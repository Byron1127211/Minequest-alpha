package com.darkmelon.minequest.client.rendering.guis;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.rendering.Tessellator;

public class GuiScreenRenderer {

	public static void render(GuiScreen screen) {
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		
		GL11.glPushMatrix();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glFrustum(0, 0, 0, 0, 0.1f, 500);
		GL11.glOrtho(0, MineQuest.instance.getWindow().getWidth(), 0, MineQuest.instance.getWindow().getHeight(), -500, 500);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		screen.render(Tessellator.INSTANCE);
		
		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}
}
