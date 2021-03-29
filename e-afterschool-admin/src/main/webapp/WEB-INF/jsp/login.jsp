<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" />
	<title>방과후학교 관리시스템 로그인</title>
	
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
	
	<link href="css/user.css" rel="stylesheet" type="text/css">
	
	<!-- Core JS files -->
	<script src="limitless/js/main/jquery.min.js"></script>
	<script src="limitless/js/main/bootstrap.bundle.min.js"></script>
	<script src="limitless/js/plugins/loaders/blockui.min.js"></script>
	<!-- Core JS files -->
	
	<!-- Theme JS files -->
	<script src="limitless/js/plugins/forms/validation/validate.min.js"></script>
	<script src="limitless/js/plugins/forms/styling/uniform.min.js"></script>
	
	<script src="limitless/js/app.js"></script>
	<!-- /theme JS files -->
	
	<!-- Custom JS files -->
	<script src="js/login.js"></script>
	<!-- /Custom JS files -->
</head>
<body>
	<div class="page-content">
		<div class="content-wrapper">
			<div class="content d-flex justify-content-center align-items-center">
				<form class="form-validate col-xl-7 col-11" action="${contextName}/login" method="POST">
					<div class="card bg-authentication mb-0">
						<div class="row m-0">
							<div class="col-md-6 col-12 bg-white">
								<div class="card-body px-md-5 py-md-4">
									<div class="text-center mb-3">
										<i class="icon-reading icon-2x text-slate-300 border-slate-300 border-3 rounded-round p-3 mb-3 mt-3"></i>
										<h3 class="mb-1 text-grey-800">계정 로그인</h3>
										<span class="d-block text-muted">아이디와 비밀번호를 입력하세요.</span>
									</div>
									
									<c:if test="${not empty error}">
										<div class="alert alert-danger border-0 alert-dismissible">
											<button type="button" class="close" data-dismiss="alert"><span>×</span></button>
											<span class="font-weight-semibold">사용자 정보가 일치하지 않습니다.</span>
									    </div>
									</c:if>
									
									<div class="form-group form-group-feedback form-group-feedback-left">
										<input type="text" class="form-control" placeholder="아이디" autocomplete="off" value="${username}" 
											name="username" required>
										<div class="form-control-feedback">
											<i class="icon-user text-muted"></i>
										</div>
									</div>
									
									<div class="form-group form-group-feedback form-group-feedback-left">
										<input type="password" class="form-control" placeholder="비밀번호" autocomplete="off" name="password" required>
										<div class="form-control-feedback">
											<i class="icon-lock2 text-muted"></i>
										</div>
									</div>
									
									<div class="form-group d-flex align-items-center">
										<div class="form-check mb-0">
											<label class="form-check-label text-grey-700">
												<input type="checkbox" name="remember-me" class="form-input-styled" checked>
												아이디 저장
											</label>
										</div>
									</div>
									
									<div class="form-group mt-4">
										<button type="submit" class="btn btn-primary btn-block">로그인<i class="icon-circle-right2 ml-2"></i></button>
									</div>
									
									<%-- <div class="form-group text-center text-muted content-divider">
										<span class="px-2">계정이 없습니까?</span>
									</div>
									
									<div class="form-group">
										<a href="${pageContext.request.contextPath}/signup" class="btn btn-light btn-block">
											회원가입<i class="icon-circle-right2 ml-2"></i>
										</a>
									</div> --%>
								</div>
							</div>
							<div class="col-md-6 d-md-block d-none text-center align-self-center p-3">
	                    		<div class="card-content">
			                        <img class="img-fluid" src="${contextName}/images/login.png" alt="branding logo">
				                </div>
	                    	</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>