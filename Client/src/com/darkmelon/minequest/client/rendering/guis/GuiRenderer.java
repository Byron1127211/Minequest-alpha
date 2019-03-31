package com.darkmelon.minequest.client.rendering.guis;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.rendering.Tessellator;

public class GuiRenderer {

	public void render(Gui gui) {
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		
		GL11.glPushMatrix();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluOrtho2D(0, MineQuest.instance.getWindow().getWidth(), 0, MineQuest.instance.getWindow().getHeight());
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		gui.render(Tessellator.INSTANCE);
		Tessellator.INSTANCE.render();
		
		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}
}
