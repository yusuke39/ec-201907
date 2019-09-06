package com.example.common;

public class Hidden {

	public static boolean hidden(String command) {
		if ("◯✕△".equals(command)) {
			return true;
		} else if("伊賀さんありがとう".equals(command)) {
			return true;
		}
		return false;
	}
	public static String hidden() {
		return "/hidden/hidden";
	}
}
