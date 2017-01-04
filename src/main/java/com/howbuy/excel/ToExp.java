package com.howbuy.excel;

public interface ToExp {
	
	public void excute(ExpExcelTemplate exp, Object obj) throws Exception;
	
	public void setExcelType(ExcelType em) throws Exception;
	
}
