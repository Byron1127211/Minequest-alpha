package com.darkmelon.minequest.utils;

public class Timer {

	private long lastTime, timeOffset;
	
	public Timer() {
		lastTime = System.nanoTime();
	}
	
	public long getTimeNano() {
		return (System.nanoTime() - lastTime) + timeOffset;
	}
	
	public long getTimeMilli() {
		return getTimeNano() / 1000000;
	}
	
	public void subTimeNano(long nano) {
		timeOffset -= nano;
	}
	
	public void subTimeMilli(long milli) {
		subTimeNano(milli * 1000000);
	}
	
	public void reset() {
		lastTime = System.nanoTime();
	}
}
