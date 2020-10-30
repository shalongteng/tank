package com.slt.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 可以写成 单例模式
 */
public class PropertyMgr {
	private static Properties props = new Properties();
	
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

	/**
	 * 之间返回int 类型
	 * @param key
	 * @return
	 */
	public static Object getInt(String key) {
		if(props == null){
			return null;
		}
		return Integer.parseInt((String) props.get(key));
	}

	/**
	 * 返回string类型
	 * @param key
	 * @return
	 */
	public static Object getString(String key) {
		if(props == null){
			return null;
		}
		return (String) props.get(key);
	}

	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));
		
	}
}
