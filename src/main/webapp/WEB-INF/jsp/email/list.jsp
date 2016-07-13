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

<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/commen.css" rel="stylesheet">

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
				<li><a href="/admin/email/list.html">邮件管理</a></li>
				<li><a href="/admin/email/list.html">已发邮件列表</a></li>
			</ol>
		</div>
		<div id="div_table_controller" class="center" style="padding: 4px;">
			<a class="btn btn-primary btn-xs" href="/admin/email/add.html"
				target="frane_two">发送</a> <a id="deleteByIds" class="btn btn-primary btn-xs">删除</a>
		</div>
		
		<div style="display: none;">
			<form id="deleteForm" action="/admin/navigation/deleteByIds" method="post">
			
			
			</form>
		</div>
		
		
		<div id="div_table_body" class="panel-body" style="padding: 0px;">
			<div id="table_databody">
				<table class="table  table-hover table-condensed b-t text-sm"
					style="margin: 0px">
					<thead style="padding: 0px;">
						<tr>
							<th><input type="checkbox"  id="invertSelection"  name="post[]" value="2">标识</th>
							<th class="th-sortable" data-toggle="class">名称 <span
								class="th-sort"> <i class="icon-sort-down text"></i> <i
									class="icon-sort-up text-active"></i> <i class="icon-sort"></i>
							</span>
							</th>
							<th>类型</th>
							<th>父级</th>
							<th>排序</th>
							<th>控制</th>
						</tr>
					</thead>
					<tbody id="table_body" style="padding: 0px;">

						<c:forEach var="navigation" items="${pages.elements}">
							<tr>
								<td><input type="checkbox"name="post[]" value="${navigation.id}">${navigation.id}</td>
								<td>${navigation.name }</td>
								<td>
								<c:choose>
								   <c:when test="${navigation.type == 1 }">   
								   	菜单 
								   </c:when>   
								   <c:otherwise>  
								   链接
								   </c:otherwise>  
								</c:choose>
								</td>
								<td>${navigation.parentname}</td>
								<td>${navigation.orders }</td>
								
								<td><a href="/admin/navigation/show.html?id=${navigation.id}" class="btn btn-primary btn-xs">编辑</a> <a
									href="/admin/navigation/delete/${navigation.id}"
									class="btn btn-primary btn-xs">删除</a></td>

							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

	</div>
	<div id="div_table_page" class="panel canelMargin" style="border: 0px">
		<div class="panel-body canelPadding" >
			<ul class="pagination pagination-sm canelMargin canelPadding">
			
				<li><a href="/admin/navigation/list.html?page=1">第一页</a></li>
			
				<c:forEach var="item" varStatus="status" begin="1" end="${pages.pageCount}">
					<li><a href="/admin/navigation/list.html?page=${item}"> ${item}</a> </li>
				</c:forEach>
				
				<li><a href="/admin/navigation/list.html?page=${pages.pageCount }">下一页</a></li>
			</ul>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			var docHeight = $(document).height();
			var pageHeight = $("#div_table_page").height();
			var tablemainH  = docHeight - pageHeight - 2;
			$("#div_table_main").height(tablemainH);
			
			
			var table_hei =  $("#div_table_body").height();
			var table_headH = $("#table_head").height();
			$("#table_databody").height(table_hei - table_headH);
			
			
			
			
			$("#invertSelection").click(function () {
				var checks  = $("#table_body").find("input");
				$.each(checks,function (){
					var isChecked =  $(this).prop("checked");
					$(this).prop("checked",!isChecked);
				});
			});
			
			$("#deleteByIds").click(function () {
				var checks  = $("#table_body").find("input");
				
				var myids = new Array();
				$.each(checks,function (){
					var isChecked =  $(this).prop("checked");
					if(isChecked){
						var input =  '<input type="text" class="form-control" name="ids" style="width: 200px" value="'+$(this).val()+'" >';
						$("#deleteForm").append(input);
						
					}
				});
				$("#deleteForm").submit();
				
			});
			

		});
	</script>
</body>
</html>
