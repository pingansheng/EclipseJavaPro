/*
 * preparedstatementԤ���� �����Ч�� ��ֹע��
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
			//1�������������ڴ�
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2���õ�����
			conn=DriverManager.getConnection("jdbc:odbc:my dsn","sa","654123");
			
			//3������statement����PreparedStatement
			//Statement���ڷ���sql���
			
			ps=conn.prepareStatement("select * from my_table where id=? and passwd=?");
			//������ֵ
			ps.setInt(1, 999);
			ps.setString(2, "123456");
			rs=ps.executeQuery();
			//�������� ������1��ʼ~~��������
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
