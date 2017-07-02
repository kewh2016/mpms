<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员积分管理系统</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="/css/index.css"></link>
</head>
<body>
	<div>
		<header>
			<span style="padding-left: 40px;">王佳移动专营店客户积分管理系统</span>
		</header>
		<div class="menu">
			<ul>
				<li><a href="/list" target="main">积分管理</a></li>
				<li><a href="/memberManager" target="main">客户管理</a></li>
				<li><a href="/productManage" target="main">产品管理</a></li>
				<li><a href="/changePassword">修改密码</a></li>
				<li><a href="/loginOut">注销</a></li>
			</ul>
		</div>
		<iframe id="main" name="main" src="/list"></iframe>
	</div>
	<script type="text/javascript">
		/* <![CDATA[ */
		$(document).ready(function() {
			setSize();
			$(window).resize(function() {
				setSize();
			})
			function setSize() {
				var windowHeight = $(window).height();
				var windowWidth = $(window).width();
				var headerHeight = $("header").height();
				$(".menu").height(windowHeight - headerHeight);
				$("#main").height(windowHeight - headerHeight);
				$("#main").width(windowWidth - $(".menu").width() - 4);
			}
		});
		/* ]]> */
	</script>
</body>
</html>