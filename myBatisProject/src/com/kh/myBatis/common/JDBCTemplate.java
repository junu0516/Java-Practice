package com.kh.myBatis.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	public Connection getConnection() {
		Connection con = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@198.121.57.34:1521:xe","userxe","passce");
			con.setAutoCommit(false);	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void close(Connection con) {
		
		try {
			if(con!=null && con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void comit(Connection con) {
		
		try {
			
			if(con != null && !con.isClosed()) {
				con.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection con) {
		
		try {
			
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt) {
		
		try {
			if(stmt!=null && stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void close(ResultSet rset) {
		
		try {
			if(rset != null && rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

}
