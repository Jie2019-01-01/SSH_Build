package cn.itcast.erp.utils.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static final String formatDate(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return  df.format(new Date(time)); 
	}
	public static final String formatTime(Long time){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final String formatDateTime(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	
	public static void main(String[] args) throws Exception{
			// long ==> String
		System.out.println(formatDate(774979200000l));
			// Date ==> long 
		String sDate = "1994-07-24";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(sDate);
		long lDate = date.getTime();
		System.out.println(lDate);
	}
}
