package com.telezone.model;

public class BS2CSType {

	public static final int DEFAULT_CALL_MINUTE = 60;
	
	public static final int ABANDON = -1;
	
	public static final int
	/**
	 * 记录总长度
	 */
			L_TOTAL = 2,
	/**
	 * 特征字
	 */
			L_TYPE = 2,
	/**
	 * 用户ID
	 */
							L_USERID = 20,
	/**
	 * 呼叫类型
	 */
							L_CALLTYPE = 1,
	/**
	 * 持续时间
	 */
							L_COUNTINUTE = 2,
	/**
	 * 呼叫分站数
	 */
							L_CARDREADER=1,
	/**
	 * 分站ID
	 */
							L_CARDREADERID = 2,
	/**
	 * 呼叫卡数
	 */
							L_CARDNO=1,
	/**
	 * 呼叫卡号
	 */
							L_CARDID = 2;
	

	
	public static final int TYPE_CALL_STOP = 0x00;
	
	public static final int TYPE_CALL_NORMAL = 0x20;
	
	public static final int TYPE_CALL_IMPORT = 0x40;
	
	public static final int TYPE_CALL_URGENCY = 0x60;
	
	public static int getCallType(int type){
		if(type==1){
			return TYPE_CALL_NORMAL;
		}else if(type==2){
			return TYPE_CALL_IMPORT;
		}else if(type==3){
			return TYPE_CALL_URGENCY;
		}else{
			return TYPE_CALL_STOP;
		}
	}
}
