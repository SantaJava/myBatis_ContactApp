package edu.iot.lib.app;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private static Map<String,  Object> map = new HashMap<>();
	
	public static void setAttribute(String key, Object value) {
		map.put(key, value);
	}
	
	public static Object getAttribute(String key) {
		return map.get(key);
	}
	
	public static void remove(String key) {
		map.remove(key);
	}	
}
