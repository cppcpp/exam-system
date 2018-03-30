<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../public/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="../../public/media/css/blue.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="../../public/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="../../public/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="../../public/media/image/favicon.ico" />
<title>题目修改页面</title>
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

				<img src="../../public/media/image/menu-toggler.png" alt="" />

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

							<li><a href="../../user/personalInfo"><i class="icon-user"></i>个人中心</a></li>

							<li><a href="../../user/modifyPassword"><i class="icon-key"></i>修改密码</a></li>
							<!-- <img src="../public/media/image/quit.png" /> -->

							<li><a href="../../user/logout"><i class="icon-power-off"></i>退出</a></li>

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
					<img src="../../public/media/image/testPaper.png" /> 

					<span class="title">试卷生成管理</span>

					<span class="arrow "></span>

					</a>

					<ul class="sub-menu">
						<li>

							<a href="../../testPaper/get">
								抽取现有试卷
							</a>

						</li>
						<li >

							<a href="../../testPaper/generateAuto">
								自动生成试卷
							</a>

						</li>
						<li >

							<a href="../../testPaper/generateSelf">
								手动生成试卷
							</a>

						</li>

					</ul>

				</li>

				<li  class="active">

					<a href="javascript:;">

					<image src="../../public/media/image/questions.png" />

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

								<li><a href="../../subject/add">科目录入</a></li>

								<li class="active"><a href="../../subject/all">科目维护</a></li>

							</ul>

						</li>
						<li>

							<a href="javascript:;">

							知识点管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">

								<li><a href="../../knowledgePoints/add">知识点录入</a></li>

								<li><a href="../../knowledgePoints/all">知识点维护</a></li>

							</ul>

						</li>
							
						<li>

							<a href="javascript:;">

							题型管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">

								<li><a href="../../questionType/add">题型录入</a></li>

								<li><a href="../../questionType/all">题型维护</a></li>

							</ul>

						</li>

						<li class="active">

							<a href="javascript:;">

							试题管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../../questions/add">试题录入</a></li>
								<li class="active"><a href="../../questions/all/-1/-1">试题维护</a></li>
								<li><a href="../../questions/print">打印题库</a></li>
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
								<li><a href="../../user/add">用户添加</a></li>
								<li><a href="../../user/all/1">录入人员管理</a></li>
								<li><a href="../../user/all/2">任课教师管理</a></li>
								<li><a href="../../user/all/3">超级用户管理</a></li>
							</ul>
						</li>
						<li>

							<a href="javascript:;">

							系统信息管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../../systemConfig/add">系统信息添加</a></li>
								<li><a href="../../systemConfig/all">系统信息查看</a></li>
								
							</ul>

						</li>
						<li>

							<a href="javascript:;">

							公告信息管理

							<span class="arrow"></span>

							</a>

							<ul class="sub-menu">
								<li><a href="../../news/add">公告信息添加</a></li>
								<li><a href="../../news/all">公告信息查看</a></li>
								
							</ul>

						</li>
						<li >

							<a href="../../databaseBabckup/all">
								数据库管理
							</a>

						</li>

					</ul>

				</li>
				<li class="">

					<a href="javascript:;">

					<img src="../../public/media/image/personInfomation.png"/>

					<span class="title">个人中心</span>

					<span class="arrow"></span>

					</a>

					<ul class="sub-menu">
						<li>

							<a href="../../user/personalInfo">
								个人信息管理
							</a>

						</li>
						<li>

							<a href="../../user/modifyPassword">
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

							科目管理 <small>科目修改</small>

						</h3>

						<ul class="breadcrumb">

							<li>

								<i class="icon-home"></i>

								<a href="../../index">首页</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li>

								<a href="#">题库管理</a>

								<i class="icon-angle-right"></i>

							</li>

							<li><a href="#">科目管理</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">科目维护</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="#">科目修改</a>
							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->

				<div class="row-fluid">

					<!-- 内容主体开始 -->
					<div class="span12">
					<c:if test="${questionModifyError!=null}">
						<c:out value="${questionModifyError}" class="error"/>
					</c:if>
					<sf:form commandName="questions">
						<sf:label cssErrorClass="error" path="qId">id:</sf:label>
						<input type="text" name="qId" value="${questions.qId}" disabled="disabled"></br>
					
						<sf:label cssErrorClass="error" path="sId">科目：</sf:label>
							<sf:select path="sId">
								<c:if test="${subjects!=null}">
									<c:forEach items="${subjects}" var="s">
										<c:if test="${s.sId==questions.sId}">
											<option selected="selected" value="${s.sId}">${s.sTitle}</option>
										</c:if>
										<c:if test="${s.sId!=questions.sId}">
											<option value="${s.sId}">${s.sTitle}</option>
										</c:if>
									</c:forEach>
								</c:if>
							</sf:select></br>
						<sf:errors cssClass="error" path="sId" /></br>
						
						<sf:label cssErrorClass="error" path="konwledgePointId">知识点：</sf:label>
							<sf:select path="konwledgePointId">
								<c:if test="${knowledgePoints!=null}">
									<c:forEach items="${knowledgePoints}" var="kp">
										<c:if test="${kp.kId==questions.konwledgePointId}">
											<c:if test="${kSubNum==0}">
												<option selected="selected" value="${kp.kId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${kp.kNum}.${kp.kSubNum}.${kp.kTitle}</option>
											</c:if>
											<c:if test="${kSubNum!=0}">
												<option selected="selected" value="${kp.kId}">${kp.kNum}.${kp.kSubNum}.${kp.kTitle}</option>
											</c:if>
										</c:if>
										<c:if test="${kp.kId!=questions.konwledgePointId}">
											<c:if test="${kSubNum==0}">
												<option value="${kp.kId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${kp.kNum}.${kp.kSubNum}.${kp.kTitle}</option>
											</c:if>
											<c:if test="${kSubNum!=0}">
												<option value="${kp.kId}">${kp.kNum}.${kp.kSubNum}.${kp.kTitle}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</sf:select>
						<sf:errors cssClass="error" path="konwledgePointId" /></br>
						
						<sf:label cssErrorClass="error" path="questionTypeId">题型：</sf:label>
							<select name="questionTypeId">
								<c:if test="${questionTypes!=null}">
									<c:forEach items="${questionTypes}" var="qt">
									<c:if test="${qt.tId==questions.questionTypeId}">
										<option value="${qt.tId}" selected="selected">${qt.tTitle}</option>
									</c:if>
									<c:if test="${qt.tId!=questions.questionTypeId}">
										<option value="${qt.tId}">${qt.tTitle}</option>
									</c:if>
									</c:forEach>
								</c:if>
							</select></br>
						<sf:errors cssClass="error" path="questionTypeId" /></br>
						
						<sf:label cssErrorClass="error" path="qTitle">题目：</sf:label>
			    		<textarea name="qTitle" id="qTitle" rows="10" cols="80">${questions.qTitle}
    					</textarea>
						<sf:errors cssClass="error" path="qTitle" /></br>
						
						<sf:label cssErrorClass="error" path="qAnswer">答案：</sf:label>
							<!-- 在div中画不同的答案 -->
							<div id="qAnswer_div">
								<c:forEach items="${questionTypes}" var="qt">
									<c:if test="${qt.tId==questions.questionTypeId}">
										<c:choose>
											<c:when test="${qt.tTitle=='单选题'}">
												<select name="qAnswer">
													<c:if test="${questions.qAnswer=='A'}">
														<option value="A" selected="selected">A</option>
														<option value="B">B</option>
														<option value="C">C</option>
														<option value="D">D</option>
													</c:if>
													<c:if test="${questions.qAnswer=='B'}">
														<option value="A">A</option>
														<option value="B" selected="selected">B</option>
														<option value="C">C</option>
														<option value="D">D</option>
													</c:if>
													<c:if test="${questions.qAnswer=='C'}">
														<option value="A">A</option>
														<option value="B">B</option>
														<option value="C" selected="selected">C</option>
														<option value="D">D</option>
													</c:if>
													<c:if test="${questions.qAnswer=='D'}">
														<option value="A">A</option>
														<option value="B">B</option>
														<option value="C">C</option>
														<option value="D" selected="selected">D</option>
													</c:if>
												</select>
											</c:when>
											<c:when test="${qt.tTitle=='多选题'}">
												<input name="qAnswer" value="${questions.qAnswer}" placeholder='多选答案形如ABCD格式'/>
											</c:when>
											<c:when test="${qt.tTitle=='选择题'}">
												<input name="qAnswer" value="${questions.qAnswer}" style='width:300px' placeholder='单选答案形如A  多选答案形如ABCD格式'/>
											</c:when>
											<c:when test="${qt.tTitle=='填空题'}">
												<c:forEach items="${fn:split(questions.qAnswer,',')}" var="answer" varStatus="status">
													空<c:out value="${status.count}" />：<input name="qAnswer" type="text" value="${answer}"></br>
												</c:forEach>
											</c:when>
											<c:when test="${qt.tTitle=='判断题'}">
												<select name="qAnswer">
													<c:choose>
														<c:when test="${questions.qAnswer=='&#935'}">
															<option value="&#935" selected="selected">&#935</option>
															<option value="&#8730">&#8730</option>
														</c:when>
														<c:otherwise>
															<option value="&#8730" selected="selected">&#8730</option>
															<option value="&#935">&#935</option>
														</c:otherwise>
													</c:choose>
												</select>
											</c:when>
											
											<c:otherwise>
												<textarea name="qAnswer" id="qAnswer1" rows="10" cols="80">${questions.qAnswer}</textarea>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</div>
						<sf:errors cssClass="error" path="qAnswer" /></br>
						
						<!-- 难易度写死 -->
						<sf:label cssErrorClass="error" path="qDifficultyLevel">难易度：</sf:label>
						<select name="qDifficultyLevel">
							<c:if test="${questions.qDifficultyLevel==diffcultyLevel.veryEasy}">
								<option value="${diffcultyLevel.veryEasy }" selected="selected">很容易</option>
								<option value="${diffcultyLevel.easy}">容易</option>
								<option value="${diffcultyLevel.medium }">中等</option>
								<option value="${diffcultyLevel.hard }">困难</option>
								<option value="${diffcultyLevel.veryHard }">很困难</option>
							</c:if>
							<c:if test="${questions.qDifficultyLevel==diffcultyLevel.easy}">
								<option value="${diffcultyLevel.veryEasy }">很容易</option>
								<option value="${diffcultyLevel.easy}" selected="selected">容易</option>
								<option value="${diffcultyLevel.medium }">中等</option>
								<option value="${diffcultyLevel.hard }">困难</option>
								<option value="${diffcultyLevel.veryHard }">很困难</option>
							</c:if>
							<c:if test="${questions.qDifficultyLevel==diffcultyLevel.medium}">
								<option value="${diffcultyLevel.veryEasy }">很容易</option>
								<option value="${diffcultyLevel.easy}">容易</option>
								<option value="${diffcultyLevel.medium }" selected="selected">中等</option>
								<option value="${diffcultyLevel.hard }">困难</option>
								<option value="${diffcultyLevel.veryHard }">很困难</option>
							</c:if>
							<c:if test="${questions.qDifficultyLevel==diffcultyLevel.hard}">
								<option value="${diffcultyLevel.veryEasy }">很容易</option>
								<option value="${diffcultyLevel.easy}">容易</option>
								<option value="${diffcultyLevel.medium }">中等</option>
								<option value="${diffcultyLevel.hard}" selected="selected">困难</option>
								<option value="${diffcultyLevel.veryHard }">很困难</option>
							</c:if>
							<c:if test="${questions.qDifficultyLevel==diffcultyLevel.veryHard}">
								<option value="${diffcultyLevel.veryEasy }">很容易</option>
								<option value="${diffcultyLevel.easy}">容易</option>
								<option value="${diffcultyLevel.medium }">中等</option>
								<option value="${diffcultyLevel.hard }">困难</option>
								<option value="${diffcultyLevel.veryHard}" selected="selected">很困难</option>
							</c:if>
							
						</select><br>
						<sf:errors cssClass="error" path="qDifficultyLevel"/></br>
						<input name="qAddTime" value="${questions.qAddTime}" type="hidden">
						
						<button type="submit" class="btn default-btn">提交</button>
					</sf:form>
						
					</div><!-- 内容主体结束 -->

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

	<script src="../../public/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="../../public/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="../../public/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="../../public/media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="../../public/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="../../public/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="../../public/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="../../public/media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="../../public/media/js/jquery.gritter.js"></script>

	<script type="text/javascript" src="../../public/media/js/jquery.pulsate.min.js"></script>

	<script type="text/javascript" src="../../public/media/js/jquery.bootpag.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="../../public/media/js/app.js"></script>

	<script src="../../public/media/js/ui-general.js"></script>   
	<script type="text/javascript" src="../../public/ckeditor/ckeditor.js"></script>  

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {       

		   // initiate layout and plugins

		   App.init();

		   UIGeneral.init();
		   
		   if($("#qTitle")){
		       CKEDITOR.replace( 'qTitle' );
		   }
		   if($("#qAnswer1")){
			   console.log("qAnswer存在·····")
			   CKEDITOR.replace('qAnswer1');
		   }
		  

		});
		

	</script>

	<!-- END JAVASCRIPTS -->

</body>
</html>