package com.zqj.dao;

import java.sql.SQLException;

import com.zqj.pojo.User;

public interface UserDao {
	// 查询用户 -->登陆
	User selectUser(User user) throws SQLException;
	
	// 插入用户 -->注册
	int insertUser(User user) throws SQLException;
	
	// 查询用户名是否存在 -->验证用户名
	int selectUserByUsername(User user) throws SQLException;
	
}
