<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-envelop2"/>
  	<c:param name="title" value="SMS 발송"/>
  	<c:param name="lastname" value="SMS 발송"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-6 mx-md-auto">
			<div class="card">
				<form id="registForm" role="form" method="POST">
					<div class="card-body">
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">내&nbsp;&nbsp;&nbsp;&nbsp;용 :</label>
							<div class="col-md-7">
								<textarea class="form-control" name="content" rows="3" required placeholder="내용을 입력하세요."></textarea>
							</div>
						</div>
						
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">선&nbsp;&nbsp;&nbsp;&nbsp;택 :</label>
						</div>
					</div>
					
					<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
						<button type="submit" class="btn bg-blue-400 mr-3 px-5"><i class="icon-paperplane mr-2"></i>보내기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
	
});
</script>