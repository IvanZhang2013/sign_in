package demo.test.jdbc;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.AppStart;
import com.howbuy.database.jdbc.DataTableUtils;
import com.howbuy.database.model.ReturnCode;
import com.howbuy.excel.ExcelType;
import com.howbuy.excel.ExpExcelTemplate;
import com.howbuy.excel.ToExpFactory;

import demo.test.BaseTests;

public class TestExcel extends BaseTests{
	
	@Value("${filepath}")
	private String filePath;

	@Autowired
	private DataTableUtils dataTableUtils;
	
	@Test
	public void test(){
		ExpExcelTemplate exp = new ExpExcelTemplate();
		exp.setObjectName("signChecks");
		exp.setTempNm("exp_excel_tmp");
		exp.setTempPath(filePath);
		exp.setEntityPath(filePath);
		try {
			ToExpFactory.getToExp(ExcelType.EXCEL_XLS).excute(exp, dataTableUtils.getAll());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
