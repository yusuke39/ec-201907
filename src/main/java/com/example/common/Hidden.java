package com.example.common;

public class Hidden {

	public static boolean hidden(String command) {
		if ("◯✕△".equals(command)) {
			return true;
		} else {
			return false;
		}
	}
	public static String hidden() {
		return "/hidden/hidden";
	}
}
