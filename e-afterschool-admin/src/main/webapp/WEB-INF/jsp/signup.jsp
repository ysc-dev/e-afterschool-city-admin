<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" />
	<title>방과후학교 관리시스템 회원가입</title>
	
	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
	
	<c:set var="contextName">${pageContext.request.contextPath}</c:set>
	
	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="limitless/css/icons/icomoon/styles.min.css" rel="stylesheet" type="text/css">
	<link href="limitless/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="limitless/css/bootstrap_limitless.min.css" rel="stylesheet" type="text/css">
	<link href="limitless/css/layout.min.css" rel="stylesheet" type="text/css">
	<link href="limitless/css/components.min.css" rel="stylesheet" type="text/css">
	<link href="limitless/css/colors.min.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->
	
	<!-- Custom stylesheets -->
	<link href="${contextName}/css/common.css" rel="stylesheet" type="text/css">
	<link href="${contextName}/css/user.css" rel="stylesheet" type="text/css">
	<!-- /Custom stylesheets -->
	
	<!-- Core JS files -->
	<script src="limitless/js/main/jquery.min.js"></script>
	<script src="limitless/js/main/bootstrap.bundle.min.js"></script>
	<script src="limitless/js/plugins/loaders/blockui.min.js"></script>
	<!-- Core JS files -->
	
	<!-- Theme JS files -->
	<script src="limitless/js/plugins/forms/validation/validate.min.js"></script>
	<script src="limitless/js/plugins/forms/styling/uniform.min.js"></script>
	
	<script src="limitless/js/plugins/notifications/sweet_alert.min.js"></script>
	
	<script src="limitless/js/app.js"></script>
	<!-- /theme JS files -->
	
	<!-- Custom JS files -->
	<script src="${contextName}/js/common.js"></script>
	<script src="${contextName}/js/signup.js"></script>
	<!-- /Custom JS files -->
</head>
<body>
    <div class="page-content">
        <div class="content-wrapper">
            <div class="content d-flex justify-content-center align-items-center">
                 <form:form id="registForm" modelAttribute="user" cssClass="form-validate col-xl-4 col-11" 
                 	action="${contextName}/user/signup" method="post">
                	<div class="card mb-0">
                		<div class="card-body px-md-5 py-md-4">
                			<div class="text-center mb-3">
                                <i class="icon-plus3 icon-2x text-success border-success border-3 rounded-round p-3 mb-3 mt-1"></i>
                                <h3 class="mb-0">회원가입</h3>
                                <span class="d-block text-muted">모든 항목을 채워야합니다</span>
                            </div>
                            
                            <div class="form-group text-center text-muted content-divider">
                                <span class="px-2">기본 정보</span>
                            </div>
                            
                            <div class="form-group form-group-feedback form-group-feedback-left">
                                <form:input path="userId" type="text" cssClass="form-control" placeholder="사용자 ID" required="required" />
                                <div class="form-control-feedback">
                                    <i class="icon-user-check text-muted"></i>
                                </div>
                            </div>
                            
                            <div class="form-group form-group-feedback form-group-feedback-left">
                                <form:input path="name" type="text" cssClass="form-control" placeholder="사용자명" required="required" />
                                <div class="form-control-feedback">
                                    <i class="icon-user-check text-muted"></i>
                                </div>
                            </div>
                            
                             <div class="form-group form-group-feedback form-group-feedback-left">
                                <form:input path="password" type="password" cssClass="form-control" placeholder="비밀번호" required="required" />
                                <div class="form-control-feedback">
                                    <i class="icon-user-lock text-muted"></i>
                                </div>
                            </div>
                            <div class="form-group form-group-feedback form-group-feedback-left">
                                <input type="password" class="form-control" placeholder="비밀번호 확인" name="confirmPassword" required>
                                <div class="form-control-feedback">
                                    <i class="icon-user-lock text-muted"></i>
                                </div>
                            </div>
                            
                            <div class="form-group form-group-feedback form-group-feedback-left">
                                <form:input path="tel" type="tel" cssClass="form-control" placeholder="전화번호" required="required" />
                                <div class="form-control-feedback">
                                    <i class="icon-phone text-muted"></i>
                                </div>
                            </div>
                            
                             <div class="form-group text-center text-muted content-divider">
                                <span class="px-2"></span>
                            </div>

                            <button type="submit" class="btn bg-primary btn-block">회원 등록<i class="icon-circle-right2 ml-2"></i></button>
                            <a href="javascript:history.back(-1);" class="btn btn-light btn-block mt-2">취 소<i class="icon-circle-left2 ml-2"></i></a>
                		</div>
                	</div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>