<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="zh-cn" style="height: 100%;">
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
<body style="background: transparent;height: 100%">
	<div class="panel-group" id="accordion"
		style="margin: 0px; height: 100%;width: 100%">
		<div class="panel panel-default" style="margin: 0px; height: 100%">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-toggle="collapse"
						data-parent="#accordion">菜单</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" >
				<div class="panel-body" >
					<nav class="nav-primary" style="margin: 0px">
						<ul class="nav">
							<li><a href="/admin/user/list.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>用户管理</span>
							</a></li>
							<li><a href="/admin/navigation/list.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>导航管理</span>
							</a></li>
							<li><a href="/admin/article/list.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>文章管理</span>
							</a></li>
							<li><a href="list.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>商品属性</span>
							</a></li>
							<li><a href="list.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>规格管理</span>
							</a></li>
							<li><a href="Brand.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>品牌管理</span>
							</a></li>
							<li><a href="/admin/setting/show.html" target="frane_two"> <i
									class="icon-tasks"></i> <span>站点设置</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</body>
</html>
