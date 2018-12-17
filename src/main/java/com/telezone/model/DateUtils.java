package com.telezone.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @(#)DateUtils.java 
 *       
 * 系统名称：    
 * 版本号：      1.0
 *  
 *  Copyright (c)  All rights reserved 
 * 
 * 作者: 	  李树林
 * 创建日期:    2017年6月30日
 * 
 * 包名：com.telezone.framework.utils
 * 功能描述：
 * 公用方法描述：
 * 
 * 修改人：
 * 修改日期：
 * 修改原因：
 * 
 **/
public class DateUtils {

	public static Date getNewTime(){
		
		return new Date();
	}
	
	public static String format(long timestamp, String  pattern){

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp));
	}
	public static String format(long timestamp){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(timestamp));
	}
	public static String format(Date time){
		if(time==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(time);
	}
	
	public static Date parseDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String double2Date(Double d){
		Date t=new Date(0);
		Calendar base = Calendar.getInstance();   
		base.set(1899, 11, 30, 0, 0, 0);
		base.add(Calendar.DATE, d.intValue());   
		base.add(Calendar.MILLISECOND,(int)((d % 1) * 24 * 60 * 60 * 1000));   
		t=base.getTime();
		return format(t);
	}
}
