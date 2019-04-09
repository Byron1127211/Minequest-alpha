package com.darkmelon.minequest.client.audio;

import paulscode.sound.SoundSystemConfig;

public class Sound {

	public static final int AMBIENT = SoundSystemConfig.ATTENUATION_NONE, ROLLOF = SoundSystemConfig.ATTENUATION_ROLLOFF;
	
	private String file;
	private boolean loop;
	private int type;
	
	public Sound(String file, boolean loop, int type) {

		this.file = file;
		this.loop = loop;
		this.type = type;
	}
	
	public String getFile() {
		return file;
	}
	public boolean loop() {
		return loop;
	}
	public int getType() {
		return type;
	}
}
