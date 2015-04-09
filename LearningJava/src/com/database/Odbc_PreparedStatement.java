/*
 * preparedstatement预编译 可提高效率 防止注入
 */
package com.database;

import java.sql.*;

public class Odbc_PreparedStatement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1、加载驱动至内存
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2、得到链接
			conn=DriverManager.getConnection("jdbc:odbc:my dsn","sa","654123");
			
			//3、创建statement或者PreparedStatement
			//Statement用于发送sql语句
			
			ps=conn.prepareStatement("select * from my_table where id=? and passwd=?");
			//给？赋值
			ps.setInt(1, 999);
			ps.setString(2, "123456");
			rs=ps.executeQuery();
			//读出数据 索引从1开始~~！！！！
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3));
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			try {
				if (ps!=null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
