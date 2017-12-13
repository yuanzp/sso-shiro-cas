<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<h1>hello node1</h1>
    角色
	<shiro:hasRole name="管理员">
		<h2>管理员</h2>
	</shiro:hasRole>
    <shiro:hasRole name="普通">
        <h2>普通</h2>
    </shiro:hasRole>
    权限
    <shiro:hasPermission name="查询">
        <h2>查询</h2>
    </shiro:hasPermission>
	<shiro:hasPermission name="管理">
		<h2>管理</h2>
	</shiro:hasPermission>
    <shiro:user>
        <h2>欢迎[<shiro:principal/>]登录</h2>
    </shiro:user>
	<a href="http://127.0.0.1:8080/node1/shiro-cas">节点1</a>
	
	<a href="http://127.0.0.1:8080/node2/shiro-cas">节点2</a>

    <a href="http://127.0.0.1:8080/node1/permission/admin">管理权限 可访问</a>
    <a href="http://127.0.0.1:8080/node1/permission/common">查询权限 可访问</a>
    <a href="http://127.0.0.1:8080/node1/permission/self">不需权限 可访问</a>

	<a href="http://127.0.0.1:8080/node1/logout">单点登出</a>
</body>
</html>