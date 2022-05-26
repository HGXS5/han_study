package cn.han.cpam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrmConfig {
	protected final static Logger logger = LoggerFactory.getLogger(UrmConfig.class);
	
	private static boolean oneApp;

	public static boolean isOneApp() {
		return oneApp;
	}

	public static void setOneApp(boolean oneApp) {
		UrmConfig.oneApp = oneApp;
	}
}
