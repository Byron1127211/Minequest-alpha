package com.darkmelon.minequest.client.audio;

import com.darkmelon.minequest.utils.Debug;

import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryJavaSound;

public class SoundSystem {

	private paulscode.sound.SoundSystem system;
	
	public void init() {
		try {
			Debug.info("Loading sound libraries...");
			SoundSystemConfig.addLibrary(LibraryJavaSound.class);
			SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
		} catch (SoundSystemException e) {
			Debug.err("Failed to load sound libraries");
			e.printStackTrace();
		}
		SoundSystemConfig.setSoundFilesPackage("assets/minequest/sounds/");
		
		system = new paulscode.sound.SoundSystem();
	}
	
	public void setListenerPosition(float x, float y, float z) {
		system.setListenerPosition(x, y, z);
	}
	
	public void setListenerAngle(float angle) {
		system.setListenerAngle(-(float)Math.toRadians(angle));
	}
	
	public void shutDown() {
		system.cleanup();
	}
	
	public void loadSound(Sound sound) {
		system.loadSound(sound.getFile());
	}
	
	public void playSound(boolean priority, Sound sound, float x, float y, float z, float volume) {
		if(sound != null) {
			system.quickPlay(priority, sound.getFile(), sound.loop(), x, y, z, sound.getType(), 250);
		}
	}
}
