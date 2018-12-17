package com.telezone.exception;
/**
 * @(#)CardIdArrTooLongException.java 
 *       
 * 系统名称：    
 * 版本号：      1.0
 *  
 *  Copyright (c)  All rights reserved 
 * 
 * 作者: 	  李树林
 * 创建日期:    2017年9月1日
 * 
 * 包名：com.telezone.framework.exceptions
 * 功能描述：
 * 公用方法描述：
 * 
 * 修改人：
 * 修改日期：
 * 修改原因：
 * 
 **/
public class CardIdArrTooLongException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145434342208758504L;

	public CardIdArrTooLongException() {
		super();
	}

	public CardIdArrTooLongException(String message) {
		super(message);
	}

}
