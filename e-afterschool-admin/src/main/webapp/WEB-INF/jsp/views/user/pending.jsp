<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-user" />
  	<c:param name="title" value="승인 대기 중" />
  	<c:param name="middlename" value="사용자 관리"/>
  	<c:param name="lastname" value="승인 대기 중" />
</c:import>

<div class="content">
	<div class="row">
		<c:forEach var="user" items="${users}" varStatus="status">
			<div class="col-3">
				<div class="card">
					<div class="card-body">
						<div class="font-size-lg font-weight-bold">${user.name}</div>
						
						<div class="mt-2 text-muted"> 
							아이디: ${user.userId}
						</div>
						<div class="text-muted">
							전화번호: ${user.tel}
						</div>
					</div>
					<div class="card-footer d-sm-flex justify-content-sm-center p-2">
						<button type="button" id="pendingBtn" class="btn bg-slate-300 btn-block mr-2" onclick="pending(${user.id}, '${user.name}')">
							승 인
						</button>
						<button type="button" class="btn btn-light btn-block mt-0" onclick="cancel(${user.id}, '${user.name}')">
							거 부
						</button>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script>
function pending(id, name) {
	swalInit.fire({
        title: "선택된 " + name + "를(을) 승인하시겠습니까?",
        type: "info",
        confirmButtonText: "승인",
        confirmButtonClass: "btn btn-primary",
        showCancelButton: true,
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: contextPath + "/user/pending",
	    		type: "PUT",
	    		data: {"id": id},
	    		success: function(response) {
	    			swalInit.fire({
	       				title: name + "가(이) 승인 되었습니다.", 
	       				type: "success"
	       			}).then(function(e) {
	       				location.href = contextPath + "/user/pending";
	       			});
	           	},
	            error: function(response) {
	            	swalInit.fire({title: "승인 요청을 실패하였습니다.", type: "error"});
	            }
	    	});
    	}
    });
}

function cancel(id, name) {
	swalInit.fire({
        title: "선택된 " + name + "를(을) 승인 거부하시겠습니까?",
        type: "warning",
        confirmButtonText: "거부",
        confirmButtonClass: "btn btn-danger",
        showCancelButton: true,
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: contextPath + "/user/pending/cancel",
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			swalInit.fire({
	       				title: name + "가(이) 승인 거부 되었습니다.", 
	       				type: "success"
	       			}).then(function(e) {
	       				location.href = contextPath + "/user/pending";
	       			});
	           	},
	            error: function(response) {
	            	swalInit.fire({title: "승인 거부 요청을 실패하였습니다.", type: "error"});
	            }
	    	});
    	}
    });
}
</script>