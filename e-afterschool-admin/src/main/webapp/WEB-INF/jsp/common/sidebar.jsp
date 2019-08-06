<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<div class="sidebar sidebar-dark sidebar-main sidebar-expand-md">
	<div class="sidebar-mobile-toggler text-center">
		<a href="#" class="sidebar-mobile-main-toggle">
			<i class="icon-arrow-left8"></i>
		</a>
		Navigation
		<a href="#" class="sidebar-mobile-expand">
			<i class="icon-screen-full"></i>
			<i class="icon-screen-normal"></i>
		</a>
	</div>
	
	<div class="sidebar-content">
		<div class="card card-sidebar-mobile">
			<ul class="nav nav-sidebar" data-nav-type="accordion">
				<li class="nav-item-header">
					<div class="text-uppercase font-size-md line-height-md">목록</div> 
					<i class="icon-menu" title="Main"></i>
				</li>
				<li id="home" class="nav-item">
					<a href="${pageContext.request.contextPath}/home" class="nav-link">
						<i class="icon-home4"></i><span>Dashboard</span>
					</a>
				</li>
				<li id="invitation" class="nav-item">
					<a href="${pageContext.request.contextPath}/invitation/list" class="nav-link">
						<i class="icon-notification2"></i><span>안내장 관리</span>
					</a>
				</li>
				<li id="school" class="nav-item">
					<a href="${pageContext.request.contextPath}/school/list" class="nav-link">
						<i class="icon-office"></i><span>학교 관리</span>
					</a>
				</li>
				<li id="student" class="nav-item">
					<a href="${pageContext.request.contextPath}/student/list" class="nav-link">
						<i class="icon-users"></i><span>학생 관리</span>
					</a>
				</li>
				<li id="teacher" class="nav-item">
					<a href="${pageContext.request.contextPath}/teacher/list" class="nav-link">
						<i class="icon-user-tie"></i><span>강사 관리</span>
					</a>
				</li>
				<li id="subject" class="nav-item nav-item-submenu">
					<a href="#" class="nav-link"><i class="icon-archive"></i> <span>과목 관리</span></a>
					<ul class="nav nav-group-sub" data-submenu-title="Layouts">
						<li id="subject_group_list" class="nav-item"><a href="${pageContext.request.contextPath}/subject/group/list" class="nav-link">
							<i class="icon-list"></i><span>과목 그룹 관리</span></a>
						</li>
						<li id="subject_list" class="nav-item"><a href="${pageContext.request.contextPath}/subject/list" class="nav-link">
							<i class="icon-list"></i><span>수강 과목 조회</span></a>
						</li>
						<li id="subject_regist" class="nav-item"><a href="${pageContext.request.contextPath}/subject/regist" class="nav-link">
							<i class="icon-pencil5"></i><span>수강 과목 등록</span></a>
						</li>
					</ul>
				</li>
				<li id="class" class="nav-item">
					<a href="${pageContext.request.contextPath}/class/list" class="nav-link">
						<i class="icon-stack"></i><span>횟수별 수업 관리</span>
					</a>
				</li>
				<li id="apply" class="nav-item">
					<a href="${pageContext.request.contextPath}/apply/list" class="nav-link">
						<i class="icon-list"></i><span>수강 관리</span>
					</a>
				</li>
				<li id="notice" class="nav-item">
					<a href="${pageContext.request.contextPath}/notice/list" class="nav-link">
						<i class="icon-bubble-notification"></i><span>공지사항</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<script>
	var pathName = this.location.pathname;
	var menuName = pathName.split("/")[2];
    var subMenuName = pathName.split("/")[3];
    var subsubMenuName = pathName.split("/")[4];
    
    if (subsubMenuName) {
    	$("li#" + menuName).addClass("nav-item-expanded nav-item-open");
		$("li#" + menuName + "_" + subMenuName + "_" + subsubMenuName).children().addClass("active");
    } else {
   	  	if (subMenuName) {
   			$("li#" + menuName).addClass("nav-item-expanded nav-item-open");
   			$("li#" + menuName + "_" + subMenuName).children().addClass("active");
   		} else {
   			 $("li#" + menuName).children().addClass("active");
   		}
    }
</script>