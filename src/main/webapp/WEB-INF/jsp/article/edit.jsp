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
				<li><a href="list.html">导航管理</a></li>
				<li><a href="add.html">导航添加</a></li>
			</ol>
		</div>
		<div id="div_add_body" class="panel-body" style="padding: 0px">
			<div id="tab_body" class="tab-content" style="width: 100%">
				<div class="tab-pane active" id="div_info"
					style="overflow: scroll; width: 100%;">
					<div>.</div>
					
					
					<form id="commentForm" class="form-horizontal" role="form"
						method="post" action="/admin/article/edit/">
						
							<div class="form-group" style="width: 100%;display: none;">
							<label for="inputEmail3" class="col-sm-2 control-label">id：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="id"
									style="width: 200px" value="${article.id}">
							</div>
							</div>
							
						<div class="form-group" style="width: 100%">
							<label for="inputEmail3" class="col-sm-2 control-label">标题：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title"
									style="width: 200px" value="${article.title}" required>
							</div>
						</div>
						<div id="parent_div" class="form-group" style="width: 100%">
							<label for="inputPassword3" class="col-sm-2 control-label">所属：</label>
							<div class="col-sm-10">
								<select id="articleCategory" name="articleCategory" class="form-control"
									style="width: 200px">
									<c:forEach var="item" items="${navigations}">
										<option value="${item.id}">${item.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group" style="width: 100%; display: none;">
							<label class="col-sm-2 control-label">所属：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="parentname"
									name="parentname" value="${navigation.name}" style="width: 200px" value="top">
							</div>
						</div>
						
						<div class="form-group" style="width: 100%;">
							<label for="inputPassword3" class="col-sm-2 control-label">作者：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="author" name="author"
									style="width: 200px" value="${article.author}"  required>
							</div>
						</div>

						<div class="form-group" style="width: 100%">
							<label for="inputPassword3" class="col-sm-2 control-label">图片：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="cover"
									name="cover" style="width: 200px"
									value="${article.cover}" required>
									
							</div>
							<button type="button"  class="btn primary" style="display:inline;"  onclick="imagesSelect()" >选择</button>  <a id="logshow"  href="#">查看</a> 
						</div>
							 
						<div class="form-group" style="width: 100%;">
							<label for="inputPassword3" class="col-sm-2 control-label">页面标题：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="seoTitle" name="seoTitle"
									style="width: 200px" value="${article.seoTitle}" required>
							</div>
						</div>
						
						<div class="form-group" style="width: 100%;">
							<label for="inputPassword3" class="col-sm-2 control-label">页面关键词：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="seoKeywords" name="seoKeywords"
									style="width: 200px" value="${article.seoKeywords}"  required>
							</div>
						</div>
						
							<div class="form-group" style="width: 100%;">
							<label for="inputPassword3" class="col-sm-2 control-label">页面描述：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="seoDescription" name="seoDescription"
									style="width: 200px" value="${article.seoDescription}"  required>
							</div>
						</div>

						<div class="form-group" style="width: 100%">
							<label for="inputPassword3" class="col-sm-2 control-label">内容：</label>
							<div class="col-sm-10">
								<script id="container" name="content" type="text/plain">
       								 ${article.content}
   								 </script>
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
					
					<div style="display: none;">
						<input type="file" id="images"
									name="images"  onchange="fileChange(this);" > 
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="/js/ueditor/ueditor.all.js"></script>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery/ajaxfileupload.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	
	function imagesSelect() {
		$("#images").click();
		
	}
	
	
	function fileChange(target) {
	     var fileSize = 0;         
	     if (!target.files) {     
	       var filePath = target.value;     
	       var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
	       var file = fileSystem.GetFile (filePath);     
	       fileSize = file.Size;    
	     } else {    
	      fileSize = target.files[0].size;     
	      }   
	      var size = fileSize / 1024;    
	      if(size>2000){  
	       alert("附件不能大于2M");
	       target.value="";
	       return
	      }
	      
	      $.ajaxFileUpload({  
	    	  url:"/js/ueditor/jsp/controller.jsp?action=uploadimage",            //需要链接到服务器地址  
             secureuri:true,  
             dataType:"json",
             fileElementId: "images",                        //文件选择框的id属性  
             success: function(data, status){ 
           	  $("#logshow").attr("href",data.url);
           	  $("#cover").val(data.url);
            },error: function (data, status, e){  
                     showDialogWithMsg('ideaMsg','提示','文件错误！');  
            }  
	      });  

	    } 

	
		$(document).ready(function() {
			var docHeight = $(window).height();
			var docWidth = $(window).width();
			var headHeight = $("#div_table_heading").height();
			var tabHeadHei = $("#ul_tab_head").height();
			$("#div_table_main").height(docHeight);
			$("#tab_body").height(docHeight-headHeight-tabHeadHei);
			$("#div_info").height(docHeight-headHeight-tabHeadHei);
			
			
			
			
			 var ue = UE.getEditor('container');
			
			$("#type").change(function() {
				var typeValue  =  $(this).val();
				if(typeValue == 1){
					$("#url").attr("disabled","disabled");
				}else{
					$("#url").removeAttr("disabled");
				}
				
			});
			
			
			$("#type").change(function() {
				var typeValue  =  $(this).val();
				if(typeValue == 1){
					$("#url").attr("disabled","disabled");
					
				}else{
					$("#url").removeAttr("disabled");
					$("#parent").val(0)
				}
				
			});
			
			
			$("#parent").change(function() {
				var parentname =  $("#parent").find("option:selected").text();
				$("#parentname").val(parentname);
			});
			
			$("#articleCategory").val(${article.articleCategory});
			
			
		});
		  //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	</script>
</body>
</html>
