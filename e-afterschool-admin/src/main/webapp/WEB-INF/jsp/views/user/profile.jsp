<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-user" />
  	<c:param name="title" value="사용자 정보" />
  	<c:param name="lastname" value="My profile" />
</c:import>

<div class="container content">
	<div class="card mt-2">
		<form:form id="updateForm" modelAttribute="user" action="${pageContext.request.contextPath}/user/update">
			<div class="card-body">
				<div class="form-group row mt-3">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">사용자 이름 :</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="name" value="${user.name}" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">사용자 ID :</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="userId" value="${user.userId}" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">비밀번호 :</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">비밀번호 확인 :</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="passwordCheck">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">이메일 :</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="email" value="${user.email}" placeholder="예) example@gmail.com">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">전화번호 :</label>
					<div class="col-lg-4">
						<input type="text" class="form-control" name="tel" value="${user.tel}" placeholder="'-' 없이 입력하세요.">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-lg-4 col-form-label text-right font-weight-bold">권한 :</label>
					<div class="col-lg-4">
						<form:select class="form-control form-control-select2" path="role" items="${userRoles}">
						</form:select> 
					</div>
				</div>
			</div>
			<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
				<button type="submit" class="btn bg-blue mr-3 px-4"><i class="icon-pencil5 mr-2"></i>수 정</button>
			</div>
		</form:form>
	</div>
</div>

<script>
$('#updateForm').submit(function(e) {
	e.preventDefault();
	
	var form = $(this);
	var url = form.attr('action');
	
	$.ajax({
       	url: url,
		type: "PUT",
       	data: form.serializeObject(),
       	success: function(response) {
       		swal({
   				title: "사용자 정보가 수정 되었습니다.", 
   				type: "success"
   			});
       	},
        error: function(response) {
        	swal({title: "사용자 정보 수정을 실패하였습니다.", type: "error"})
        }
	});
});
</script>