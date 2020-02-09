package cn.itcast.erp.utils.format;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static final String formatTime(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date(time)); 
	}
	public static final String formatDateTime(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  df.format(new Date(time)); 
	}
	public static final Long formatDate(String sdate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long ldate = 0l;
		try {
			Date date = df.parse(sdate);
			ldate = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ldate;
	}
	
	public static final String formatMonmey(Double monmey) {
//		DecimalFormat df = new DecimalFormat("#.00");
//		return df.format(monmey);
		return String.format("%.2f", monmey);
	}
	
	public static void main(String[] args) {
		System.out.println(formatMonmey(0.5));
	}
}
