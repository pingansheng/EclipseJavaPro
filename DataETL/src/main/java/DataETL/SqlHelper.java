package DataETL;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

public class SqlHelper {
	// 定义变量
	private Connection ct = null;
	// 大多数情况下用preparedstatement替代statement
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// 连接数据库的参数
	private static String url = "";
	private static String username = "";
	private static String driver = "";
	private static String passwd = "";

	private static CallableStatement cs = null;

	public static CallableStatement getCs() {
		return cs;
	}

	private static Properties pp = null;
	private static InputStream fis = null;
	// 加载驱动，只需要一次，用静态代码块
	static {
		try {
			// 从dbinfo.properties
			pp = new Properties();
			//类加载器默认主目录SRC
			fis = SqlHelper.class.getClassLoader().getResourceAsStream(
					"dbInfo.properties");
			// fis = new FileInputStream();
			pp.load(fis);
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			driver = pp.getProperty("driver");
			passwd = pp.getProperty("passwd");

			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis = null;// 垃圾回收站上收拾
		}

	}

	// 得到连接
	public Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}

	// *************callPro1存储过程函数1*************
	public  CallableStatement callPro1(String sql, String[] parameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, cs, ct);
		}
		return cs;
	}

	// *******************callpro2存储过程2************************
	public CallableStatement callPro2(String sql, String[] inparameters,
			Integer[] outparameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}
			// cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
			if (outparameters != null) {
				for (int i = 0; i < outparameters.length; i++) {
					cs.registerOutParameter(inparameters.length + 1 + i,
							outparameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return cs;
	}

	public ArrayList<Object> executeQuery(String sql, Object... parameters) {
		Connection ct = null;
		// 大多数情况下用preparedstatement替代statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i].toString());
				}
			}
			rs = ps.executeQuery();
			
			ArrayList<Object> al=new ArrayList<Object>();
			ResultSetMetaData rsmd=rs.getMetaData();
			int columncount=rsmd.getColumnCount();
			
			while(rs.next())
			{
				Object obs[]=new Object[columncount];
				for(int i=0;i<columncount;i++)
				{
					obs[i]=rs.getObject(i+1);
				}
				al.add(obs);
			}
			return al;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源(先开后关)
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ps = null;
			}
			if (null != ct) {
				try {
					ct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ct = null;
			}
		}
	}

	public  Connection getCt() {
		return ct;
	}

	public  PreparedStatement getPs() {
		return ps;
	}

	public  ResultSet getRs() {
		return rs;
	}

	public  void executeUpdate2(String[] sql, Object[][] parameters) {
		try {
			ct = getConnection();
			ct.setAutoCommit(false);
			
			//设置隔离级别
			//ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			for (int i = 0; i < sql.length; i++) {

				if (null != parameters[i]) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setObject(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}

			}

			ct.commit();

		} catch (Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}

	}

	// 先写一个update、delete、insert
	// sql格式：update 表名 set 字段名 =？where 字段=？
	// parameter神应该是（”abc“,23）
	public  int executeUpdate(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}

			}
			return ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 可以处理，也可以不处理
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
	}

	public void close(ResultSet rs, Statement ps, Connection ct) {
		// 关闭资源(先开后关)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
		if (null != ct) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ct = null;
		}
	}
}
