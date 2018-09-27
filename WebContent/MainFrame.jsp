<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<script src="js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
	
</head>
<body>
<img alt="" src="img/lufei.jpg" width="100" height="100">
	<h1>欢迎登录</h1>
	${log}
		<a href='LoginServlet.jsp'>返回</a>
		<h3>请选择操作</h3>
		<a href='/UsersManager4/ManagerUserServlet?pageNow=1'>管理界面</a><br>
		<a href='/UsersManager4/User/UserClServlet?flag=gotoAddUser'>添加用户</a><br>
		<a href='LoginServlet.jsp'>查找用户</a><br>
		<a href='LoginServlet.jsp'>退出系统</a><br>
</body>
</html>