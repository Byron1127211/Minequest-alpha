package com.darkmelon.minequest.client.rendering.guis;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.blocks.Block;
import com.darkmelon.minequest.world.items.Item;

import java.util.ArrayList;

public abstract class GuiScreen {

	private List<Gui> guis;
	protected ItemStack mouseItem;
	
	public GuiScreen() {
		
		guis = new ArrayList<>();
		mouseItem = new ItemStack(Block.air, 1);
		initGuis();
	}
	
	public void addGui(Gui gui) {
		guis.add(gui);
	}
	
	public final void render(Tessellator t) {
		onRender(t);
		for(Gui gui : guis) {
			gui.render(t);
		}
		
		Input input = MineQuest.instance.getInput();
		
		if(mouseItem.getItem().getID() != Block.air.getID()) {
			if(mouseItem.getItem() instanceof Block) {
				Block.atlas.bind();
			}else if(mouseItem.getItem() instanceof Item) {
				Item.atlas.bind();
			}
			GL11.glPushMatrix();
			mouseItem.getItem().renderInInventory(t, input.getMouseX(), input.getMouseY(), 100);
			t.render();
			GL11.glPopMatrix();
		}
	}
	
	public void update() {
		for(Gui gui : guis) {
			gui.onUpdate();
		}
		onUpdate();
	}
	
	public void onUpdate() {}
	
	public abstract void initGuis();
	public abstract void onRender(Tessellator t);
	
	public void onResize() {
		guis.clear();
		initGuis();
	}
	
	public ItemStack getItemStackOnMouse() {
		return mouseItem;
	}
}
