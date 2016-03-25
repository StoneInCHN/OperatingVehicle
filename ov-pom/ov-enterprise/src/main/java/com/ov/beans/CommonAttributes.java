
package com.ov.beans;

/**
 * 公共参数
 * 
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" , "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** common-config.xml文件路径 */
	public static final String COMMON_CONFIG_XML_PATH = "/common-config.xml";

	/** common-config.properties文件路径 */
	public static final String COMMON_CONFIG_PROPERTIES_PATH = "/common-config.properties";
	
	/**管理员*/
	public static final String ADMIN = "admin";
	/**管理员配置元*/
	public static final String SYSTEM_MANAGE = "systemManage";
	public static final String USER_MANAGE = "userManage";
	public static final String ACCOUNT_MANAGE= "accountManage";
	public static final String DEPARTMENT_MANAGE= "departmentManage";
	public static final String AUTHORITY_MANAGE= "authorityManage";
	public static final String ROLE_MANAGE= "roleManage";
    
	
	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}