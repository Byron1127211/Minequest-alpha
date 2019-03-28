package com.darkmelon.minequest.utils;

public class Debug {

	public static void info(Object msg) {

		System.out.println("(" + Thread.currentThread().getName()  + ")" + " [INF] : " + msg.toString());
	}
	
	public static void err(Object msg) {

		System.err.println("(" + Thread.currentThread().getName()  + ")" + " [ERR] : " + msg.toString());
	}
}
