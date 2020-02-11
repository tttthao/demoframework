package com.abc.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	Properties pro;
	public ConfigReader() {
		pro = new Properties();
		try {
			pro.load(new FileInputStream(System.getProperty("user.dir")+"\\Configurations\\config.properties"));
		}catch(Exception e) {
			Log.error("Exception while loading config file: " + e.getMessage());
		}
	}
	
	public String getBaseUrl() {
		return pro.getProperty("baseURL");
	}

}
