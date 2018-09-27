package com.zqj.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zqj.pojo.User;
import com.zqj.util.SqlHelper;

public class UserService {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public boolean checkUser(User user) {
		System.out.println(user.getPassword()+"adfds");
		boolean b=false;
		String sql="select * from users where id=? and password=?";
		String parameters[]= {user.getId()+"",user.getPassword()};
		List<User> users=new ArrayList<User>();
		users=SqlHelper.executeQuery(sql, parameters);
		if(users.size()==1) {
			b=true;
		}
		
		return b;
	}
	public List getUsersByPage(int pageNow,int pageSize) {
		System.out.println(pageNow);
		System.out.println(pageSize);
		List<User> userslist=new ArrayList<User>();
		String sql="select * from users limit "+((pageNow-1)*pageSize)+","+pageSize;
		List<Object[]> list=SqlHelper.executeQuery(sql, null);
		for (Object[] objects : list) {
			User user=new User();
			user.setId((int) objects[0]);
			user.setUsername((String) objects[1]);
			user.setEmail((String) objects[2]);
			user.setGrade((int) objects[3]);
			user.setPassword((String) objects[4]);
			userslist.add(user);
		}
		return userslist;
	}
	public int getPageCount(int pageSize) {
		int rowCount=0;
		String sql="select count(*) from users";
		List usercount=new ArrayList();
		usercount=SqlHelper.executeQuery(sql, null);
		System.out.println((Object[])usercount.get(0));
		System.out.println(((Object[])usercount.get(0))[0].toString());
		System.out.println(Integer.parseInt(((Object[])usercount.get(0))[0].toString()));
		rowCount=Integer.parseInt(((Object[])usercount.get(0))[0].toString());
		System.out.println((rowCount-1)/pageSize+1);
		return (rowCount-1)/pageSize+1;
	}
	public boolean delUser(String id) {
		boolean b=true;
		String sql="delete from users where id=?";
		String parameters[]= {id};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
			// TODO: handle exception
		}
		return b;
	}
	public User getUserById(String id) {
		User user=new User(); 
		String sql="select * from users where id=?";
		String parameters[]= {id};
		List<User> users=new ArrayList<User>();
		List<Object[]> list=SqlHelper.executeQuery(sql, parameters);
		for (Object[] objects : list) {
			
			user.setId((int) objects[0]);
			user.setUsername((String) objects[1]);
			user.setEmail((String) objects[2]);
			user.setGrade((int) objects[3]);
			user.setPassword((String) objects[4]);
		}
		return user;
	}
	public boolean updUser(User user) {
		boolean b=true;
		String sql="update users set username=?,email=?,grade=?,password=? where id=?";
		String parameters[]= {user.getUsername(),user.getEmail(),user.getGrade()+"",user.getPassword(),user.getId()+""};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
			// TODO: handle exception
		}
		return b;
	}
	public boolean addUser(User user) {
		System.out.println(1234);
		boolean b=true;
		String sql="insert into users(username,email,grade,password) values(?,?,?,?)";
		String parameters[]= {user.getUsername(),user.getEmail(),user.getGrade()+"",user.getPassword()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
			// TODO: handle exception
		}
		return b;
	}
	public boolean checkUserEmail(User user) {
		boolean b=false;
		String sql="select * from users where email=?";
		String parameters[]= {user.getEmail()};
		List<User> users=new ArrayList<User>();
		users=SqlHelper.executeQuery(sql, parameters);
		if(users.size()==1) {
			b=true;
		}
		
		return b;
	}
}