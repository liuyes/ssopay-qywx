package com.ssopay.qywx.base;

import java.util.ResourceBundle;

public class Constants {
	public static final ResourceBundle bundle = ResourceBundle.getBundle("application");
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	/**
	 * 是否需要发送email
	 */
	public static final boolean NEED_EMAIL = false;
	/**
	 * 线程数,CPU核数*2
	 */
	public static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors()*2;
	/**
	 * redis服务器IP
	 */
	public static final String REDIS_SERVER = bundle.getString("redis.server");
	/**
	 * redis访问密码
	 */
	public static final String REDIS_AUTH = bundle.getString("redis.auth");
	/**
	 * 加密的token
	 */
	public static final byte[] TOKEN = bundle.getString("token").getBytes();
	/**
	 * 已禁用
	 */
	public static final int DISABLE_YES = -1;
	/**
	 * 未禁用
	 */
	public static final int DISABLE_NO = 1;
	/**
	 * 成功标识
	 */
	public static final int SUCCESS = 1;


}
