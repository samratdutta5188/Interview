package com.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_LinkedHashMap {
	
	private final int CAPACITY;
	private LinkedHashMap<Integer, Integer> map;
	
	@SuppressWarnings("serial")
	public LRUCache_LinkedHashMap(int capacity){
		this.CAPACITY = capacity;
		this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
			@SuppressWarnings({ "rawtypes", "unused" })
			protected boolean removeOldestEntry(Map.Entry entry) {
				return size() > CAPACITY;
			}
		};
	}
	
	public int get(int key){
		return map.containsKey(key) == false ? -1 : map.get(key);
	}
	
	public void set(int key, int value){
		map.put(key, value);
	}
}
