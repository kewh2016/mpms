<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员列表</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#query").click(function() {
				var phoneNo = $("#phoneNo").val();
				var memberName = $("#memberName").val();
				post("/list", {
					"phoneNo" : phoneNo,
					"memberName" : memberName
				});
			});
		});

		function pointChange(memberId) {
			$("#memberId").val(memberId);
			$("#pointFlag").val(-1);
			$("#changePointInput").val("");
			$("#changePointReasonInput").val("");
			$(".title").text("请输入扣减的分值");
			$("#reduce").removeClass("hidden");
		}

		function pointAdd(memberId) {
			$("#memberIdAdd").val(memberId);
			$("#addPointForm").removeClass("hidden");
		}

		function submit() {
			$.ajax({
				url : "/pointChange",
				method : "post",
				dataType : "json",
				data : {
					pointFlag : $("#pointFlag").val(),
					memberId : $("#memberId").val(),
					points : $("#changePointInput").val(),
					reason : $("#changePointReasonInput").val()
				},
				success : function(result) {
					alert(result.message);
					if (result.success == "true") {
						var phoneNo = $("#phoneNo").val();
						var memberName = $("#memberName").val();
						post("/list", {
							"phoneNo" : phoneNo,
							"memberName" : memberName
						});
					} else {
						$("#changePointInput").val("");
						$("#changePointInput").focus();
					}
				}
			})
		}

		function submitAdd() {
			$.ajax({
				url : "/pointChange",
				method : "post",
				dataType : "json",
				data : {
					pointFlag : "1",
					productId : $("#productSelect option:selected").val(),
					memberId : $("#memberIdAdd").val(),
					reason : $("#productSelect option:selected").text()
				},
				success : function(result) {
					alert(result.message);
					if (result.success == "true") {
						var phoneNo = $("#phoneNo").val();
						var memberName = $("#memberName").val();
						post("/list", {
							"phoneNo" : phoneNo,
							"memberName" : memberName
						});
					} else {
						alert("发生未知错误！")
					}
				}
			})
		}
	</script>
	<div class="body">
		<div class="queryDiv">
			<table class="queryTable">
				<tr>
					<td><span style="width: 40px; display: inline-block;">&nbsp;</span></td>
					<td>客户名：<input id="memberName" name="memberName"
						value="${member.memberName}"></input></td>
					<td><span style="width: 10px; display: inline-block;">&nbsp;</span></td>
					<td>手机号码：<input id="phoneNo" name="phoneNo"
						value="${member.phoneNo}"></input></td>
					<td><span style="width: 10px; display: inline-block;">&nbsp;</span></td>
					<td><a href="#" id="query" class="query">查询</a></td>
				</tr>
			</table>
		</div>
		<div>
			<table class="table">
				<tr>
					<th width="5%">序号</th>
					<th width="20%">客户名</th>
					<th width="20%">手机号码</th>
					<th width="10%">当前积分</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach items="${members}" var="mem" varStatus="statu">
					<tr>
						<td>${statu.count}</td>
						<td>${mem.memberName}</td>
						<td>${mem.phoneNo}</td>
						<td>${mem.points}</td>
						<td><a class="query" href="#"
							onclick="pointAdd('${mem.memberId}')">积分增加</a> <a class="query"
							href="#" onclick="pointChange('${mem.memberId}')">积分扣减</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="cover hidden" id="addPointForm">
		<div class="table">
			<div style="text-align: center;">请选择产品</div>
			<input type="hidden" id="memberIdAdd">
			<select
				style="width: 200px;font-size:15px; margin-left: 20px" id="productSelect">
				<c:forEach items="${products}" var="pro">
					<option value="${pro.id}">${pro.brand}${pro.name}${pro.model}积${pro.point}分</option>
				</c:forEach>
			</select> <a class="query" href="#" onclick="submitAdd()">确定</a> <a
				class="query" href="#" onclick="$('.cover').addClass('hidden')">取消</a>
		</div>
	</div>
	<div class="cover hidden" id="reduce">
		<div class="table">
			<input type="hidden" id="memberId"> <input type="hidden"
				id="pointFlag">
			<table>
				<caption class="title">&nbsp;</caption>
				<tr>
					<td style="width: 110px; text-align: right;"><span
						style="color: red;">*</span>变动分值：</td>
					<td><input required="required" type="number" id="changePointInput"
						onblur="$(this).val($(this).val().replace(/[^0-9]*/g, ''));"
						style="width: 229px;"></td>
				</tr>
				<tr>
					<td style="width: 110px; text-align: right;">变动原因：</td>
					<td><textarea id="changePointReasonInput" maxlength="200"
							rows="5" cols="30" style="resize: none;"></textarea></td>
				</tr>
				<tr>
					<td style="width: 110px; text-align: right;"></td>
					<td style="text-align: right;"><a class="query" href="#"
						onclick="submit()">确定</a> <a class="query" href="#"
						onclick="$('.cover').addClass('hidden')">取消</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>