<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet" type="text/css" />

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-info22"/>
  	<c:param name="title" value="공지사항"/>
  	<c:param name="content" value="상세정보 입니다."/>
  	<c:param name="firstname" value="공지사항"/>
  	<c:param name="lastname" value="상세정보"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-10 mx-md-auto">
			<div class="card notice-detail">
				<div class="card-header bg-primary-400">
					<h3 class="card-title">${notice.title}</h3>
				</div>
				<div class="card-body">
					<div class="subject">
						<label class="font-weight-bold">작성자 :</label>
						<label class="ml-1">${notice.userName}</label>

						<fmt:formatDate var="createDate" value="${localDateTimeFormat.parse(notice.createDate)}" pattern="yyyy-MM-dd HH:mm:ss"/>
						
						<label class="font-weight-bold ml-4">날짜 :</label>
						<label class="ml-1">${createDate}</label> 
						
						<label class="font-weight-bold ml-4">조회수 :</label>
						<label class="ml-1">${notice.hit}</label>
					</div>
					<%-- <div class="file">
						<i class="icon-file-text2 mr-1"></i>
						<label class="font-weight-bold mr-2">첨부파일 :</label>
						<a href="#" class=" ">${notice.uploadedFile.fileName}</a>
					</div> --%>
					<div class="content">
						<p>${notice.content}</p>
					</div>
				</div>
				<div class="card-footer bg-white d-flex justify-content-between align-items-center py-3">
					<div>
						<a href="${pageContext.request.contextPath}/notice/list" class="btn btn-light px-3"><i class="icon-list mr-2"></i>목 록</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/notice/update/${notice.id}" class="btn bg-teal-400 mr-2 px-3">
							<i class="icon-pencil5 mr-2"></i>수 정
						</a>
						<button type="button" class="btn bg-danger-400 px-3" onclick="deleteNotice(${notice.id})">
							<i class="icon-trash mr-2"></i>삭 제
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function deleteNotice(id) {
	swal({
        title: "공지사항을 삭제하시겠습니까?",
        type: "warning",
        confirmButtonText: "삭제",
        confirmButtonClass: "btn btn-danger",
        showCancelButton: true, 
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: contextPath + "/notice/delete",
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			swal({
	       				title: "공지사항이 삭제 되었습니다.", 
	       				type: "success"
	       			}).then(function(e) {
	       				location.replace(contextPath + "/notice/list");
	       			});
	           	},
	            error: function(response) {
	            	swal({title: "공지사항 삭제를 실패하였습니다.", type: "error"})
	            }
	    	}); 
    	}
    });
}
</script>