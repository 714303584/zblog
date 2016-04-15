<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-image: url('img/mooning.png');">
	<div id="div_top"  class="col-md-12 panel" style="height: 100px; padding: 4px; margin: 0px">
		<table style="height: 100%">
			<tbody style="height: 100%">
			<tr>
				<th style="width: 20%; padding: 0px">
					<a href="#none">
						<img alt="主页" height="100px" src="/img/2012102918455786548.png">
					</a>
				</th>
				<th style="width: 50%;padding: 0px">
					<div>
						<div align="right">
						<a href="/userMenu.html" target="iframe_menu">
							<img alt="用户管理" width="60px" src="/img/facebook.png">
						</a>
						<a href="#none">
							<img alt="商品管理" width="60px" src="/img/facebook.png">
						</a>
					</div>
					</div>
				</th>
				<th style="width: 30%">
					<div>111111</div>
					<div>2222222</div>
				</th>
			</tr>
		</tbody>
	
		</table>
	</div>
	<div id="div_body" class="container-fluid"
		style="height: 100%; padding-top: 0px">
		<div class="row" style="padding-top: 0px; padding-bottom: 0px;">
			<div class="col-md-2" style="padding: 0px;">
				<div id="iframe_1" style="padding: 0px; height: 100%">
					<iframe id="iframe_left" name="iframe_menu" style="width: 100%;border: 0px" src="/admin/left.html">
						
					</iframe>
				</div>
			</div>
			<div class="col-md-10"
				style="padding: 0px; background-color: transparent; margin: 0px;">
				<div id="div_2" style="padding-right: 0px">
					<iframe id="iframe_2" name="frane_two"
						style="width: 100%; height: 100%; border: 0px; background-color: transparent;"
						scrolling="no" src="/admin/info.html"> </iframe>
				</div>
			</div>
		</div>
	</div>
	<div align="center" class="panel" style="margin: 0px;height: 30px;border-top: 1px">
		<p style="margin: 0px">COPYRIGHT © 2005-2013 LUSTSHEEP.COM ALL RIGHTS RESERVED.</p>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var body_height = ($(document).height() - $("div_top").height());
			$("#iframe_1").height(body_height - 130);
			$("#div_2").height(body_height - 130);
			$("#iframe_2").height(body_height - 130);
			$("#iframe_left").height(body_height - 130);
			
		});
		
	
	</script>
</body>
</html>
