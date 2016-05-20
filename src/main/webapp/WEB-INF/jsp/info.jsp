<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/commen.css" rel="stylesheet">

  <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/js/ueditor/ueditor.all.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-color: transparent;">
	<div id="div_table_main" class="panel panel-default"
		style="margin: 0px">
		<div id="div_table_heading" class="panel-heading"
			style="padding: 0px; margin: 0px">
			<ol class="breadcrumb" style="margin: 0px">
				<li><a href="#">管理中心首页</a></li>
			</ol>
		</div>
		<div id="div_table_body" class="panel-body" style="padding: 0px">
			<table class="table  table-hover table-condensed b-t text-sm"
				style="margin: 0px">
				<tbody id="table_body" style="padding: 0px;">
					<tr>
						<td align="right">系统名称：</td>
						<td align="left">网上商城系统</td>
						<td align="right">系统版本：</td>
						<td align="left">1.0Building</td>
					</tr>
					<tr>
						<td align="right">官方网站：</td>
						<td align="left">http://www.lustsheep.com</td>
						<td align="right">官方论坛：</td>
						<td align="left">http://bss.lustsheep.com</td>
					</tr>
					<tr>
						<td align="right"></td>
						<td align="left"></td>
						<td align="right"></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right">运行环境：</td>
						<td align="left">JAVA</td>
						<td align="right">操作系统：</td>
						<td align="left">Linux</td>
					</tr>
					<tr>
						<td align="right">运行容器：</td>
						<td align="left">Tomcat</td>
						<td align="right">存储机制：</td>
						<td align="left">mysql</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">上架商品：</td>
						<td align="left">0</td>
						<td align="right">下架商品：</td>
						<td align="left">0</td>
					</tr>
					<tr>
						<td align="right">报警商品：</td>
						<td align="left">0</td>
						<td align="right">缺货商品：</td>
						<td align="left">0</td>
					</tr>
					<tr>
						<td align="right">等待付款：</td>
						<td align="left">0</td>
						<td align="right">等待发货：</td>
						<td align="left">0</td>
					</tr>
					<tr>
						<td align="right">会员总数：</td>
						<td align="left">1</td>
						<td align="right">未读消息：</td>
						<td align="left">
							


						</td>
					</tr>



					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var docHeight = $(document).height();
			var pageHeight = $("#div_table_page").height();
			$("#div_table_main").height(docHeight - pageHeight - 2);

		});
	</script>
</body>
</html>
