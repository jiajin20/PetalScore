package com.cf.huaban.util;

import java.util.UUID;

/**
 * ID 处理工具类
 */
public class NumberUtils {
	
	/**
	 * 使用 UUID 生成32位系统ID
	 * @return
	 */
	public static String makeIDByUUID() {
		String id = UUID.randomUUID().toString().replaceAll("-","");
		return id;
	}
	
	/**
	 * 使用时间戳生成ID, 13位
	 * @return
	 */
	public static String makeIDByCurrent() {
		Long mills = DateUtils.getCurrent();
		return mills.toString();
	}
}
