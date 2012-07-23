package com.SnsOa.system.util;

public class LinkURL {
	/**
	 * 登陆：无“/分隔符”
	 * 主要参数
	 * String password,String account,String identifyingCode(验证码)
	 */
	public  static final String  MAIN_LOGIN="/login";
	/**
	 * 动态企业类别
	 */
	public static final String MAIN_TYPE="companyTypeTree";
	/**
	 * 注册企业账户
	 */
	public static final String MAIN_REGISTER="register";
	/**
	 * 生成验证码
	 */
	public static final String IDENTIFYINGCODE_IMAGE="image";
	/**
	 * ajax验证验证码是否正确
	 */
	public static final String IDENTIFYINGCODE="ajaxIdentifyingCode";
	/**
	 * 注销
	 */
	public static final String LOGOUT="logout";
	/**
	 * 用于获取应用程序列表
	 */
	public static final String APP_LIST="appList";
	/**
	 * 添加新的应用程序
	 */
	public static final String ADD_NEW_APP="addNewApp";
	/**
	 * 修改应用程序功能选项信息
	 */
	public static final String UPDATA_APP_ITEM="updateItem";
	/**
	 * 获取应用程序的功能详细信息
	 */
	public static final String GET_APP_ITEM_INFO="getItemByItemInfo";
	/**
	 * 修改应用程序功能的可使用状态
	 */
	public static final String UPDATE_ENABLE="enableitem";
	/**
	 * 删除应用程序功能信息
	 */
	public static final String DELETE_APP_ITEM="deleteitem";
}
