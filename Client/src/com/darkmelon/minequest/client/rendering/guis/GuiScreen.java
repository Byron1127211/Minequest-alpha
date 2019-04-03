package com.darkmelon.minequest.client.rendering.guis;

import java.util.List;

import com.darkmelon.minequest.client.rendering.Tessellator;

import java.util.ArrayList;

public abstract class GuiScreen {

	private List<Gui> guis;
	
	public GuiScreen() {
		
		guis = new ArrayList<>();
		initGuis();
	}
	
	public abstract void initGuis();
	
	public void addGui(Gui gui) {
		guis.add(gui);
	}
	
	public final void render(Tessellator t) {
		onRender(t);
		for(Gui gui : guis) {
			gui.render(t);
		}
	}
	
	public void update() {
		for(Gui gui : guis) {
			gui.onUpdate();
		}
		onUpdate();
	}
	
	public void onUpdate() {}
	
	public void onRender(Tessellator t) {
		
	}
	
	public void onResize() {
		guis.clear();
		initGuis();
	}
}
