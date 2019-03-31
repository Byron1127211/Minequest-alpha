package com.darkmelon.minequest.client.rendering.guis;

import java.util.List;

import com.darkmelon.minequest.client.rendering.Tessellator;

import java.util.ArrayList;

public class GuiScreen {

	private List<GuiButton> buttons;
	
	public GuiScreen() {
		
		buttons = new ArrayList<>();
	}
	
	public void addButton(GuiButton button) {
		buttons.add(button);
	}
	
	public final void render(Tessellator t) {
		onDraw(t);
		for(GuiButton button : buttons) {
			button.render(t);
		}
	}
	
	public void onDraw(Tessellator t) {
		
	}
}
