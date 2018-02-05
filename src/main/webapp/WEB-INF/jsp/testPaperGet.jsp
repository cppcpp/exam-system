<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../public/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="../public/media/css/blue.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="../public/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="../public/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="../public/media/image/favicon.ico" />
<title>抽取现有试卷</title>
</head>
<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">
				<a class="brand">南京晓庄学院自动组卷系统</a>
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

				<img src="../public/media/image/menu-toggler.png" alt="" />

				</a>          

				<!-- END RESPONSIVE MENU TOGGLER -->            

				<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user">

						<a href="#" class="dropdown-toggle" data-toggle="dropdown">

						<!-- <img alt="" src="../public/media/image/avatar1_small.jpg" /> -->

						<span class="username"><c:out value="${user.name}"/></span>

						<i class="icon-angle-down"></i>

						</a>

						<ul class="dropdown-menu">

							<li><a href="../user/personalInfo"><i class="icon-user"></i>个人中心</a></li>

							<li><a href="../user/modifyPassword"><i class="icon-key"></i>修改密码</a></li>
							<!-- <img src="../public/media/image/quit.png" /> -->

							<li><a href="../user/logout"><i class="icon-power-off"></i>退出</a></li>

						</ul>

					</li>

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

				<li class="active">

					<a href="javascript:;">

				 	<!-- <i class="icon-cogs"></i> -->
					<img src="../public/media/image/testPaper.png" /> 

					<span class="title" style="color: #f0e8">试卷生成管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li class="active">

							<a href="get">
								抽取现有试卷
							</a>

						</li>
						<li >

							<a href="generateAuto">
								自动生成试卷
							</a>

						</li>
						<li >

							<a href="generateSelf">
								手动生成试卷
							</a>

						</li>

					</ul>

				</li>

				<li>

					<a class="active" href="javascript:;">

					<image src="../public/media/image/questions.png" />

					<span class="title">题库管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li>

							<a href="javascript:;">

							科目管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">

								<li><a href="../subject/add">科目录入</a></li>

								<li><a href="../subject/all">科目维护</a></li>

							</ul>

						</li>
						<li>

							<a href="javascript:;">

							知识点管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">

								<li><a href="../knowledgePoints/add">知识点录入</a></li>

								<li><a href="../knowledgePoints/all">知识点维护</a></li>

							</ul>

						</li>
							
						<li>

							<a href="javascript:;">

							题型管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">

								<li><a href="../questionType/add">题型录入</a></li>

								<li><a href="../questionType/all">题型维护</a></li>

							</ul>

						</li>

						<li>

							<a href="javascript:;">

							试题管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../questions/add">试题录入</a></li>
								<li><a href="../questions/all/-1/-1">试题维护</a></li>
								<li><a href="../questions/print">打印题库</a></li>
							</ul>

						</li>
					</ul>

				</li>
				
				<li class="">

					<a href="javascript:;">

					<i class="icon-cogs"></i> 

					<span class="title">系统管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li>

							<a href="javascript:;">

							用户管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../user/add">用户添加</a></li>
								<li><a href="../user/all/1">录入人员管理</a></li>
								<li><a href="../user/all/2">任课教师管理</a></li>
								<li><a href="../user/all/3">超级用户管理</a></li>
							</ul>
						</li>
						<li>

							<a href="javascript:;">

							系统信息管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../systemConfig/add">系统信息添加</a></li>
								<li><a href="../systemConfig/all">系统信息查看</a></li>
								
							</ul>

						</li>
						<li>

							<a href="javascript:;">

							公告信息管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../news/add">公告信息添加</a></li>
								<li><a href="../news/all">公告信息查看</a></li>
								
							</ul>

						</li>
						<li >

							<a href="../databaseBabckup/all">
								数据库管理
							</a>

						</li>

					</ul>

				</li>
				<li class="">

					<a href="javascript:;">

					<img src="../public/media/image/personInfomation.png"/>

					<span class="title">个人中心</span>

					<span class="arrow"></span>

					</a>

					<ul class="sub-menu">
						<li class="active">

							<a href="../user/personalInfo">
								个人信息管理
							</a>

						</li>
						<li >

							<a href="../user/modifyPassword">
								修改密码
							</a>

						</li>

					</ul>

				</li>

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

							试卷生成管理 <small>抽取现有试卷</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="../index">首页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">试卷生成管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">抽取现有试卷</a></li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
					<!-- 主体部分开始 -->
					<div class="span12">
						<table class="table table-striped table-bordered" id="table">
							<tr>
								<th colspan="6">试卷列表</th>
							</tr>
							<tr>
								<td>试卷标题</td>
								<td>难易度</td>
								<td>科目</td>
								<td>生成者</td>
								<td>生成时间</td>
								<td>操作</td>
							</tr>
							<c:if test="${examList!=null}">
								<c:forEach items="${examList}" var="exam">
									<tr>
										<td>${exam.eTitle}</td>
										<td>${exam.eDifficulty}</td>
										<td>${exam.subjectTitle}</td>
										<td>${exam.userName}</td>
										<td>${exam.addTime}</td>
										<td><a href=''>下载</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</table>						
	
					</div><!-- 主体部分结束 -->

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

		<div class="footer-inner">
			2017 &copy; 技术支持  陈培培 南京晓庄学院
		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="../public/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="../public/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="../public/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="../public/media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="../public/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="../public/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="../public/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="../public/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="../public/media/js/jquery.gritter.js"></script>

	<script type="text/javascript" src="../public/media/js/jquery.pulsate.min.js"></script>

	<script type="text/javascript" src="../public/media/js/jquery.bootpag.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="../public/media/js/app.js"></script>

	<script src="../public/media/js/ui-general.js"></script>     

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		   UIGeneral.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>
</html>