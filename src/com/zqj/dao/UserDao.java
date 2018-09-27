package com.zqj.dao;

import java.sql.SQLException;

import com.zqj.pojo.User;

public interface UserDao {
	// ��ѯ�û� -->��½
	User selectUser(User user) throws SQLException;
	
	// �����û� -->ע��
	int insertUser(User user) throws SQLException;
	
	// ��ѯ�û����Ƿ���� -->��֤�û���
	int selectUserByUsername(User user) throws SQLException;
	
}
