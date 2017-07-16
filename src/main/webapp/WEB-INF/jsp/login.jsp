<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<link type="text/css" rel="stylesheet" href="/css/list.css"></link>
<link type="text/css" rel="stylesheet" href="/css/index.css"></link>
<title>登录</title>
</head>
<body style="background-image: url('/image/background.jpg');">
	<script type="text/javascript">
		function submit() {
			var password = hex_md5($("#password").val());
			$.ajax({
				url : "/loginSubmit",
				type : "post",
				dataType : "json",
				data : {
					"password" : password
				},
				success : function(result) {
					if (result.type == "0") {
						window.location = "/"
					} else {
						alert("密码错误！")
					}
				}
			})
		}
	</script>
	<h1 style="margin: 20px auto;text-align:center;font-size: 50px;color: white;" >王佳移动专营店客户积分管理系统</h1>
	<div style="margin: 200px auto; width: 360px;text-align: center;background-color: white;border-radius: 20px;box-shadow: 3px 3px 5px 5px #4a9fdb;">
		<input type="password" id="password" name="password" style="text-align:center;font-size: 30px;padding: 0;margin: 30px;width: 300px" placeholder="密码"><br>
		<input type="button" class="query" value="登录" onclick="submit()" style="font-size: 20px;padding: 5px 20px;margin-bottom: 30px;">
	</div>
</body>
</html>