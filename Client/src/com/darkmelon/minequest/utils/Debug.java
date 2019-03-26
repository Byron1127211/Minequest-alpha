package com.darkmelon.minequest.utils;

public class Debug {

	public static void info(Object msg) {

		System.out.println(Thread.currentThread().getName() + " [INF] : " + msg.toString());
	}
}
