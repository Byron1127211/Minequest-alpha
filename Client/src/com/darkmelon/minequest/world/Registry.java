package com.darkmelon.minequest.world;

import java.util.HashMap;
import java.util.Map;

public class Registry<K, V> {

	private Map<K, V> registry;
	
	public Registry() {
		registry = new HashMap<>();
	}
	
	public void register(K key, V value) {
		registry.put(key, value);
	}
	
	public V get(K key) {
		return registry.get(key);
	}
}
