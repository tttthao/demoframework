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
	
	public String getUsername() {
		return pro.getProperty("username");
	}
	
	public String getPassword() {
		return pro.getProperty("password");
	}
	
	public String getChromePath() {
		return pro.getProperty("chromepath");
	}
	
	public String getIEPath() {
		return pro.getProperty("iepath");
	}
	
	public String getFireFoxPath() {
		return pro.getProperty("firefoxpath");
	}

}
