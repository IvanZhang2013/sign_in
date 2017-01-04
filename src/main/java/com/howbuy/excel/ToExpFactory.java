package com.howbuy.excel;

/**
 * 导出Excel工厂
 * */
public class ToExpFactory {
	private String execlType;

	public String getExeclType() {
		return execlType;
	}

	public void setExeclType(String execlType) {
		this.execlType = execlType;
	}

	public static ToExp getToExp(ExcelType em) throws Exception {
		Class<?> cl = Class.forName("com.howbuy.excel.Data2Excel");
		ToExp toexp = (ToExp) cl.newInstance();
		toexp.setExcelType(em);
		return toexp;
	}

}
