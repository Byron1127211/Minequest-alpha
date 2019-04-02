package com.darkmelon.minequest.client;

import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.rendering.ChunkRenderer;
import com.darkmelon.minequest.client.rendering.Window;
import com.darkmelon.minequest.client.rendering.guis.GuiButton;
import com.darkmelon.minequest.client.rendering.guis.GuiScreen;
import com.darkmelon.minequest.client.rendering.guis.GuiScreenRenderer;
import com.darkmelon.minequest.utils.Debug;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.Player;

public class MineQuest implements Runnable {
	public static MineQuest instance;
	
	private final int width, height;
	
	private boolean running;
	private Thread gameThread;
	
	private Window window;
	private World world;
	private Input input;
	private Player player;
	
	private GuiScreen screen;
	
	public void init() {
		
		world = new World();
		player = new Player(World.MAX_LOADED_CHUNKS * 16 / 2, 80, World.MAX_LOADED_CHUNKS * 16 / 2);
		
//		input.hideCursor(true);
		
		screen = new GuiScreen() {

			@Override
			public void initGuis() {

				int wWidth = MineQuest.instance.getWindow().getWidth();
				int wHeight = MineQuest.instance.getWindow().getHeight();
				
				addGui(new GuiButton(wWidth / 2 - 320 / 2, wHeight / 2 - 80 / 2, 0, 400, 80) {
					
					@Override
					public void onClick() {
						
					}
				});
			}	
		};
		
	}
	
	public void render() {
		
		world.tick(player);
		ChunkRenderer.render(world.getChunks(), player);
		GuiScreenRenderer.render(screen);
	}
	
	public void update() {
		
		screen.update();
//		player.update(world);
	}
	
	@Override
	public void run() {
		
		window = new Window(this.width, this.height, "MineQuest Alpha 1.0.0", false);
		input = new Input(window);
		
		init();
		
		Timer secondsTimer = new Timer();
		Timer updateTimer = new Timer();
		int frames = 0, updates = 0;
		
		while(running) {
			
			window.clear(0.5f, 0.8f, 1.0f);
			
			if(updateTimer.getTimeMilli() >= 1000 / 60) {
				input.update();
				update();
				updates++;
				updateTimer.subTimeMilli(1000 / 60);
			}
			
			if(window.shouldClose()) {
				running = false;
			}
			
			render();
			
			window.update();
			frames++;
			
			if(secondsTimer.getTimeMilli() >= 1000) {
				
				Debug.info("FPS : " + frames + " UPS : " + updates);
				frames = 0;
				updates = 0;
				secondsTimer.subTimeMilli(1000);
			}
		}
		
		world.save();
		
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
	
	public Input getInput() {
		return input;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public MineQuest(int width, int height) {
		
		if(instance != null) {
			throw new RuntimeException("Tried to create multiple Minequest instances on same JVM");
		}
		
		instance = this;
		this.width = width;
		this.height = height;
		this.gameThread = new Thread(this, "client");
	}
}
