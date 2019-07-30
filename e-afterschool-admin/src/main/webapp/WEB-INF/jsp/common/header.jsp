<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<!-- Main navbar -->
<div class="navbar navbar-expand-md navbar-dark">
	<div class="navbar-brand">
		<a href="${pageContext.request.contextPath}/home" class="d-inline-block ml-2">
			<img src="${pageContext.request.contextPath}/images/logo.png" alt="logo">
		</a>
	</div>
	
	<div class="d-md-none">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-mobile">
			<i class="icon-tree5"></i>
		</button>
		<button class="navbar-toggler sidebar-mobile-main-toggle" type="button">
			<i class="icon-paragraph-justify3"></i>
		</button>
	</div>
	
	<div class="collapse navbar-collapse" id="navbar-mobile">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a href="#" class="navbar-nav-link sidebar-control sidebar-main-toggle d-none d-md-block">
					<i class="icon-paragraph-justify3"></i>
				</a>
			</li>
		</ul>
		
		<div class="navbar-title ml-md-3">
			<span class="text-center">방과후 학교 관리 시스템</span>
		</div>
		
		<span class="badge bg-success ml-md-4 mr-md-auto px-2">Open</span>
		
		<ul class="navbar-nav">
			<li class="nav-item dropdown dropdown-user">
				<a href="#" class="navbar-nav-link d-flex align-items-center dropdown-toggle" data-toggle="dropdown">
					<img src="${pageContext.request.contextPath}/images/user.png" class="rounded-circle mr-3" height="30" alt=""> 
					<span>관리자</span>
				</a>

				<div class="dropdown-menu dropdown-menu-right">
					<a href="${pageContext.request.contextPath}/user/profile" class="dropdown-item"><i class="icon-user"></i> My profile</a>
					<div class="dropdown-divider"></div>
					<a href="${pageContext.request.contextPath}/logout" class="dropdown-item"><i class="icon-exit"></i> Logout</a>
				</div>
			</li>
		</ul>
	</div>
</div>