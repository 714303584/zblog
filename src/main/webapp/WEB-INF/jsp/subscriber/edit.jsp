<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>Bootstrap 101 Template</title>


<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script
	src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/commen.css" rel="stylesheet">
<link
	href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css"
	rel="stylesheet">

<script type="text/javascript" charset="utf-8"
	src="/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/js/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="/js/ueditor/lang/zh-cn/zh-cn.js"></script>


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background: transparent;">
	<div id="div_table_main" class="panel panel-default"
		style="margin: 0px">
		<div id="div_table_heading" class="panel-heading"
			style="padding: 0px; margin: 0px">
			<ol class="breadcrumb" style="margin: 0px">
				<li><a href="list.html">订阅管理</a></li>
				<li><a href="add.html">订阅编辑</a></li>
			</ol>
		</div>
		<div id="div_add_body" class="panel-body" style="padding: 0px">
			<div id="tab_body" class="tab-content" style="width: 100%">
				<div class="tab-pane active" id="div_info"
					style="overflow: scroll; width: 100%;">
					<div>.</div>
					<form id="commentForm" class="form-horizontal" role="form"
						method="post" action="/admin/subscriber/edit/">
						
						<div class="form-group" style="width: 100%;display: none;">
							<label for="inputEmail3" class="col-sm-2 control-label">id：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="id"
									style="width: 200px" value="${subscriber.id}">
							</div>
						</div>
							<div class="form-group" style="width: 100%;">
							<label for="inputEmail3" class="col-sm-2 control-label">邮箱：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="email"
									style="width: 200px" value="${subscriber.email}">
							</div>
						</div>
						
						<div class="form-group" style="width: 100%">
							<label for="inputPassword3" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<button class="btn primary">提交</button>
								<button type="button" class="btn primary"
									href="/admin/user/userList.html">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var docHeight = $(window).height();
			var docWidth = $(window).width();
			var headHeight = $("#div_table_heading").height();
			var tabHeadHei = $("#ul_tab_head").height();
			$("#div_table_main").height(docHeight);
			$("#tab_body").height(docHeight-headHeight-tabHeadHei);
			$("#div_info").height(docHeight-headHeight-tabHeadHei);
			
			
			
		});
		  //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	</script>
</body>
</html>
