<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加产品</title>
<style type="text/css">
.title {
	text-align: right;
}

.error {
	color: red;
}

.query {
	color: #434F5B;
	border-radius: 20px;
	text-decoration: none;
	border: 1px #accef9 solid;
	background-color: #d9e4ee;
	padding: 2px 20px;
	font-size: 20px
}
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#addProductForm").validate({
				rules : {
					brand : "required",
					name : "required",
					model : "required",
					stock : {
						required : true,
						digits : true
					},
					price : {
						required : true,
						number : true,
						min : 0
					},
					point : {
						required : true,
						digits : true
					}
				},
				messages : {
					brand : {
						required : "请输入产品品牌"
					},
					name : "请输入产品名称",
					model : "请输入产品型号",
					stock : {
						required : "请输入库存",
						digits : "库存必须是非负整数"
					},
					price : {
						required : "请输入价格",
						number : "价格必须是正数",
						min : "价格必须是正数"
					},
					point : {
						required : "请输入可积积分",
						digits : "积分必须是非负整数"
					}
				}
			})
		})
	</script>
	<div style="margin: 20px 0 0 20px; font-size: 20px;">
		<form id="addProductForm" action="addProductCommit" method="post">
			<input type="hidden" name="id" value="${pro.id }">
			<table style="width: 600px; margin: 200px auto;">
				<caption style="font-size: 30px;">
					<c:if test="${empty pro}">新增产品</c:if>
					<c:if test="${not empty pro}">修改产品信息</c:if>
				</caption>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>产品品牌：</td>
					<td><input name="brand" value="${pro.brand }"
						required="required"></td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>产品名称：</td>
					<td><input name="name" value="${pro.name }"
						required="required"></td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>产品型号：</td>
					<td><input name="model" value="${ pro.model}"
						required="required"></td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>产品库存：</td>
					<td><input name="stock" type="number" value="${pro.stock }"
						required="required"></td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>产品价格：</td>
					<td><input name="price" type="number" value="${pro.price }"
						required="required"></td>
				</tr>
				<tr>
					<td class="title"><span style="color: red;">*</span>可积积分：</td>
					<td><input name="point" type="number" value="${pro.point }"
						required="required"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="提交" class="query"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>