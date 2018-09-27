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
<h1>管理用户</h1>
<table class="table inline">
<tr><th>用户id</th><th>用户名</th><th>邮件</th><th>级别</th>  </tr>
<%System.out.print("进来了"); %>
<c:forEach var="user" items="${userslist}">
<tr>
<td>${user.id}</td>
<td>${user.username}</td>
<td>${user.email}</td>
<td>${user.grade}</td>
<td><a onclick="return confirmOper()" href="/UsersManager4/User/UserClServlet?flag=del&id=${user.id}">删除</a> </td>
<td><a href="/UsersManager4/User/UserClServlet?flag=gotoUpdView&id=${user.id}">编辑</a> </td>
</tr>
</c:forEach>
</table>
<%
int i=1;
for(;i<=(int)request.getAttribute("pageCount");i++){
%>
<a href="ManagerUserServlet?pageNow=<%=i %>"><%=i %></a>
<% }%>
<br>
跳转到：<input type="text" id=go><button onclick='f()'>go</button>
<script type="text/javascript">
function f() {
	var pageNow = document.getElementById("go").value;
	if(!isNaN(pageNow)){
		location.href="/UsersManager4/ManagerUserServlet?pageNow="+pageNow+"#go";
	}
}
function confirmOper() {
	return confirm("确认删除");
}
</script>
</body>
</html>