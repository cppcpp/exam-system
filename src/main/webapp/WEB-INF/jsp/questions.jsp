<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../../public/media/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<link href="../../../public/media/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />

<link href="../../../public/media/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<link href="../../../public/media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="../../../public/media/css/style.css" rel="stylesheet"
	type="text/css" />

<link href="../../../public/media/css/style-responsive.css"
	rel="stylesheet" type="text/css" />

<link href="../../../public/media/css/blue.css" rel="stylesheet"
	type="text/css" id="style_color" />

<link href="../../../public/media/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="../../../public/media/css/jquery.gritter.css"
	rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="../../../public/media/image/favicon.ico" />
<style type="text/css">
	table {
		table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
	}
	
	.fix_td_class{
		width: 100%;
		word-break: keep-all; /* 不换行 */
		white-space: nowrap; /* 不换行 */
		overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
</style>
<title>试题信息</title>
</head>
<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">
				<a class="brand">南京晓庄学院自动组卷系统</a>
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed"
					data-toggle="collapse" data-target=".nav-collapse"> <img
					src="../../../public/media/image/menu-toggler.png" alt="" />

				</a>

				<!-- END RESPONSIVE MENU TOGGLER -->

				<!-- BEGIN TOP NAVIGATION MENU -->

				<ul class="nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <!-- <img alt="" src="../public/media/image/avatar1_small.jpg" /> -->

							<span class="username"><c:out value="${user.name}" /></span> <i
							class="icon-angle-down"></i>

					</a>

						<ul class="dropdown-menu">

							<li><a href="../../../user/personalInfo"><i
									class="icon-user"></i>个人中心</a></li>

							<li><a href="../../../user/modifyPassword"><i
									class="icon-key"></i>修改密码</a></li>
							<!-- <img src="../public/media/image/quit.png" /> -->

							<li><a href="../../../user/logout"><i
									class="icon-power-off"></i>退出</a></li>

						</ul></li>

					<!-- END USER LOGIN DROPDOWN -->

				</ul>

				<!-- END TOP NAVIGATION MENU -->

			</div>

		</div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->

	<div class="page-container row-fluid">

		<!-- BEGIN SIDEBAR -->

		<div class="page-sidebar nav-collapse collapse">

			<!-- BEGIN SIDEBAR MENU -->

			<ul class="page-sidebar-menu">

				<li>
					<!--隐藏侧边栏按钮-->
					<div class="sidebar-toggler hidden-phone"></div>
				</li>

				<li><a href="javascript:;"> <!-- <i class="icon-cogs"></i> -->
						<img src="../../../public/media/image/testPaper.png" /> <span
						class="title" style="color: #f0e8">试卷生成管理</span> <span
						class="arrow "></span>

				</a>

					<ul class="sub-menu">
						<li><a href="../../../testPaper/get"> 抽取现有试卷 </a></li>
						<li><a href="../../../testPaper/generateAuto"> 自动生成试卷 </a></li>
						<li><a href="../../../testPaper/generateSelf"> 手动生成试卷 </a></li>

					</ul></li>

				<li class="active"><a href="javascript:;"> <image
							src="../../../public/media/image/questions.png" /> <span
						class="title">题库管理</span> <span class="arrow "></span>

				</a>

					<ul class="sub-menu">
						<li><a href="javascript:;"> 科目管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">

								<li><a href="../../../subject/add">科目录入</a></li>

								<li class="active"><a href="../../../subject/all">科目维护</a></li>

							</ul></li>
						<li><a href="javascript:;"> 知识点管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">

								<li><a href="../../../knowledgePoints/add">知识点录入</a></li>

								<li><a href="../../../knowledgePoints/all">知识点维护</a></li>

							</ul></li>

						<li><a href="javascript:;"> 题型管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">

								<li><a href="../../../questionType/add">题型录入</a></li>

								<li><a href="../../../questionType/all">题型维护</a></li>

							</ul></li>

						<li class="active"><a href="javascript:;"> 试题管理 <span
								class="arrow"></span>

						</a>

							<ul class="sub-menu">
								<li><a href="../../../questions/add">试题录入</a></li>
								<li class="active"><a href="../-1/-1">试题维护</a></li>
								<li><a href="../../../questions/print">打印题库</a></li>
							</ul></li>
					</ul></li>

				<li class=""><a href="javascript:;"> <i class="icon-cogs"></i>

						<span class="title">系统管理</span> <span class="arrow "></span>

				</a>

					<ul class="sub-menu">
						<li><a href="javascript:;"> 用户管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">
								<li><a href="../../../user/add">用户添加</a></li>
								<li><a href="../../../user/all/1">录入人员管理</a></li>
								<li><a href="../../../user/all/2">任课教师管理</a></li>
								<li><a href="../../../user/all/3">超级用户管理</a></li>
							</ul></li>
						<li><a href="javascript:;"> 系统信息管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">
								<li><a href="../../../systemConfig/add">系统信息添加</a></li>
								<li><a href="../../../systemConfig/all">系统信息查看</a></li>

							</ul></li>
						<li><a href="javascript:;"> 公告信息管理 <span class="arrow"></span>

						</a>

							<ul class="sub-menu">
								<li><a href="../../../news/add">公告信息添加</a></li>
								<li><a href="../../../news/all">公告信息查看</a></li>

							</ul></li>
						<li><a href="../../../databaseBabckup/all"> 数据库管理 </a></li>

					</ul></li>
				<li class=""><a href="javascript:;"> <img
						src="../../../public/media/image/personInfomation.png" /> <span
						class="title">个人中心</span> <span class="arrow"></span>

				</a>

					<ul class="sub-menu">
						<li class="active"><a href="../../../user/personalInfo">
								个人信息管理 </a></li>
						<li><a href="../../../user/modifyPassword"> 修改密码 </a></li>

					</ul></li>

			</ul>

			<!-- END SIDEBAR MENU -->

		</div>

		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div id="portlet-config" class="modal hide">

				<div class="modal-header">

					<button data-dismiss="modal" class="close" type="button"></button>

					<h3>Widget Settings</h3>

				</div>

				<div class="modal-body">

					<p>Here will be a configuration form</p>

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->

				<div class="row-fluid">

					<div class="span12">

						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 class="page-title">

							试题管理 <small>试题维护</small>

						</h3>

						<ul class="breadcrumb">

							<li><i class="icon-home"></i> <a href="../../../index">首页</a>

								<i class="icon-angle-right"></i></li>

							<li><a href="#">题库管理</a> <i class="icon-angle-right"></i></li>

							<li><a href="#">试题管理</a> <i class="icon-angle-right"></i></li>
							<li><a href="#">试题维护</a></li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">

					<!-- 内容主体 -->

					<div class="span12">
					<c:if test="${questionDelError!=null}">
						<c:out value="${questionDelError}" />
					</c:if>
					
						科目： <select id="sId">
							<option value="-1">请选择科目信息</option>
							<c:if test="${subjects!=null}">
								<c:forEach items="${subjects}" var="subject">
									<c:if test="${sId==subject.sId}">
										<option value="${subject.sId}" selected="selected">${subject.sTitle}</option>
									</c:if>
									<c:if test="${sId!=subject.sId}">
										<option value="${subject.sId}">${subject.sTitle}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</select> 题型： <select id="qtId">
							<option value="-1">请选择题型</option>
							<c:if test="${questionTypes!=null}">
								<c:forEach items="${questionTypes}" var="qt">
									<c:if test="${qtId==qt.tId}">
										<option value="${qt.tId}" selected="selected">${qt.tTitle}</option>
									</c:if>
									<c:if test="${qtId!=qt.tId}">
										<option value="${qt.tId}">${qt.tTitle}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</select>
						<button class="btn default" id="query">查询</button>

						<table class="table table-striped table-bordered" id="table">
							<tr>
								<th colspan="7">试题列表</th>
							</tr>
							<tr>
								<td>题目</td>
								<td>答案</td>
								<td>科目</td>
								<td>题型</td>
								<td>难易度</td>
								<td>添加时间</td>
								<td>操作</td>
							</tr>
							<c:if test="${questions!=null}">
								<c:forEach items="${questions}" var="q">
									<tr>
										<td class="fix_td_class" title='${q.qTitle}'><c:out value="${q.qTitle}" /></td>
										<td class="fix_td_class" title='${q.qAnswer}'><c:out value="${q.qAnswer}" /></td>
										<td><c:out value="${q.sIdCN}" /></td>
										<td><c:out value="${q.questionTypeCN}" /></td>
										<td><c:out value="${q.qDifficultyLevelCN}" /></td>
										<td><c:out value="${q.qAddTime}" /></td>
										<td class="fix_td_class">
											<button class="btn" id="button_modify" data-toggle="modal" data-target="#detailModal">查</button> 
											<a href="../../modify/${q.qId}" class="btn">改</a>
											<a href="../../delete/${q.sId}/${q.questionTypeId}/${q.qId}" class="btn">删</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
						<a href="../${sId}/${qtId}?pageNum=${page.firstPage}">首页</a>|<a href="../${sId}/${qtId}?pageNum=${page.prePage}">上一页</a>|<a href="../${sId}/${qtId}?pageNum=${page.nextPage}">下一页</a>|<a href="../${sId}/${qtId}?pageNum=${page.lastPage}">尾页</a></br>
		共<c:out value="${page.total}"/>条数据，当前为第 <c:out value="${page.pageNum}"/> 页，共  <c:out value="${page.pages}"/>页
						
						<!-- 查询模态框 -->
						<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
											
										</button>
										<h4 class="modal-title" id="myModalLabel">
											题目详情
										</h4>
									</div>
									<div class="modal-body">
										<table border="1">
											<tr>
												<td>题目:</td>
												<td id="qTitle"></td>
											</tr>
											<tr>
												<td>答案：</td>
												<td id="qAnswer"></td>
											</tr>
										</table>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
					</div>
					<!-- 内容主体结束 -->
				</div>

				<!-- END PAGE CONTENT-->

			</div>

			<!-- END PAGE CONTAINER-->

		</div>

		<!-- BEGIN PAGE -->

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">2017 &copy; 技术支持 陈培培 南京晓庄学院</div>

		<div class="footer-tools">

			<span class="go-top"> <i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="../../../public/media/js/jquery-1.10.1.min.js"
		type="text/javascript"></script>

	<script src="../../../public/media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="../../../public/media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

	<script src="../../../public/media/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="../../../public/media/js/jquery.slimscroll.min.js"
		type="text/javascript"></script>

	<script src="../../../public/media/js/jquery.blockui.min.js"
		type="text/javascript"></script>

	<script src="../../../public/media/js/jquery.cookie.min.js"
		type="text/javascript"></script>

	<script src="../../../public/media/js/jquery.uniform.min.js"
		type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript"
		src="../../../public/media/js/jquery.gritter.js"></script>

	<script type="text/javascript"
		src="../../../public/media/js/jquery.pulsate.min.js"></script>

	<script type="text/javascript"
		src="../../../public/media/js/jquery.bootpag.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="../../../public/media/js/app.js"></script>

	<script src="../../../public/media/js/ui-general.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {

			// initiate layout and plugins

			App.init();

			UIGeneral.init();
		});

		//查询按钮点击
		$("#query").click(function() {
			var sId = $("#sId").val();
			var qtId = $("#qtId").val();
			location.href = "../" + sId + "/" + qtId;
		});
		
		//赋值给模态框
		$("#table tr").click(function(){
			var qTitle,qAnswer;
			var td=$(this).find("td");
			//获取指定id的孩子节点
			qTitle=td.eq(0).text();
			qAnswer=td.eq(1).text();

			console.log("wenti:"+qTitle)
			console.log("答案："+qAnswer)
			
			//给模态框中的值设值
			$("#qTitle").html(qTitle);
			$("#qAnswer").html(qAnswer);
		})
		
	</script>

	<!-- END JAVASCRIPTS -->

</body>
</html>