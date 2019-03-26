package com.darkmelon.minequest.client;

import com.darkmelon.minequest.client.rendering.Window;
import com.darkmelon.minequest.utils.Debug;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.world.World;

public class MineQuest implements Runnable {
	public static MineQuest instance;
	
	private final int width, height;
	
	private boolean running;
	private Thread gameThread;
	
	private Window window;
	private World world;
	
	public void init() {
		
		world = new World();
	}
	
	public void render() {
		
	}
	
	public void tick() {
		world.tick();
	}
	
	@Override
	public void run() {
		
		window = new Window(this.width, this.height, "MineQuest Alpha 1.0.0");
		
		init();
		
		Timer secondsTimer = new Timer();
		Timer ticksTimer = new Timer();
		int frames = 0, ticks = 0;
		
		while(running) {
			
			window.clear(0.5f, 0.8f, 1.0f);
			
			if(ticksTimer.getTimeMilli() >= 1000 / 20) {
				tick();
				ticks++;
				ticksTimer.subTimeMilli(1000 / 20);
			}
			
			if(window.shouldClose()) {
				running = false;
			}
			
			window.update();
			frames++;
			
			if(secondsTimer.getTimeMilli() >= 1000) {
				
				Debug.info("FPS : " + frames + " TPS : " + ticks);
				frames = 0;
				ticks = 0;
				secondsTimer.subTimeMilli(1000);
			}
		}
		
		window.close();
	}

	public synchronized void start() {
		
		this.running = true;
		this.gameThread.start();
	}
	
	public synchronized void stop() {
		try {
			this.gameThread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public Window getWindow() {
		return window;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public MineQuest(int width, int height) {
		
		if(instance != null) {
			throw new RuntimeException("Tried to create multiple Minequest instances on same JVM");
		}
		this.width = width;
		this.height = height;
		this.gameThread = new Thread(this, "client");
	}
}
