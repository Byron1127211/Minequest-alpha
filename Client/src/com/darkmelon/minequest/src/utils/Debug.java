package com.darkmelon.minequest.src.utils;

public class Debug {

	public static void info(String msg) {
		System.out.println(Thread.currentThread().getName() + " [INF] : " + msg);
	}
	
	public static void err(String msg) {
		System.err.println(Thread.currentThread().getName() + " [INF] : " + msg);
	}
}
