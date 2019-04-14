package com.darkmelon.minequest.client;

import com.darkmelon.minequest.client.rendering.Tessellator;
import com.darkmelon.minequest.client.rendering.Window;
import com.darkmelon.minequest.src.World;

public class MineQuest implements Runnable {
	public static MineQuest instance;
	
	private final int START_WIDTH, START_HEIGHT;
	
	private Window window;

	private Thread clientThread;
	private boolean running;
	private World world;
	
	private void init() {
		world = new World();
		world.getChunk(0, 0).update(Tessellator.INSTANCE, world);
	}
	
	private void update() {
		
	}
	
	private void render() {
		
	}
	
	private void exit() {
		
	}
	
	@Override
	public void run() {

		window = new Window(START_WIDTH, START_HEIGHT, "MineQuest Alpha 1.0.2");
		init();
		
		while(running) {
			
			window.clear();
			update();
			
			render();
			window.update();
			
			if(window.shouldClose()) {
				running = false;
			}
		}
		
		exit();
		window.close();
	}
	
	public synchronized void start() {
		if(instance == null) {
			instance = this;
			running = true;
			clientThread.start();
		}
	}
	
	public MineQuest(int width, int height) {
		
		this.START_WIDTH = width;
		this.START_HEIGHT = height;
		clientThread = new Thread(this, "client");
	}
}
