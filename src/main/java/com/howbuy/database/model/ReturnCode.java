package com.howbuy.database.model;

public enum ReturnCode {
	success("0000","调用成功"),
	fail("9999","调用失败");
	
	public final String code;
	public final String message;
	
	private ReturnCode(String code ,String message){
		this.code =code;
		this.message =message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
