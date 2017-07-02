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
<title>客户</title>
<style type="text/css">
.data td {
	text-align: center;
	border: 1px gray solid;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#commit").click(function() {
				var memberId = $("#memberId").val();
				var memberName = $("#memberName").val();
				var phoneNo = $("#phoneNo").val();
				var age = $("#age").val();
				if ($.trim(memberName) == "") {
					alert("请输入客户名！")
					$("#memberName").focus();
					return;
				}
				if ($.trim(phoneNo) == "") {
					alert("请输入客户手机号！")
					$("#phoneNo").focus();
					return;
				}
				if ($.trim(phoneNo).length != 11) {
					alert("客户手机号位数不正确！")
					$("#phoneNo").focus();
					return;
				}
				$.ajax({
					url : "/memberSave",
					method : "post",
					dataType : "json",
					data : {
						memberId : memberId,
						memberName : memberName,
						phoneNo : phoneNo,
						age : age
					},
					success : function(result) {
						alert(result.message);
						if (result.success == "true") {
							var phoneNo = $("#phoneNo").val();
							var memberName = $("#memberName").val();
							window.location = "/memberManager";
						} else {
							alert(result.message);
						}
					},
					error : function() {
						alert("添加客户发生未知错误！")
					}
				});
			});
		});
	</script>
	<div style="margin: 20px 0 0 20px;">
		<form action="/memberSave" method="post">
			<input type="hidden" name="memberId" id="memberId"
				value="${member.memberId}">
			<table style="width: 400px; margin: 200px auto;">
				<caption style="font-size: 30px;">
					<c:if test="${empty member}">新增客户</c:if>
					<c:if test="${not empty member}">修改客户信息</c:if>
				</caption>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td style="width: 100px; text-align: right; font-size: 20px;"><span
						style="color: red;">*</span>客户名:</td>
					<td style="width: 100px; text-align: left; font-size: 20px;"><input
						style="font-size: 20px;" name="memberName" maxlength="20"
						id="memberName" required="required" value="${member.memberName}"></input></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right; font-size: 20px;"><span
						style="color: red;">*</span>手机号:</td>
					<td style="width: 100px; text-align: left; font-size: 20px;"><input
						style="font-size: 20px;" name="phoneNo"
						onblur="$(this).val($(this).val().replace(/[^0-9]*/g, ''));"
						id="phoneNo" required="required" value="${member.phoneNo}"></input></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 100px; text-align: right; font-size: 20px;">年龄:</td>
					<td style="width: 100px; text-align: left; font-size: 20px;"><input
						style="font-size: 20px;" name="age" id="age" maxlength="4"
						onblur="$(this).val($(this).val().replace(/[^0-9]*/g, ''));"
						value="${member.age}"></input></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: left;"><input id="commit"
						type="button" title="提交" value="提交"
						style="font-size: 20px; border-radius: 16px" class="query"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>