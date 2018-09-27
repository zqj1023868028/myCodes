<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap4/css/bootstrap.css"/>
	<script src="js/jquery-3.3.1.js" type="text/javascript" charset="utf-8">
		</script>
		<script type="text/javascript">
		
					$(function(){
						var id=$("#id").val();
						var password=$("#password").val();
						if("${cookie.iskeepinfo.value}"=="true"){
							document.getElementById("iskeepinfo").checked=true;
						}else{
							document.getElementById("iskeepinfo").checked=false;
						}
						if("${cookie.autoLogin.value}"=="true"){
							document.getElementById("autoLogin").checked=true;
						window.location.href="http://localhost:8080/UsersManager4/LoginClServlet?id="+id+"&password="+password+"&autoLogin=true";
						}else{
							document.getElementById("autoLogin").checked=false;
						} 
					})
		</script>
</head>

<body>
<img alt="" src="img/lufei.jpg" width="100" height="100">
<hr>
<%=new Date() %>
<h1>用户登录</h1>
<h5>当前访问人数：${usernum }</h5>
		<form action='LoginClServlet' method="get" class="form">
		<div><label class="form-group">用户id</label><input type='text' name='id' id='id' value="${cookie.id.value}"></div>
		<div><label class="form-group">密码</label><input type='text' name='password' id="password" value="${cookie.password.value }"></div>
		<div><label class="form-group">验证码</label><input type="text" name='checkcode'><img  src="/UsersManager4/CreateCode"></div>
		<input type='submit' value='登录' class="btn btn-danger"></br>
		<input type="checkbox" name="iskeepinfo" id="iskeepinfo" value="keep">保存
		<input type="checkbox" name="autoLogin" id="autoLogin"  value="checked" >  <!-- value="checked"   checked="" -->自动登录
		<a href=''>注册</a>
		</form>
		<a href='LoadServlet?filename=night.jpg'>go</a>
		<c:if test="${err!=null}">
		${err}
		</c:if>
		<hr>
		<img alt="" src="img/night.jpg" width="200" height="50">

</body>
</html>