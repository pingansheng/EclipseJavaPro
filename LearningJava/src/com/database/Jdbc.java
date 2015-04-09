/*
 * ¥øjdbc
 */
package com.database;
import java.sql.*;
public class Jdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PreparedStatement ps=null;
		Connection conn=null;
		ResultSet rs=null;
		
		try {
			//≥ı ºªØ
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1236;databaseName=test","sa","654123");
			ps=conn.prepareStatement("select * from my_table");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+rs.getString(2)+rs.getString(3));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				if (rs == null) {
					rs.close();
				}
				if (ps == null) {
					ps.close();
				}
				if (conn == null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
