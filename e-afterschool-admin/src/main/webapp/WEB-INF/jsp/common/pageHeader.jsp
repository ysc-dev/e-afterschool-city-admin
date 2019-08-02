<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-header page-header-light">
	<div class="page-header-content header-elements-md-inline">
		<div class="page-title d-flex">
			<h4>
				<i class="${param.icon} mr-3"></i><span class="font-weight-bold">${param.title}</span>
				<c:if test="${param.content ne null}">
					<small class="text-muted">${param.content}</small>
				</c:if>
			</h4>
		</div>
	</div>
	<div class="breadcrumb-line breadcrumb-line-light header-elements-md-inline">
		<div class="d-flex">
			<div class="breadcrumb">
				<a href="${pageContext.request.contextPath}/home" class="breadcrumb-item">
					<i class="icon-home2 mr-2"></i> Home
				</a>
				<c:if test="${param.firstname ne null}">
					<span class="breadcrumb-item">${param.firstname}</span>
				</c:if>
				<c:if test="${param.middlename ne null}">
					<span class="breadcrumb-item">${param.middlename}</span>
				</c:if>
				<span class="breadcrumb-item active">${param.lastname}</span>
			</div>
		</div>
	</div>
</div>