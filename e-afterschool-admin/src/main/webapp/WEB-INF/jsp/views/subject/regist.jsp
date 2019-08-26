<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-pencil7" />
  	<c:param name="title" value="수강 과목 등록" />
  	<c:param name="content" value="정보 입력 후 하단 [등록]을 클릭하세요." />
  	<c:param name="firstname" value="과목 관리" />
  	<c:param name="lastname" value="수강 과목 등록" />
</c:import>

<div class="content">
	<div class="card mt-2">
		<form id="registForm" role="form" method="post">
			<div class="card-body row mt-2">
				<div class="col-md-5 ml-auto mr-3">
					<div class="form-group">
						<label class="font-weight-bold">안내장 선택 :</label>
						<select class="form-control form-control-select2" name="invitation" required>
							<c:forEach var="invitation" items="${invitations}" varStatus="status">
								<option value="${invitation.id}">${invitation.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label class="font-weight-bold">이 름 :</label>
						<input type="text" class="form-control" name="name" placeholder="과목 이름" required autocomplete="off">
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">대 상 :</label>
								<select class="form-control form-control-select2" name="targetType" required>
									<c:forEach var="targetType" items="${targetTypes}" varStatus="status">
										<option value="${targetType}">${targetType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">대상 학년 :</label>
								<select class="form-control form-control-select2" name="gradeType">
									<c:forEach var="gradeType" items="${gradeTypes}" varStatus="status">
										<option value="${gradeType}">${gradeType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">운영 시간 :</label>
								<input type="text" class="form-control" name="time" placeholder="예) 16:00-18:00" required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">유 형 :</label>
								<input type="text" class="form-control" name="week" placeholder="예) 화,목,토" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="font-weight-bold">수업 장소 :</label>
						<textarea rows="4" class="form-control" name="location" placeholder="지명(주소)" required></textarea>
					</div>
				</div>
				
				<div class="col-md-5 mr-auto ml-3">
					<div class="form-group">
						<label class="font-weight-bold">캠퍼스 선택 :</label>
						<select class="form-control form-control-select2" name="subjectGroup" required>
							<c:forEach var="subjectGroup" items="${subjectGroups}" varStatus="status">
								<option value="${subjectGroup.id}">${subjectGroup.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label class="font-weight-bold">강사 선택 :</label>
						<select class="form-control form-control-select2" name="teacher" required>
							<c:forEach var="teacher" items="${teachers}" varStatus="status">
								<option value="${teacher.id}">${teacher.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">정 원(명) :</label>
								<input type="number" class="form-control" name="fixedNumber" required>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="font-weight-bold">수강 기간 :</label>
								<input type="text" class="form-control" name="period" placeholder="예) 7/1~9/28" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="font-weight-bold">재료비 및 교구비 :</label>
						<input type="text" class="form-control" name="cost" placeholder="예) 무료 또는 1만원" required>
					</div>
					<div class="form-group">
						<label class="font-weight-bold">과목 특징 :</label>
						<textarea rows="4" class="form-control" name="description" placeholder="과목 특징 설명" required></textarea>
					</div>
				</div>
			</div>
			<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
				<button type="submit" class="btn bg-blue-400 mr-3 px-5"><i class="icon-plus-circle2 mr-2"></i>등 록</button>
				<a href="list" class="btn btn-light px-5"><i class="icon-cross2 mr-2"></i>취 소</a>
			</div>
		</form>
	</div>
</div>
	
<script>
$('#registForm').submit(function(e) {
	e.preventDefault();
	registToMove($(this), "과목", contextPath + "/subject/list");
});
</script>