package cn.han.util;

public class InjectStatusConstant {
	/**
	 * 待审核
	 */
	public static final String TO_AUDIT = "0";
	/**
	 * 审核通过
	 * 
	 */
	public static final String AUDIT_PASS = "1";
	/**
	 * 审核不通过
	 */
	public static final String AUDIT_NOT_PASS = "-1";
	/**
	 * 注入成功
	 */
	public static final String INJECT_SUCCESS = "99";
	/**
	 * 注入不成功
	 */
	public static final String INJECT_FAILED = "-99";
	/**
	 * 下线成功
	 */
	public static final String OFFLINE_SUCCESS = "101";
	/**
	 * 下线失败
	 */
	public static final String OFFLINE_FAILED = "102";
	/**
	 * 注入中
	 */
	public static final String INJECT_ING = "2";

	public static final String OFFLINE_ING = "103";

}
