package com.darkmelon.minequest.client.rendering.guis;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.Texture;
import com.darkmelon.minequest.client.input.MouseButton;
import com.darkmelon.minequest.client.rendering.Tessellator;

public class GuiButton extends Gui {
	
	private static Texture buttonTex = Texture.loadAsset("guis/button");
	
	public GuiButton(int x, int y, int depth, int width, int height) {
		super(x, y, depth, width, height);

	}
	
	@Override
	public void render(Tessellator t) { 
		buttonTex.bind();
		
		if(onHover()) {
			t.color(0.5f, 0.8f, 1.0f);
			if(MineQuest.instance.getInput().getMouseButton(MouseButton.LEFT)) {
				onClick();
			}
		}
		
		t.rect.rectUV(0, 0, 1, 1);
		t.rect.rect(x, y, depth, width, height);
		t.render();
		
		Texture.unbind();
	}
	
	@Override
	public void onUpdate() {
		if(onHover()) {
			if(MineQuest.instance.getInput().getMouseButton(MouseButton.LEFT)) {
				onClick();
			}
		}
	}
	
	public boolean onHover() {
		int mouseX = (int)MineQuest.instance.getInput().getMouseX();
		int mouseY = (int)MineQuest.instance.getInput().getMouseY();
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}

	public void onClick() {}
}
