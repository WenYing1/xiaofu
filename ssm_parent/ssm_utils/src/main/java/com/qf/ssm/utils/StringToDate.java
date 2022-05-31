package com.qf.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

	public static String chage(Date date,String s) {
		SimpleDateFormat sdf =new SimpleDateFormat(s);
		String str =sdf.format(date);
		
		return str;
	}
}
