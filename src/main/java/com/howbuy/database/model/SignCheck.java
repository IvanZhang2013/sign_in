package com.howbuy.database.model;

import java.io.Serializable;

public class SignCheck implements Serializable {

	private static final long serialVersionUID = 1L;

	private String signCode;

	private String custName;

	private String consName;

	private String section;

	private Integer actualNum;

	private Integer planNum;

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}

	@Override
	public String toString() {
		return "SignCheck [signCode=" + signCode + ", custName=" + custName + ", consName=" + consName + ", section="
				+ section + ", actualNum=" + actualNum + ", planNum=" + planNum + "]";
	}
	
}
