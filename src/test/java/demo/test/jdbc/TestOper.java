package demo.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.howbuy.AppStart;
import com.howbuy.database.jdbc.DataTableUtils;
import com.howbuy.database.jdbc.SqlLiteJDBCUtils;
import com.howbuy.database.model.SignCheck;
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringApplicationConfiguration(classes = AppStart.class) // 指定我们SpringBoot工程的Application启动类
public class TestOper {
	
	@Autowired
	private DataTableUtils dtaTableUtils;

	@Test
	public void test() throws ClassNotFoundException{
		try {
			SignCheck signCheck = dtaTableUtils.getSignCode("1001");
			System.out.println(signCheck.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
