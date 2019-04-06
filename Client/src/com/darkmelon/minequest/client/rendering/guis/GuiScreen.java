package com.darkmelon.minequest.client.rendering.guis;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.fonts.Font;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.TextRenderer;
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
		
		Item item = mouseItem.getItem();
		int count = mouseItem.getCount();
		if(item.getID() != Block.air.getID() && count != 0) {
			GL11.glPushMatrix();
			item.renderInInventory(t, input.getMouseX() - 30, input.getMouseY() - 30, 100);
			GL11.glPopMatrix();
			
			if(count != 1) {
				if(count >= 0 && count < 10) {
					TextRenderer.renderString(t, Integer.toString(count), Font.minecraftia, input.getMouseX() - 30 + 50, input.getMouseY() - 30 + 25, 130, 0.6f);
				}else {
					TextRenderer.renderString(t, Integer.toString(count), Font.minecraftia, input.getMouseX() - 30 + 35, input.getMouseY() - 30 + 25, 130, 0.6f);
				}
			}
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
