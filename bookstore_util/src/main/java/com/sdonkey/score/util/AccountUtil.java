package com.sdonkey.score.util;

/**
 * @author 赵超 
 * 
 * 处理银行卡号显示
 * 处理开户人姓名显示
 * 
 */
public class AccountUtil {
	public static final int fourNumbers = 0;
	public static final int onlyShowFourNumbers = 1;

	/**
	 * 根据不同的标记信息返回银行卡号的不同显示形式
	 * 
	 * @param flag
	 * @return
	 */
	public static String getAccountNum(int flag, String accountNum) {
		if (accountNum != null) {
			//只显示银行卡号的后四位数	 8888
			if (flag == fourNumbers) {	
				return getAccountNum(accountNum);
			}
			//银行卡号的后四位数显示出来，前面的数字都用星号代替	 * * * * * * * * * * * 8888
			if(flag == onlyShowFourNumbers) {	
				return getAccountNumFirst(accountNum+"", 4)+getAccountNum(accountNum);
			}
		}
		return null;
	}
	
	private static String getAccountNum(String accountNum){
		return accountNum.substring(
				accountNum.length() - 4,
				accountNum.length());
	}
	
	private static String getAccountNumFirst(String accountNum, int num) {
		// TODO 将银行卡后四位数之前的数字替换成 * 
		//String number = accountNum.toString();
		StringBuffer newNumber = new StringBuffer("");
		for(int i = 1; i <= accountNum.length() - num; i++) {
			newNumber.append("* ");
		}
		return newNumber.toString();
	}
	
	public static String getAccountUserName(String name) {
		if (name.length() > 1) {
			int num = 1;
			if(name.length() > 2) {
				num = 2;
			}
			String first = getAccountNumFirst(name, num);
			return first + name.substring(name.length()-num, name.length());
		} else {
			return name;
		}
	}
	
}
