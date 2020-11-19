package com.slt.myTest.netTank.tank01;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties props = new Properties();

	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		if(props == null){
			return null;
		}
		return props.get(key);
	}

	public static int getInt(String key){
		return Integer.parseInt((String)get(key));
	}
	//getString(key)

	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));

	}
}
