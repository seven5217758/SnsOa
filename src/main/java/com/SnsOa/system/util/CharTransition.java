package com.SnsOa.system.util;

import java.io.UnsupportedEncodingException;

public class CharTransition {
	/**
	 * ×ª»»³Éutf-8
	 * @param str
	 * @return
	 */
	public static String toUTF_8(String str){
		String strs=null;
		try {
			strs = new String(str.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strs;
	}
}
