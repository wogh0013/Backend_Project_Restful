package com.example.demo.common;

public class StringUtil {

	private StringUtil() {}

	public static String nullToValue(Object ob, String value) {
		if(ob==null)
			return value;
		else
			return ob.toString();
	}

}
