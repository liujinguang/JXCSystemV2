package com.lzw.utils;

import java.net.URL;

import org.apache.log4j.Logger;

public class Utils {
	private static Logger rootLogger = Logger.getLogger("rootLogger");

	public static URL getResourceUrl(String name) {
		return Utils.class.getClassLoader().getResource(name);
	}

	public static void main(String[] args) {
		rootLogger.debug(getResourceUrl("login.jpg"));
	}
}
