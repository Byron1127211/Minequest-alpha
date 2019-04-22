package com.darkmelon.minequest.world.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.utils.Utils;
import com.darkmelon.minequest.utils.maths.AABB;
import com.darkmelon.minequest.world.World;

public class EntityZombie extends EntityLiving {

	private static Texture texture = Texture.loadAsset("mobs/zombie");
	
	public EntityZombie(float x, float y, float z) {
		super(x, y, z, 20, 20);

	}

	@Override
	public void onUpdate(World world) {

		vy -= World.GRAVITY_FORCE;
	}
	
	
	@Override
	public void onRender(Tessellator t, World world) {
		
		glTranslatef(x, y, z);
		glRotatef(rx, 1, 0, 0);
		glRotatef(ry, 0, 1, 0);
		glRotatef(rz, 0, 0, 1);
		texture.bind();
		
		t.setTextureDimensions(texture);

		//Head
		glPushMatrix();
	
		t.cube.setFace(Utils.FRONT, 0, 0, 8, 8);
		t.cube.setFace(Utils.RIGHT, 8, 0, 16, 8);
		t.cube.setFace(Utils.LEFT, 16, 0, 8, 8);
		t.cube.setFace(Utils.BACK, 16, 0, 24, 8);
		t.cube.setFace(Utils.TOP, 8, 8, 16, 16);
		t.cube.setFace(Utils.BOTTOM, 0, 8, 8, 16);
		t.cube.cube(-0.25f, -0.25f, -0.25f, 0.25f, 0.25f, 0.25f, 0, 0, 0);
		
		t.render();
		t.setTextureDimensions(texture);
		
		glPopMatrix();
		
		//Body
		glPushMatrix();
		
		GL11.glTranslatef(0, -0.25f, 0);
		
		t.cube.setFace(Utils.FRONT, 24, 0, 32, 12);
		t.cube.setFace(Utils.RIGHT, 32, 0, 36, 12);
		t.cube.setFace(Utils.LEFT, 36, 0, 32, 12);
		t.cube.setFace(Utils.BACK, 36, 0, 44, 12);
		t.cube.setFace(Utils.TOP, 24, 20, 32, 16);
		t.cube.setFace(Utils.BOTTOM, 24, 16, 32, 12);
		t.cube.cube(-0.25f, -0.75f, -0.125f, 0.25f, 0.0f, 0.125f, 0, 0, 0);
		
		t.render();
		t.setTextureDimensions(texture);
		
		glPopMatrix();
		
		//Right hand
		glPushMatrix();
			
		glTranslatef(-0.375f, -0.375f, 0);
		glRotatef(-90, 1, 0, 0);
		
		t.cube.setAllFaces(16, 8, 20, 20);
		t.cube.setFace(Utils.TOP, 4, 16, 8, 20);
		t.cube.setFace(Utils.BOTTOM, 12, 16, 16, 20);
		t.cube.cube(-0.125f, -0.625f, -0.125f, 0.125f, 0.125f, 0.125f, 0, 0, 0);
		
		t.render();
		t.setTextureDimensions(texture);
		
		glPopMatrix();
		
		//Left hand
		glPushMatrix();
			
		glTranslatef(0.375f, -0.375f, 0);
		glRotatef(-90, 1, 0, 0);
		
		t.cube.setAllFaces(16, 8, 20, 20);
		t.cube.setFace(Utils.TOP, 4, 16, 8, 20);
		t.cube.setFace(Utils.BOTTOM, 12, 16, 16, 20);
		t.cube.cube(-0.125f, -0.625f, -0.125f, 0.125f, 0.125f, 0.125f, 0, 0, 0);
		
		t.render();
		t.setTextureDimensions(texture);
		
		glPopMatrix();
		
		//Right Leg
		glPushMatrix();
			
		glTranslatef(-0.125f, -1.0f, 0);
		glRotatef(0, 1, 0, 0);
		
		t.cube.setAllFaces(20, 8, 24, 20);
		t.cube.setFace(Utils.TOP, 0, 16, 4, 20);
		t.cube.setFace(Utils.BOTTOM, 8, 16, 12, 20);
		t.cube.cube(-0.125f, -0.75f, -0.125f, 0.125f, 0.0f, 0.125f, 0, 0, 0);
		
		t.render();
		t.setTextureDimensions(texture);
		
		glPopMatrix();
		
		//Left Leg
		glPushMatrix();
			
		glTranslatef(0.125f, -1.0f, 0);
		glRotatef(0, 1, 0, 0);
		
		t.cube.setAllFaces(20, 8, 24, 20);
		t.cube.setFace(Utils.TOP, 0, 16, 4, 20);
		t.cube.setFace(Utils.BOTTOM, 8, 16, 12, 20);
		t.cube.cube(-0.125f, -0.75f, -0.125f, 0.125f, 0.0f, 0.125f, 0, 0, 0);
		
		t.render();
		
		glPopMatrix();
		
		Texture.unbind();
	}
	
	@Override
	public void getHitbox(AABB dest) {
		dest.set(-0.25f, -1.74f, -0.25f, 0.25f, 0.24f, 0.25f);
	}
}
