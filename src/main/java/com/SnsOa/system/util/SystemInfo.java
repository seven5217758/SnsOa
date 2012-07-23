package com.SnsOa.system.util;

import java.io.File;

/**
 * 常用系统常量
 * @author 董成龙
 *
 */
public class SystemInfo {
	/**
	 * 系统根目录
	 */
	public static final String SYSTEM=File.separatorChar+"SnsOaSysFile"+File.separatorChar;
	/**
	 * 用户文件位置
	 */
	public static final String SYSTEM_USER=SYSTEM+"Users"+File.separatorChar;
	/**
	 * 用户图片位置,需要结合用户id使用，SYSTEM_USER+id+USER_IMAGE
	 */
	public static final String USER_IMAGE=File.separatorChar+"image"+File.separatorChar;
}
