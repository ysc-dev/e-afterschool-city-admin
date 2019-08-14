<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" />
	<title>방과후학교 관리시스템</title>
	
	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	
	<c:set var="contextName">${pageContext.request.contextPath}</c:set>
	
	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/layout.min.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/components.min.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/limitless/css/colors.min.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->
	
	<link href="${contextName}/css/common.css" rel="stylesheet" type="text/css">
	
	<!-- Core JS files -->
	<script src="${contextName}/limitless/js/main/jquery.min.js"></script>
	<script src="${contextName}/limitless/js/main/bootstrap.bundle.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/loaders/blockui.min.js"></script>
	<!-- Core JS files -->
	
	<!-- Theme JS files -->
	<script src="${contextName}/limitless/js/plugins/forms/styling/uniform.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/forms/inputs/inputmask.js"></script>
	<script src="${contextName}/limitless/js/plugins/forms/inputs/formatter.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/forms/selects/select2.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/ui/moment/moment.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/tables/datatables/datatables.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/tables/datatables/extensions/jszip/jszip.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/tables/datatables/extensions/buttons.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/extensions/jquery_ui/interactions.min.js"></script>
	<script src="${contextName}/limitless/js/plugins/uploaders/fileinput/fileinput.min.js"></script>
	
	<script src="${contextName}/limitless/js/plugins/notifications/sweet_alert.min.js"></script>
	
	<script src="${contextName}/limitless/js/app.js"></script>
	<!-- /theme JS files -->
	
	<script src="${contextName}/js/common.js"></script>
	<script src="${contextName}/js/custom.js"></script>
	<script src="${contextName}/js/table.js"></script>
</head>