package com.herokuapp.restfulbooker.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverClass {
	
	private static Properties properties;
	protected String baseDir=System.getProperty("user.dir");
	
	public DriverClass() {
		
		try {
			properties=new Properties();
			FileInputStream fileInputStream=new FileInputStream(baseDir+"/src/main/java/com/herokuapp/restfulbooker/config/config.properties");
			properties.load(fileInputStream);
		}
		catch(IOException e) {
		e.printStackTrace();
		}
		
	}
	
	public String getBaseUri() {
		String baseURI=properties.getProperty("baseURI");
		return baseURI;
	}
	
	public String getUserName() {
		String userName=properties.getProperty("userName");
		return userName;
	}
	
	public String getPassword() {
		String password=properties.getProperty("password");
		return password;
	}
	
	
}
