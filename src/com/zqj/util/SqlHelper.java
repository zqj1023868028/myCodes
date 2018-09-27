package com.zqj.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlHelper {
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static CallableStatement cs=null;
	private static ResultSet rs=null;
	
	private static String url=null;
	private static String username=null;
	private static String driver= null;
	private static String password=null;
	
	private static Properties pp=null;
	private static InputStream fis=null;
	
	static {
		try {
			pp=new Properties();
			System.out.println("ok");
			fis=SqlHelper.class.getClassLoader().getResourceAsStream("/new.properties");
			pp.load(fis);
			System.out.println(pp.getProperty("url"));
			url=pp.getProperty("url");
			username=pp.getProperty("username");
			driver=pp.getProperty("driver");
			password=pp.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		try {
			con=DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static CallableStatement callPro2
	(String sql,String[] inparameters,Integer[] outparmeters) {
		try {
			con=getConnection();
			cs=con.prepareCall(sql);
			if(inparameters!=null) {
				for(int i=0;i<inparameters.length;i++) {
					cs.setObject(i+1, inparameters[i]);
				}
			}
			if(outparmeters!=null) {
				for(int i=0;i<outparmeters.length;i++) {
					cs.registerOutParameter(inparameters.length+1+i, 0);
				}
			}
			cs.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cs;
	}
	
	public static void callProl(String sql,String[] parameters) {
		
		try {
			con=getConnection();
			cs=con.prepareCall(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					cs.setObject(i+1, parameters[i]);
				}
				cs.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			close(rs,cs,con);
		}
	}
	public static List executeQuery(String sql,String[]parameters) {
		List list=new ArrayList();
		try {
			System.out.println(sql);
			con=getConnection();
			ps=con.prepareStatement(sql);
			if(parameters!=null&&!parameters.equals("")) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int count = metaData.getColumnCount();
			while(rs.next()) {
				Object[] objects=new Object[count];
				for(int i=1;i<=count;i++) {
					objects[i-1]=rs.getObject(i);
				}
				list.add(objects);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			SqlHelper.close(rs, ps, con);
		}
		return list;
	}
	public static void executeUpdate2(String sql[],String[][]parameters) {
		try {
			con=getConnection();
			con.setAutoCommit(false);
			for(int i=0;i<sql.length;i++) {
				if(parameters[i]!=null) {
					ps=con.prepareStatement(sql[i]);
					for(int j=0;j<parameters[i].length;j++) {
						ps.setString(j+1,parameters[i][j]);
					}
					ps.executeUpdate();
				}
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException();
		}finally {
			close(rs,ps,con);
		}
	}
	public static void executeUpdate(String sql,String[]parameters) {
		try {
			con=getConnection();
			ps=con.prepareStatement(sql);
				if(parameters!=null) {
					;
					for(int i=0;i<parameters.length;i++) {
						ps.setString(i+1,parameters[i]);
					}
					System.out.println(sql);
					ps.executeUpdate();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			close(rs,ps,con);
		}
	}
	public static void close(ResultSet rs,Statement ps,Connection con) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con=null;
		}
	}

	public static Connection getCon() {
		return con;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static CallableStatement getCs() {
		return cs;
	}

	public static ResultSet getRs() {
		return rs;
	}
	
	

}
