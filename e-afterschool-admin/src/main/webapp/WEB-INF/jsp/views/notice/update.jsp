<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet" type="text/css" />

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-info22"/>
  	<c:param name="title" value="공지사항 수정"/>
  	<c:param name="content" value="제목과 내용을 입력해주세요."/>
  	<c:param name="firstname" value="공지사항"/>
  	<c:param name="lastname" value="공지사항 수정"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-10 mx-md-auto">
			<div class="card">
				<form id="updateForm" role="form" method="POST" action="${pageContext.request.contextPath}/notice/update" 
					enctype="multipart/form-data">
					<div class="card-body">
						<input type="hidden" value="${notice.id}" name="id" />
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">지&nbsp;&nbsp;&nbsp;&nbsp;역 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" value="${notice.city}" readonly />
							</div>
						</div>
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">제&nbsp;&nbsp;&nbsp;&nbsp;목 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="title" value="${notice.title}" autocomplete="off" required>
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">내&nbsp;&nbsp;&nbsp;&nbsp;용 :</label>
							<div class="col-md-7">
								<textarea class="form-control" name="content" rows="8" required>${notice.content}</textarea>
							</div>
						</div>
						
						<div class="form-group row mt-4">
							<label class="col-md-3 col-form-label text-md-right">첨부파일 :</label>
							<div class="col-md-7">
								<input type="file" class="file-input" data-show-upload="false" name="images" 
									accept="image/*" multiple="multiple" data-fouc>
								<span class="form-text text-muted">※ 이미지 파일만 업로드 가능합니다.</span>
							</div>
						</div>
					</div>
					<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
						<button type="submit" class="btn bg-teal-400 mr-3 px-4"><i class="icon-pencil5 mr-2"></i>저 장</button>
						<a href="${pageContext.request.contextPath}/notice/list" class="btn btn-light px-4"><i class="icon-list mr-2"></i>목 록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
$('#updateForm').submit(function(e) {
	e.preventDefault();

	var form = $(this);
    var url = form.attr('action');
    var formData = new FormData($("#updateForm")[0]);

    $.ajax({
		type: "PUT",
       	url: url,
       	data: formData,
       	processData: false,
       	contentType: false,
       	success: function(response) {
       		swal({
   				title: "공지사항 수정이 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				location.replace(contextPath + "/notice/list");
   			});
       	},
        error: function(response) {
        	swal({title: "공지사항 수정을 실패하였습니다.", type: "error"})
        }
	});
});
</script>