<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="public/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="public/css/login.css" />
<title>登录页面</title>

</head>
<body>

	<div class=" background"> 
		<h2><img src="public/images/school3.png"/>南京晓庄学院自动组卷系统</h2>
		<!-- 1.form表单没有action，提交到请求login.jsp的url上，即 /user/login
		2.form表单的属性commandName为user，因此model必须有一个key为user的对象-->
		<%-- <c:if test="${allErrors!=null}">
			<c:forEach items="${allErrors}" var="error">
				<font color="red">${error.defaultMessage}</font>
				<br />
			</c:forEach>
		</c:if> --%>
		
		<div class="login_form">
		<sf:form method="post" commandName="user">
			<div class="">
				<sf:label path="username" class="label_username" cssErrorClass="error">用户名</sf:label>
				<c:if test="${loginError!=null}">
					<label class="error">${loginError}</label>
				</c:if>
				<sf:errors cssClass="error" path="username" />
				<sf:input type="text" id="username" path="username"  class="input_username"/>
			</div>

			<div class="">
			<sf:label path="password" class="label_password" cssErrorClass="error">密码</sf:label>
			<sf:errors cssClass="error" path="password" />
				<sf:input type="password" path="password" class="input_password"/>
			</div>
			
			 <div class="">
      			<button type="submit" class="btn btn-primary">登录</button>
  			</div>
		</sf:form>
		</div>
	</div>
	<%-- <a href="login">登录</a>
	<a href='<s:url value="login"></s:url>'>登录s</a> --%>
</body>
</html>