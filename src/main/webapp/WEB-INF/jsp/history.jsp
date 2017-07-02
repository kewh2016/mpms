<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
<title>积分变更记录查询</title>
<style type="text/css">
.data td {
	text-align: center;border: 1px gray solid;
}
</style>
</head>
<body>
	<div style="margin: 20px 0 0 20px;">
		<c:if test="${not empty member }">
			<table style="width: 600px; margin: 20px auto 0px;">
				<tr>
					<td style="width: 100px;text-align: right;">客户名:</td>
					<td style="width: 100px;text-align: left;">${member.memberName}</td>
				</tr>
				<tr>
					<td style="width: 100px;text-align: right;">手机号:</td>
					<td style="width: 100px;text-align: left;">${member.phoneNo}</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty historyList }">
			<table class="data" style="margin: 30px auto; min-width: 800px;border-collapse: collapse;">
				<tr>
					<th width="5%">序号</th>
					<th width="10%">类型</th>
					<th width="20%">变更时间</th>
					<th width="30%">变更理由</th>
				</tr>
				<c:forEach items="${historyList}" var="history" varStatus="statu">
					<tr>
						<td>${statu.count}</td>
						<td>${history.type}</td>
						<td><fmt:formatDate value="${history.changeTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${history.reason}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty historyList }">
			<span style="color: red;display: block; margin:20px auto;width: 250px;">当前客户不存在积分变更记录!</span>
		</c:if>
	</div>
</body>
</html>