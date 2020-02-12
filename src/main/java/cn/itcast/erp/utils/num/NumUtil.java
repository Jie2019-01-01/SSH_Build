package cn.itcast.erp.utils.num;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumUtil {

	public static int serNum = 1;
	public static final int ORDER_NUMBER_MAX_LENGTH = 7;
	public static final byte[] zeros = {48,48,48,48,48,48};
	
	public static String orderNumUtil() {
		// 订单号生成规则
		//1.年（只只留两位）月日
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyMMdd");
		String formatDate = df.format(date);
		//2.序列号，每天从1开始
		int num = serNum++;
		//2.1每天订单按照百万个计算，那么生成的第一个订单号就是: formatDate + 000000 + 1
		int lenNum = (num+"").length();
		String lenZero = new String(zeros, 0, ORDER_NUMBER_MAX_LENGTH - lenNum);
		String orderNum = formatDate +lenZero+ num;
		// 转成十六进制返回
		return Long.toHexString(new Long(orderNum)).toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(orderNumUtil());
	}
}
