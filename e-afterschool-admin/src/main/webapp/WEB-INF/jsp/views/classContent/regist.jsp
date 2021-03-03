<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-stack"/>
  	<c:param name="title" value="수업 내용 등록"/>
  	<c:param name="content" value="정보 입력 후 하단 [등록]을 클릭하세요."/>
  	<c:param name="firstname" value="횟수별 수업 관리"/>
  	<c:param name="lastname" value="수업 내용 등록"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-10 mx-md-auto">
			<div class="card">
				<div class="card-header bg-transparent">
					<h5 class="card-title font-weight-bold">과목 : ${subject.name}</h5>
				</div>
				<form id="registForm" role="form" method="POST" enctype="multipart/form-data">
					<div class="card-body">
						<input type="hidden" value="${subject.id}" name="subjectId"/>
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">내&nbsp;&nbsp;&nbsp;&nbsp;용 :</label>
							<div class="col-md-7">
								<textarea class="form-control" name="content" rows="3" required></textarea>
							</div>
						</div>
						
						<div class="form-group row mt-4">
							<label class="col-md-3 col-form-label text-md-right">첨부파일 :</label>
							<div class="col-md-7">
								<input type="file" class="file-input" data-show-upload="false" name="files" 
									accept="video/mp4, image/*, .pdf, .hwp" multiple="multiple" data-fouc required>
								<span class="form-text text-muted">
									※ 이미지 파일 업로드 가능합니다.<br>
									※ 동영상은 최대 20Mb, 영상확장자가 MP4일 때 업로드 가능합니다.<br>
									※ PDF 파일 업로드 가능합니다.<br>
									※ 한글 파일 업로드 가능합니다.
								</span> 
							</div>
						</div>
					</div>
					<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
						<button type="submit" class="btn bg-teal-400 mr-3 px-4"><i class="icon-pencil5 mr-2"></i>등 록</button>
						<a href="list" class="btn btn-light px-4"><i class="icon-list mr-2"></i>목 록</a>
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
       		swalInit.fire({
   				title: "수업 내용 등록이 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				location.replace(contextPath + "/classContent/list");
   			});
       	},
        error: function(response) {
        	swalInit.fire({title: "수업 내용 등록을 실패하였습니다.", type: "error"})
        }
	});
});
</script>