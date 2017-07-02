<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
<title>客户管理</title>
<style type="text/css">
body{font-size: 24px;}
td ,th{text-align: center;border: 1px solid gray;height: 40px;}
</style>
</head>
<body>
	<div style="margin: 20px 0 0 20px;">
		<table style="margin: 30px auto; min-width: 800px;border: 1px gray solid;padding-bottom: 20px;border-collapse: collapse;">
			<caption style="padding: 10px 10px;">
				<a href="/memberEdit" class="query">新增客户</a>
			</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">客户名</th>
				<th width="20%">手机号码</th>
				<th width="10%">当前积分</th>
				<th width="40%">操作</th>
			</tr>
			<c:forEach items="${members}" var="mem" varStatus="statu">
				<tr>
					<td>${statu.count}</td>
					<td>${mem.memberName}</td>
					<td>${mem.phoneNo}</td>
					<td>${mem.points}</td>
					<td><a class="query"
						href="/viewHistory?memberId=${mem.memberId}">积分变更记录</a> <a
						class="query" href="/memberEdit?memberId=${mem.memberId}">修改客户信息</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>