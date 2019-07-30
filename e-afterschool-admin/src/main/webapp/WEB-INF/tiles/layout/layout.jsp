<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<!DOCTYPE html>
<html>
	<tiles:insertAttribute name="head"/>
<body>
	<tiles:insertAttribute name="header"/>
    
    <div class="page-content">
	    <tiles:insertAttribute name="sidebar"/>
	    
	    <div class="content-wrapper">
			<tiles:insertAttribute name="content"/>
		</div>
    </div>
</body>
</html>
