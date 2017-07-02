<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
</head>
<body style="background-image: url('/image/background.png');">
	<script type="text/javascript">
		function submit() {
			var oldPassword = hex_md5($("#oldPassword").val());
			var newPassword = hex_md5($("#newPassword").val());
			var reNewPassword = hex_md5($("#reNewPassword").val());
			if($("#oldPassword").val() == ""){
				alert("请输入原密码！")
				return false;
			}
			if($("#newPassword").val() == ""){
				alert("请输入新密码！")
				return false;
			}
			if($("#reNewPassword").val() == ""){
				alert("请再次输入密码！")
				return false;
			}
			$.ajax({
				url : "changePasswordSubmit",
				type : "post",
				dataType : "json",
				data : {
					"oldPassword" : oldPassword,
					"newPassword" : newPassword,
					"reNewPassword" : reNewPassword
				},
				success : function(result) {
					alert(result.message);
					if (result.type != "1") {
						window.location = "/login"
					}
				}
			})
		}
	</script>
	<h1 style="margin: 20px auto; text-align: center; font-size: 50px; color: white;">王佳移动专营店客户积分管理系统</h1>
	<div style="margin: 200px auto; width: 400px; text-align: center; background-color: white; border-radius: 20px; box-shadow: 3px 3px 5px 5px #4a9fdb;">
		<table style="margin: 0 auto; padding: 40px;">
			<tr>
				<td colspan="2" style="font-size: 30px; font-weight: bold;">修改密码</td>
			</tr>
			<tr>
				<td style="text-align: right;">原密码：</td>
				<td><input type="password" name="oldPassword" id="oldPassword"
					required="required"></td>
			</tr>
			<tr>
				<td style="text-align: right;">新密码：</td>
				<td><input type="password" name="newPassword" id="newPassword"
					required="required"></td>
			</tr>
			<tr>
				<td style="text-align: right;">重复新密码：</td>
				<td><input type="password" name="reNewPassword"
					id="reNewPassword" required="required"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="提交" class="query"
					style="font-size: 20px; padding: 5px 20px;" onclick="submit()"><input type="button" value="取消" class="query"
					style="font-size: 20px; padding: 5px 20px;margin-left: 20px" onclick="window.location='/'"></td>
			</tr>
		</table>
	</div>
</body>
</html>