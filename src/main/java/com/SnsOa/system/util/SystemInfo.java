package com.SnsOa.system.util;

import java.io.File;

/**
 * ����ϵͳ����
 * @author ������
 *
 */
public class SystemInfo {
	/**
	 * ϵͳ��Ŀ¼
	 */
	public static final String SYSTEM=File.separatorChar+"SnsOaSysFile"+File.separatorChar;
	/**
	 * �û��ļ�λ��
	 */
	public static final String SYSTEM_USER=SYSTEM+"Users"+File.separatorChar;
	/**
	 * �û�ͼƬλ��,��Ҫ����û�idʹ�ã�SYSTEM_USER+id+USER_IMAGE
	 */
	public static final String USER_IMAGE=File.separatorChar+"image"+File.separatorChar;
}
