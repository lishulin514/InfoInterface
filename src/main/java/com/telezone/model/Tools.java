package com.telezone.model;

import org.apache.commons.lang3.StringUtils;


/**
 * @(#)Tools.java 
 *       
 * 系统名称：    
 * 版本号：      1.0
 *  
 *  Copyright (c)  All rights reserved 
 * 
 * 作者: 	  李树林
 * 创建日期:    2017年7月28日
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
public class Tools {


	/**
	 * 
	 * @param content
	 * @return 默认类型0 正整数返回1 拼 音返回2
	 */
	public static int typeOf(String content){
		if(isNumeric(content)){
			return 1;
		}else if(isLetter(content)){
			return 2;
		}
		return 0;
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str){
		int len = str.length();
		for (int i = 0; i < len; i++){
				if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isLetter(String str){
		int len = str.length();
		for (int i = 0; i < len; i++) {  
			if (!((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                    && (str.charAt(i) >= 'a' && str.charAt(i) <= 'z'))) {
				return false;  
            }  
        } 
		return true;
	}
	
	public static byte[] int2Bytes(int length, int val){
		byte[] b = new byte[length];
		for(int i = 0;i < length;i++){
			int bit = ((length-1)*8-i*8);
			int toByte = val>>bit;
			b[i]=(byte)toByte;
		}
		return b;
	}
	public static byte[] intTo2Bytes(int length, int val){
		byte[] b = new byte[2];
		if(length!=2){
			return b;
		}
		b[0]=(byte) (0x000000FF & val);
		b[1]=(byte) ((0x0000FF00 & val)>>8);
		return b;
	}
	
	public static int byte2Int(byte[] b) {
	
		int mask=0xff;
		int temp=0;
		int n=0;
		for(int i=0;i<b.length;i++){
			n<<=8;
			temp=b[i]&mask;
			n|=temp;
		}
	    return n;
	}
	
	public static String getStringByLimit(String src, Integer length){
		if(StringUtils.isEmpty(src)){
			return null;
		}
		
		byte[] bs = src.getBytes();
		if(bs.length > length){
			byte[] obj = new byte[length];
			System.arraycopy(bs, 0, obj, 0, length);
			return new String(obj);
		}
		return src;
	}
}
