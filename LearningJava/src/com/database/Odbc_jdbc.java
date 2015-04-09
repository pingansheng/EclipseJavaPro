/*
 * ODBC桥连接数据库
 * 1、配置数据源
 * 2、连接数据源
 */
package com.database;

import java.sql.*;

public class Odbc_jdbc {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Connection conn=null;
		Statement sm=null;
		try {
			//1、加载驱动至内存
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2、得到链接
			conn=DriverManager.getConnection("jdbc:odbc:my dsn","sa","654123");
			
			//3、创建statement或者PreparedStatement
			//Statement用于发送sql语句
			
			sm=conn.createStatement();
			
			//4、执行
			for (int j = 0; j < 1000; j++) {
				int i = sm
						.executeUpdate("insert into "
								+ "my_table (username,passwd) values('pingansheng','123456')");

				if (i == 1) {
					System.out.println("成功");
				} else {
					System.out.println("失败");
				}
			}
			
			ResultSet rs=sm.executeQuery("select * from my_table");
			
			//读出数据 索引从1开始~~！！！！
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		finally{
			try {
				if (sm!=null) {
					sm.close();
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
