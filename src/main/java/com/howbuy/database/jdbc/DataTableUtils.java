package com.howbuy.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.howbuy.database.model.SignCheck;

@Repository
public class DataTableUtils {

	@Autowired
	private SqlLiteJDBCUtils sqlLiteJDBCUtils;
	
	//根据签到验证码查询信息
	private final static String GET_SIGN_CODE ="SELECT SIGN_CODE,CUST_NAME,CONS_NAME,SECTION,ACTUAL_NUM,PLAN_NUM FROM SIGN_CHECK WHERE SIGN_CODE = ? ";
	//初始化插入数据表
	private final static String INSERT_SIGN_CODE ="INSERT INTO SIGN_CHECK (SIGN_CODE, CUST_NAME, CONS_NAME, SECTION, ACTUAL_NUM, PLAN_NUM, CREATE_DATE) VALUES (?, ?, ?, ?, ?, ?,DATETIME())  ";
	//更新实际人数
	private final static String UPDATE_SIGN_CHECK="UPDATE SIGN_CHECK SET ACTUAL_NUM = (ACTUAL_NUM+?)  ,UPDATE_DATE = DATETIME() WHERE SIGN_CODE = ? ";
	
	private final static String GET_ALL="SELECT SIGN_CODE,CUST_NAME,CONS_NAME,SECTION,ACTUAL_NUM,PLAN_NUM FROM SIGN_CHECK";
	//插入数据
	public boolean insert(SignCheck signCheck) throws SQLException {
		
		boolean flag =false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = sqlLiteJDBCUtils.getConn();
			ps = conn.prepareStatement(INSERT_SIGN_CODE);
			ps.setString(1, signCheck.getSignCode());
			ps.setString(2, signCheck.getCustName());
			ps.setString(3, signCheck.getCustName());
			ps.setString(4, signCheck.getSection());
			ps.setInt(5, signCheck.getActualNum()==null?0:signCheck.getActualNum());
			ps.setInt(6, signCheck.getPlanNum());
			flag =ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			sqlLiteJDBCUtils.closeConn(conn, ps);
		}
		return flag;
	}
	
	//根据验证码查询信息
	public SignCheck getSignCode(String signCode) throws SQLException {
		SignCheck signCheck = new SignCheck();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		try {
			conn = sqlLiteJDBCUtils.getConn();
			ps = conn.prepareStatement(GET_SIGN_CODE);
			ps.setString(1, signCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				signCheck.setSignCode(rs.getString(1));
				signCheck.setCustName(rs.getString(2));
				signCheck.setConsName(rs.getString(3));
				signCheck.setSection(rs.getString(4));
				signCheck.setActualNum(rs.getInt(5));
				signCheck.setPlanNum(rs.getInt(6));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			sqlLiteJDBCUtils.closeConn(conn, ps,rs);
		}
		return signCheck;
	}
	
	//更新签到人数
	public boolean updateSignCheck(String signCode ,Integer actualNum) throws SQLException{
		boolean flag =false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = sqlLiteJDBCUtils.getConn();
			ps = conn.prepareStatement(UPDATE_SIGN_CHECK);
			ps.setInt(1, actualNum);
			ps.setString(2, signCode);
			flag =ps.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			sqlLiteJDBCUtils.closeConn(conn, ps);
		}
		return flag;
	}
	
	//查询所有
	public List<SignCheck> getAll() throws SQLException {
		List<SignCheck>  list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		try {
			conn = sqlLiteJDBCUtils.getConn();
			ps = conn.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				SignCheck signCheck = new SignCheck();
				signCheck.setSignCode(rs.getString(1));
				signCheck.setCustName(rs.getString(2));
				signCheck.setConsName(rs.getString(3));
				signCheck.setSection(rs.getString(4));
				signCheck.setActualNum(rs.getInt(5));
				signCheck.setPlanNum(rs.getInt(6));
				list.add(signCheck);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			sqlLiteJDBCUtils.closeConn(conn, ps,rs);
		}
		return list;
	}

}
