package com.darkmelon.minequest.client.rendering.guis;

import com.darkmelon.minequest.client.MineQuest;
import com.darkmelon.minequest.client.input.MouseButton;

public abstract class GuiButton extends Gui {
	
	public GuiButton(int x, int y, int depth, int width, int height) {
		super(x, y, depth, width, height);

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

	public abstract void onClick();
}
