<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
<style type="text/css">
td, th {
	text-align: center;
	border: 1px solid gray;
	height: 40px;
}
</style>
</head>
<body style="font-size: 24px;">
	<div style="margin: 20px 0 0 20px;">
		<table
			style="margin: 30px auto; min-width: 800px; border: 1px gray solid; padding-bottom: 20px; border-collapse: collapse;">
			<caption style="padding: 10px 10px;">
				<a class="query" href="/addProduct">增加产品</a>
			</caption>
			<tr>
				<th>产品编号</th>
				<th>产品品牌</th>
				<th>产品名称</th>
				<th>产品型号</th>
				<th>产品价格(元)</th>
				<th>产品积分</th>
				<th>产品库存</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${products}" var="pro">
				<tr>
					<td>${pro.id }</td>
					<td>${pro.brand }</td>
					<td>${pro.name }</td>
					<td>${pro.model }</td>
					<td>${pro.price }</td>
					<td>${pro.point }</td>
					<td>${pro.stock }</td>
					<td><a class="query" href="/editProduct?id=${pro.id }">修改产品信息</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>