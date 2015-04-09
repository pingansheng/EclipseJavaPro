/*
 * ODBC���������ݿ�
 * 1����������Դ
 * 2����������Դ
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
			//1�������������ڴ�
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2���õ�����
			conn=DriverManager.getConnection("jdbc:odbc:my dsn","sa","654123");
			
			//3������statement����PreparedStatement
			//Statement���ڷ���sql���
			
			sm=conn.createStatement();
			
			//4��ִ��
			for (int j = 0; j < 1000; j++) {
				int i = sm
						.executeUpdate("insert into "
								+ "my_table (username,passwd) values('pingansheng','123456')");

				if (i == 1) {
					System.out.println("�ɹ�");
				} else {
					System.out.println("ʧ��");
				}
			}
			
			ResultSet rs=sm.executeQuery("select * from my_table");
			
			//�������� ������1��ʼ~~��������
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
