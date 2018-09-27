<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap4/css/bootstrap.css"/>
</head>
<body>
<a href="MainFrame.jsp">返回主界面</a>
<a href="LoginServlet.jsp">安全退出</a>
<h1>修改用户</h1>
<form action="/UsersManager4/User/UserClServlet?type=update" method="post">
<table class="table inline">
<tr><td>id</td> <td><input type="text" name="id" readonly="readonly" value="${userinfo.id}"></td></tr>
<tr><td>用户名</td> <td><input type="text" name="username" value="${userinfo.username}"></td></tr>
<tr><td>email</td> <td><input type="text" name="email" value="${userinfo.email}"></td></tr>
<tr><td>级别</td> <td><input type="text" name="grade" value="${userinfo.grade}"></td></tr>
<tr><td>密码</td> <td><input type="text" name="password" value="${userinfo.password}"></td></tr>
<tr><td><input type="submit" value="确认" ></td><td><input type="reset" value="重置" ></td> </tr>
</table>
<input type="hidden" value="update" name="flag">
</form>
</body>
</html>