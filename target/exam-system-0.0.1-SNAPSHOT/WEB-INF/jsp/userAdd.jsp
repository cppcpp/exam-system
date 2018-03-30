<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
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

	<!-- <link href="../public/css/userAdd.css" rel="stylesheet" type="text/css"/> -->
	<!-- <link href="../public/css/error.css" rel="stylesheet" type="text/css"/> -->
	<link rel="stylesheet" type="text/css" href="../public/media/css/bootstrapValidator.css"/>
	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="../public/media/image/favicon.ico" />
<title>用户添加页面</title>
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

				<li>

					<a href="javascript:;">

				 	<!-- <i class="icon-cogs"></i> -->
					<img src="../public/media/image/testPaper.png" /> 

					<span class="title">试卷生成管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li>

							<a href="../testPaper/get">
								抽取现有试卷
							</a>

						</li>
						<li >

							<a href="../testPaper/generateAuto">
								自动生成试卷
							</a>

						</li>
						<li >

							<a href="../testPaper/generateSelf">
								手动生成试卷
							</a>

						</li>

					</ul>

				</li>

				<li>

					<a href="javascript:;">

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

								<li  class="active"><a href="../subject/add">科目录入</a></li>

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
				
				<li class="active">

					<a href="javascript:;">

					<i class="icon-cogs"></i> 

					<span class="title">系统管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li class="active">

							<a href="javascript:;">

							用户管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li class="active"><a href="../user/add">用户添加</a></li>
								<li><a href="../user/all/1"></a></li>
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

							用户管理 <small>用户添加</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="../index">首页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">系统管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">用户管理</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">用户添加</a>
							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">
					<!-- 内容主体 -->
					<div class="span12">
						<c:if test="${addUserError!=null}">
							<label class="error"><c:out value="${addUserError}"/></label>
						</c:if>
						<c:if test="${userSubjectAddError!=null}">
							<label class="error"><c:out value="${userSubjectAddError}"/></label>
						</c:if>
						
						<sf:form commandName="addUser" id="form">
							<div class="form-group">
								<sf:label class="col-lg-3 control-label" path="username" cssErrorClass="error">用户名</sf:label>
								<sf:input type="text" class="form-control" path="username" cssErrorClass="error"/>
								<sf:errors path="username" cssClass="error"/>
							</div>
							
							<div class="form-group">
								<sf:label class="bold" path="password" cssErrorClass="error">密码</sf:label>
								<sf:input type="password" class="from-control" path="password" id="password" placeholder="6-15位的字母或数字"/>
								<sf:errors path="password" cssClass="error"/>
							</div>
							
							<div class="form-group">
								<label class="bold">确认密码</label>
								<input type="password" class="from-control" name="confirmPassword" id="confirmPassword"/>
							</div>
							
							<div class="form-group">
								<sf:label class="bold" path="name" cssErrorClass="error">姓名</sf:label>
								<sf:input type="text" path="name" required="required" />
								<sf:errors path="name" cssClass="error"/>
							</div>
							
							<div class="form-group">
								<label class="bold">权限</label>
								<c:if test="${powers!=null}">
									<c:forEach items="${powers}" var="power">
										<label class="radio inline">
		  									<%-- <input type="radio" name="RadioPowerOptions" id="radioPower"+${power.key} value="${power.key}"><c:out value=" ${power.value}"/> --%>
		  									<sf:radiobutton path="power" value="${power.key}"/><c:out value=" ${power.value}"/>
										</label>
									</c:forEach>
								</c:if>
							</div>
							
							<div class="form-group">
								<label class="bold">所属科目</label>
								<c:if test="${subjects!=null}">
									<c:forEach items="${subjects}" var="subject">
										<label class="checkbox inline">
										  <input type="checkbox" name="subjects" value="${subject.sId}"><c:out value="${subject.sTitle }"/>
										  <%-- <sf:checkbox path="subjects" value="${subject.sId}" /><c:out value="${subject.sTitle }"/> --%>
										</label>
									</c:forEach>
								</c:if>
							</div>
							
							<button class="btn btn-default" type="submit">提交</button>
						</sf:form>
						
					</div><!-- 内容主体 -->

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
	
	<script src="../public/media/js/bootstrapValidator.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	
	<script type="text/javascript">
	
	/* $(function(){
		//密码限制只能在6-15位
		$("#password").blur(function(){
			var password=$("#password").val();
			var reg="^([a-z]|[A-Z]|[0-9]|_){6,15}$";
			console.log("---------"+password.match(reg))
			if(password.match(reg)==null){
				alert("密码必须是6-15位的字母数字");
				$("#name").focus();
			}
		})
		 
		
		$("#confirmPassword").blur(function(){
			console.log($("#submitButton").attr("disabled"))
			if($("#password").val()!=$("#confirmPassword").val()){
				//console.log("执行")
				alert("两次密码输入不一致");
				//console.log($("#submitButton").attr("disabled"))
				if($("#submitButton").attr("disabled")==undefined){
					$("#submitButton").attr("disabled","disabled")
				}
			}else{
				$("#submitButton").removeAttr("disabled");
			}
		})
		
		console.log($(":radio:checked").val())
		console.log($(":checkbox:checked").val())
		
	}) */
	
	$(document).ready(function(){
		$("#form").bootstrapValidator({
			feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
       		},
			fields:{
				username:{
	                validators: {
	                		notEmpty: {
	                            message: '用户名不能为空'
	                        },
	                	},
				},
				
				password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    stringLength: {
                            min: 6,
                            max: 20,
                            message: '长度必须在6-20之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码由只能数字、字母、下划线组成'
                        },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: '两次输入密码不一样'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                    notEmpty: {
	                        message: '确认密码不能为空'
	                    },
	                    identical: {
	                        field: 'password',
	                        message: '两次输入密码不一样'
	                    }
	                }
	            },
				
	            name:{
	            	validators: {
	                    notEmpty: {
	                        message: '姓名不能为空'
	                    }
	                }
	            },
	            
	            power:{
	            	validators: {
	                    notEmpty: {
	                        message: '权限不能为空'
	                    }
	                }
	            },
	            
	            subjects:{
	            	validators: {
	                    notEmpty: {
	                        message: '科目不能为空'
	                    }
	                }
	            },
	            
			},
		});
	})
	
		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		   UIGeneral.init();

		});
		
	</script>

	<!-- END JAVASCRIPTS -->

</body>
</html>