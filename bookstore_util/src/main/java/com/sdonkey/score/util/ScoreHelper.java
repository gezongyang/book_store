package com.sdonkey.score.util;

/**
 * @author 赵超
 *	积分交易明细的 Util
 *	包含：
 *		交易类型常量(int)
 *		收支类型常量(String)
 *		转账类型常量(String)
 *		用户类型常量(String)
 *		提现类型常量(String)
 */
public class ScoreHelper {
	//定义交易类型常量
	/**
	 * 	Ad = 0	合约广告
	 */
	public static final int Ad = 0;		
	/**
	 * transferToFriend = 1	好友转账
	 */
	public static final int transferToFriend = 1;
	/**
	 * buyApp = 2			应用购买
	 */
	public static final int buyApp = 2;
	/**
	 * payForApp = 3		应用充值
	 */
	public static final int payForApp = 3;
	/**
	 * towardAd = 4			专项广告
	 */
	public static final int towardAd = 4;
	/**
	 * transferToBank = 5	提现到银行卡
	 */
	public static final int transferToBank = 5;

	//定义收支类型常量
	/**
	 * INCOME = "收入"	1
	 */
	public static final String INCOME = "收入";
	/**
	 * OUTPUT = "支出"	-1
	 */
	public static final String OUTPUT = "支出";
	
	//定义转账类型常量
	/**
	 * IMMEDIATELY = "立即到账"	0
	 */
	public static final String IMMEDIATELY = "立即到账";
	/**
	 * DURING_TWO_HOURS = "两小时内到账"	1
	 */
	public static final String DURING_TWO_HOURS = "两小时内到账";
	
	//定义用户类型常量
	/**
	 * NORMAL_USER = "普通用户"	0
	 */
	public static final String NORMAL_USER = "user";
	/**
	 * DEVELOPER = "内容商"	1
	 */
	public static final String DEVELOPER = "developer";
	
	//提现类型常量
	/**
	 * EXTRACT_QUICKLY = "快速提现"	0
	 */
	public static final String EXTRACT_QUICKLY = "快速提现";
	/**
	 * EXTRACT_SAFETY = "安全提现"	1
	 */
	public static final String EXTRACT_SAFETY = "安全提现";
	
	
	/**
	 * 通过编号得到交易类型
	 * @param dealNum	交易类型编号
	 * @return
	 */
	public static String getDealType(Integer dealNum) {
		String dealType = null;
		switch (dealNum) {
		case Ad:
			dealType = "合约广告";
			break;
		case transferToFriend:
			dealType = "好友转账";
			break;
		case buyApp:
			dealType = "应用购买";
			break;
		case payForApp:
			dealType = "应用充值";
			break;
		case towardAd:
			dealType = "专项广告";
			break;
		case transferToBank:
			dealType = "提现到银行卡";
			break;
		default:
			dealType = "其他类型";
			break;
		}
		return dealType;
	}
	
}
