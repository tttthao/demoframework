package com.abc.utilities;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {
	private Log() {};
	
	private static Logger logger = Logger.getLogger(Log.class.getName());
	
	public static void initLog(String className) {
		logger = Logger.getLogger(className);
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Configurations\\log4j.properties");
	}
	
	public static void startLog(String testName) {
		logger.info("["+ testName +"] is Starting...");
	}
	
	public static void endLog(String testName) {
		logger.info("["+ testName +"] is Ending...");
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message );
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
	
}
