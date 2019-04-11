package com.darkmelon.minequest.client;

import com.darkmelon.minequest.client.audio.SoundSystem;
import com.darkmelon.minequest.client.guis.GuiScreen;
import com.darkmelon.minequest.client.input.Input;
import com.darkmelon.minequest.client.rendering.ChunkRenderer;
import com.darkmelon.minequest.client.rendering.EntityRenderer;
import com.darkmelon.minequest.client.rendering.GuiScreenRenderer;
import com.darkmelon.minequest.client.rendering.Window;
import com.darkmelon.minequest.client.screens.PlayingScreen;
import com.darkmelon.minequest.utils.Debug;
import com.darkmelon.minequest.utils.Timer;
import com.darkmelon.minequest.world.Sounds;
import com.darkmelon.minequest.world.World;
import com.darkmelon.minequest.world.entities.EntityPlayer;

//3205 lines of code... for now
public class MineQuest implements Runnable {
	public static MineQuest instance;

	private final int width, height;

	private boolean running;
	private Thread gameThread;

	private Window window;
	private World world;
	private Input input;
	private EntityPlayer player;
	public boolean paused;
	
	private SoundSystem soundSystem;

	private GuiScreen screen;

	public void init() {

		soundSystem = new SoundSystem();
		soundSystem.init();
		Sounds.init(soundSystem);
		
		world = new World();
		player = new EntityPlayer(World.MAX_LOADED_CHUNKS * 16 / 2, 80, World.MAX_LOADED_CHUNKS * 16 / 2);
		
		Debug.info("Spawning in player...");
		world.getEntityManager().addEntity(player);
		
		input.hideCursor(true);

		showScreen(new PlayingScreen());
	}

	public void render() {

		world.regenerateChunks(player);
		ChunkRenderer.render(world.getChunks(), player);
		EntityRenderer.render(world.getEntityManager(), player, world);
		GuiScreenRenderer.render(screen);
	}

	public void update() {
		
		world.update();
		
		screen.update();
	}

	private void close() {
		
		soundSystem.shutDown();
		world.save();
	}

	
	@Override
	public void run() {

		window = new Window(this.width, this.height, "MineQuest Alpha 1.0.0", false);
		input = new Input(window);

		init();

		Timer secondsTimer = new Timer();
		Timer updateTimer = new Timer();
		int frames = 0, updates = 0;

		while (running) {

			window.clear(0.5f, 0.8f, 1.0f);

			if (updateTimer.getTimeMilli() >= 1000 / 60) {
				update();
				input.update();
				window.pollEvents();
				updates++;
				updateTimer.subTimeMilli(1000 / 60);
			}

			if (window.shouldClose()) {
				running = false;
			}

			render();

			window.update();
			frames++;

			if (secondsTimer.getTimeMilli() >= 1000) {

				Debug.info("FPS : " + frames + " UPS : " + updates);
				frames = 0;
				updates = 0;
				secondsTimer.subTimeMilli(1000);
			}
		}
		
		close();
		
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

	
	public void showScreen(GuiScreen screen) {
		if(this.screen != null) {
			this.screen.exit();
		}
		this.screen = screen;
		screen.initGuis();
	}
	
	public GuiScreen currentScreen() {
		return this.screen;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public Window getWindow() {
		return window;
	}

	public Input getInput() {
		return input;
	}
	
	public SoundSystem getSoundSystem() {
		return soundSystem;
	}
	
	public World getWorld() {
		return world;
	}
	
	public boolean isRunning() {
		return running;
	}

	
	public MineQuest(int width, int height) {

		if (instance != null) {
			throw new RuntimeException("Tried to create multiple Minequest instances on same JVM");
		}

		instance = this;
		this.width = width;
		this.height = height;
		this.gameThread = new Thread(this, "client");
	}
}
