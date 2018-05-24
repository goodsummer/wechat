package com.feng.wechat.constants;

public class WeChatConstants {
	
	/**
	 * appid
	 */
	public static final String APP_ID = "xxxxxxxxxxxxxxxxx";
	
	/**
	 * appsecret
	 */
	public static final String APPSECRET = "xxxxxxxxxxxxxxxxxxxx";
	
	/**
	 * token
	 */
	public static final String TOKEN = "zjfweixin";
	
	/**
	 * accesstoken
	 */
	public static String ACCESSTOKEN = "";
		
	/**
	 * 请求accesstoken失败后的重试时间(毫秒)
	 */
	public static int ACCESSTOKEN_RETRY_TIME = 1000 * 3;
	
	/**
	 * 存储用户发来的图片的文件夹的路径
	 */
	public static final String USR_IMG_PATH = "D://WeChatUsrFile/img/";
	
	/**
	 * 存储用户发来的语音的文件夹的路径
	 */
	public static final String USR_VOICE_PATH = "D://WeChatUsrFile/voice/";

	//public static final String ENCODING_AES_KEY = "MOalzbWvNhRdASOB5QUHDZ0saxwIPl6Y4AaM26NyAou";
}
