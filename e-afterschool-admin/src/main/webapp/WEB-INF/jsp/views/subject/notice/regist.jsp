<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-pencil7"/>
  	<c:param name="title" value="과목별 공지사항 등록"/>
  	<c:param name="content" value="제목과 내용을 입력해주세요."/>
  	<c:param name="firstname" value="과목 관리" />
  	<c:param name="middlename" value="과목별 공지사항"/>
  	<c:param name="lastname" value="과목별 공지사항 등록"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-10 mx-md-auto">
			<div class="card">
				<form id="registForm" role="form" method="POST" action="${pageContext.request.contextPath}/subject/notice/regist">
					<div class="card-body">
						<input type="hidden" id="subjectId" name="subjectId" value="${subject.id}"/>
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">과목 이름 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" value="${subject.name}" readonly>
							</div>
						</div>
						
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">제&nbsp;&nbsp;&nbsp;&nbsp;목 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="title" placeholder="" autocomplete="off" required>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">내&nbsp;&nbsp;&nbsp;&nbsp;용 :</label>
							<div class="col-md-7">
								<textarea class="form-control" name="content" rows="10" required></textarea>
							</div>
						</div>
					</div>
					<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
						<button type="submit" class="btn bg-teal-400 mr-3 px-4"><i class="icon-pencil5 mr-2"></i>저 장</button>
						<a href="${pageContext.request.contextPath}/subject/notice/list/${subject.id}" class="btn btn-light px-4">
							<i class="icon-list mr-2"></i>목 록
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
	
<script>
$('#registForm').submit(function(e) {
	e.preventDefault();

	var form = $(this);
    var url = form.attr('action');
    var formData = new FormData($("#registForm")[0]);

    $.ajax({
		type: "POST",
       	url: url,
       	data: formData,
       	processData: false,
       	contentType: false,
       	success: function(response) {
           	console.log(response);
       		swal({
   				title: "과목 별 공지사항 등록이 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				location.replace(contextPath + "/subject/notice/list/" + $('#subjectId').val());
   			});
       	},
        error: function(response) {
        	swal({title: "과목 별  공지사항 등록을 실패하였습니다.", type: "error"})
        }
	});
});
</script>