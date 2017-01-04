package com.howbuy.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.howbuy.database.jdbc.DataTableUtils;
import com.howbuy.database.model.ReturnCode;
import com.howbuy.database.model.SignCheck;
import com.howbuy.excel.ExcelType;
import com.howbuy.excel.ExcelUtils;
import com.howbuy.excel.ExpExcelTemplate;
import com.howbuy.excel.ToExpFactory;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private DataTableUtils dataTableUtils;
	@Autowired
	private ExcelUtils excelUtils;
	@Value("${filepath}")
	private String filePath;

	/**
	 * 签到页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping("index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("/pages/index");
	}

	/**
	 * 签到页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping("index/query")
	@ResponseBody
	public SignCheck select(@RequestParam(name = "signCode", required = true) String signCode) {
		SignCheck signCheck = null;
		try {
			signCheck = dataTableUtils.getSignCode(signCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return signCheck;
	}

	/**
	 * 签到页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping("index/update")
	@ResponseBody
	public ReturnCode update(@RequestParam(name = "signCode", required = true) String signCode,
			@RequestParam(name = "actualNum", required = false) Integer actualNum) {
		if (actualNum == 0) {
			actualNum = 1;
		}
		try {
			dataTableUtils.updateSignCheck(signCode, actualNum);
		} catch (SQLException e) {
			e.printStackTrace();
			return ReturnCode.fail;
		}
		return ReturnCode.success;
	}

	/**
	 * 管理页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping("manager")
	public ModelAndView manager(HttpServletRequest httpServletRequest) {
		return new ModelAndView("/pages/manager");
	}

	/**
	 * 初始化数据
	 * 
	 * @throws Exception
	 */
	@RequestMapping("manager/initTable")
	@ResponseBody
	public ReturnCode initTable(HttpServletRequest httpServletRequest) {
		try {
			excelUtils.initTable(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnCode.fail;
		}
		return ReturnCode.success;
	}

	/**
	 * 生成excel文件
	 * 
	 * @throws Exception
	 */
	@RequestMapping("manager/expExcel")
	@ResponseBody
	public ReturnCode expTable() {
		ExpExcelTemplate exp = new ExpExcelTemplate();
		exp.setObjectName("signChecks");
		exp.setTempNm("exp_excel_tmp");
		exp.setTempPath(filePath);
		exp.setEntityPath(filePath);
		try {
			ToExpFactory.getToExp(ExcelType.EXCEL_XLS).excute(exp, dataTableUtils.getAll());
		} catch (SQLException e) {
			e.printStackTrace();
			return ReturnCode.fail;
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnCode.fail;
		}
		return ReturnCode.success;
	}

}
