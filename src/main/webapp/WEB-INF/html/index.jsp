<html xmlns="http://www.w3.org/1999/xhtml" ng-app="user">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户登录</title>
</head>

<body ng-controller="loginController">
<script type="text/javascript" src="../js/angular/angular.js"></script>
<script type="text/javascript" src="../js/controller/loginController.js"></script>
<input type="text" id="userName" name="userName" ng-model="userName"/><br>
<input type="password" id="password" name="password" ng-model="password"/><br>
<input type="button" ng-click="useLogin()" value="登录" id="login"/>
<p>
    {{&scope.loginInfo}}
</p>
<%--<form action="/ssmmvn/user/login" method="post" id="myform">
    <input type="text" id="userName" name="userName"/>
    <input type="password" id="password" name="password"/>
    <input type="text" id="age" name="age"/>
    <input type="submit" value="提交" id="login" />
</form>--%>
<%--<a href="/ssmmvn/user/register">注册</a>--%>
</body>

</html>
