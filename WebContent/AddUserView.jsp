<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap4/css/bootstrap.css"/>
		<script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
		<script type="text/javascript">
	$(function(){
		$("#email").blur(function(){
			var email = $(this).val();
			$.ajax({
				type:"post",
				url:"UserClServlet",
				datatype:"text",
				data:{
					"email" : email,
				},
				success:function(result){
					$("#d1").html(result);
				}
			});
		});
	})
</script>
		
</head>
<body>
<a href="MainFrame.jsp">返回主界面</a>
<a href="LoginServlet.jsp">安全退出</a>
<h1>添加用户</h1>
<form action="/UsersManager4/User/UserClServlet?flag=add" method="get">
<table class="table inline">
<tr><td>用户名</td> <td><input type="text" name="username"></td></tr>
<tr><td>email</td> <td><input type="text" name="email" id="email" value=""></td><td><span id="d1" ></span></td> </tr>
<tr><td>级别</td> <td><input type="text" name="grade"></td></tr>
<tr><td>密码</td> <td><input type="text" name="password"></td></tr>
<tr><td><input type="submit" value="确认" ></td><td><input type="reset" value="重置" ></td> </tr>
</table>
<input type="hidden" value="add" name="flag">
</form>
</body>
</html>