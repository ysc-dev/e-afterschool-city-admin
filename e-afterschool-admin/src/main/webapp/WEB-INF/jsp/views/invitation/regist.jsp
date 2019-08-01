<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-pencil7"/>
  	<c:param name="title" value="안내장 등록"/>
  	<c:param name="content" value="정보 입력 후 하단 [등록]을 클릭하세요."/>
  	<c:param name="firstname" value="안내장 관리"/>
  	<c:param name="lastname" value="안내장 등록"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-7 mx-md-auto">
			<div class="card">
				<form id="registForm" role="form" method="POST">
					<div class="card-body">
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">지역 선택 :</label>
							<div class="col-md-7">
								<select class="form-control form-control-select2" name="cityId">
									<c:forEach var="city" items="${cities}" varStatus="status">
										<option value="${city.id}">${city.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">안내장 제목 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="name" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">신청 마감일 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="deadlineDate" placeholder="예) 2019년 9월 30일" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">설&nbsp;&nbsp;&nbsp;&nbsp;명 :</label>
							<div class="col-md-7">
								<textarea class="form-control" name="description" rows="7" placeholder="필수 입력 항목은 아닙니다."></textarea>
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
       		swal({
   				title: "안내장 등록이 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				location.replace(contextPath + "/invitation/list");
   			});
       	},
        error: function(response) {
        	swal({title: "공지사항 등록을 실패하였습니다.", type: "error"})
        }
	});
});
</script>