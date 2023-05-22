package com.config;


import java.io.FileInputStream;
import java.util.Properties;



public class PropertiesFile {
	 String property=null;
	
	public  String getProperties(String key) {
		try {
		Properties prop = new Properties();
		prop.load(new FileInputStream("./config.properties"));
		 property = prop.getProperty(key);
		// System.out.println(property);
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return property;
	
	}
}
