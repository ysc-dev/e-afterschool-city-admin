<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<link href="${pageContext.request.contextPath}/css/notice.css" rel="stylesheet" type="text/css" />

<sec:authentication property="principal" var="user"></sec:authentication>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-info22"/>
  	<c:param name="title" value="과목별 공지사항"/>
  	<c:param name="content" value="상세정보 입니다."/>
  	<c:param name="firstname" value="과목 관리"/>
  	<c:param name="middlename" value="과목별 공지사항"/>
  	<c:param name="lastname" value="상세정보"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-10 mx-md-auto">
			<div class="card notice-detail">
				<div class="card-header bg-transparent">
					<h3 class="card-title">${subjectNotice.title}</h3>
				</div>
				<div class="card-body">
					<input type="hidden" id="noticeId" value="${subjectNotice.id}"/>
					<input type="hidden" id="subjectId" value="${subjectNotice.subjectId}"/>
					<div class="subject">
						<label class="font-weight-bold">작성자 :</label>
						<label class="ml-1">${subjectNotice.userName}</label>
						
						<label class="font-weight-bold ml-4">날짜 :</label>
						<fmt:formatDate var="createDate" value="${localDateTimeFormat.parse(subjectNotice.createDate)}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<label class="ml-1">${createDate}</label> 
						
						<label class="font-weight-bold ml-4">조회수 :</label>
						<label class="ml-1">${subjectNotice.hit}</label>
					</div>
					<div class="content">
						<p>${subjectNotice.content}</p>
					</div>
				</div>
				<div class="comment-body"> 
					<h6 class="mb-2 font-weight-bold">댓글 ${fn:length(comments)}개</h6>
					<div class="d-flex mt-2">
						<textarea class="form-control m-input" id="commentInput" rows="1"></textarea>
						<button type="button" id="commentRegistBtn" class="btn btn-info px-3 ml-3">댓글 추가</button>
					</div>
				</div>
				<c:if test="${fn:length(comments) > 0}">
					<div class="comment-list-body">
						<c:forEach var="comment" items="${comments}" varStatus="status">
							<div class="comment-list">
								<div class="media-title">
									<a href="#" class="text-primary-800 font-weight-bold">${comment.userName}</a>
									<fmt:formatDate var="createDate" value="${localDateTimeFormat.parse(comment.createDate)}" pattern="yyyy-MM-dd HH:mm:ss"/>
									<span class="text-muted ml-3">${createDate}</span>
									<c:if test="${user.id == comment.userId && user.name == comment.userName}">
										<a href="#" onclick="updateBtnClick(${comment.id})" class="text-primary font-weight-bold ml-4">수정</a>
										<a href="#" onclick="deleteComment(${comment.id})" class="text-danger font-weight-bold ml-2">삭제</a>
									</c:if>
								</div>
								<div id="content_${comment.id}">
									<p>${comment.content}</p>
								</div>
								<div id="update_${comment.id}" class="d-none mt-1">
									<div class="d-flex">
										<textarea class="form-control" id="updateInput_${comment.id}" rows="1"></textarea>
										<button type="button" onclick="updateComment(${comment.id})" class="btn btn-primary px-2 ml-3">수정</button>
										<button type="button" onclick="cancel(${comment.id})" class="btn btn-light px-2 ml-2">취소</button>
									</div>
								</div>
							</div>
							<c:if test="${status.last == false}">
								<hr class="my-0">
							</c:if>
						</c:forEach> 
					</div>
				</c:if>
				<div class="card-footer bg-white d-flex justify-content-between align-items-center py-3">
					<div>
						<a href="${pageContext.request.contextPath}/subject/notice/list/${subjectNotice.subjectId}" class="btn btn-light px-3">
							<i class="icon-list mr-2"></i>목 록
						</a>
					</div>
					<div>
						<button type="button" class="btn bg-danger-400 px-3" onclick="deleteNotice(${subjectNotice.id})">
							<i class="icon-trash mr-2"></i>삭 제
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
/** 댓글 추가 버튼 클릭 시 */
$("#commentRegistBtn").click(function() {
	var content = $("#commentInput").val();
	var noticeId = $("#noticeId").val();
	if (content) {
		$.ajax({
    		url: contextPath + "/comment/regist",
    		type: "POST",
    		data: {"subjectNoticeId": noticeId, "content": content},
    		success: function(response) {
    			swal({
           			title: "댓글이 추가되었습니다.",
       				type: "success"
       			}).then(function(e) {
       				location.reload();
       			});
    		},
    		error: function(response) {
            	swal({title: "댓글 추가을 실패하였습니다.", type: "error"})
            }
    	});
	} else {
		swal({title: "댓글 내용을 입력하세요.", type: "warning"});
	}
});

/** 댓글 수정 버튼 클릭 시 */
function updateBtnClick(id) {
	$.ajax({
  		url: contextPath + "/comment/get",
  		data: {"id": id},
  		type: "GET",
  		dataType: "json",
  		success: function(response) {
  			$("#content_" + id).addClass("d-none");
  			$("#update_" + id).removeClass("d-none");
  			$("#updateInput_" + id).val(response.content)
 		}
	});
}

function updateComment(id) {
	var content = $("#updateInput_" + id).val();
	if (content) {
		$.ajax({
      		url: contextPath + "/comment/update",
      		data: {"id": id, "content": content},
      		type: "PUT",
      		success: function(response) {
      			location.reload();
     		},
            error: function(response) {
            	swal({title: "댓글 수정을 실패하였습니다.", type: "error"})
            }
		});
	} else {
		swal({title: "댓글 내용을 입력하세요.", type: "warning"});
	}
}

function cancel(id) {
	$("#content_" + id).removeClass("d-none");
	$("#update_" + id).addClass("d-none");
	$("#updateInput_" + id).val("")l
}

/** 댓글 삭제 버튼 클릭시 */
function deleteComment(id) {
	swal({
        title: "삭제하시겠습니까?",
        type: "warning",
        confirmButtonText: "삭제",
        confirmButtonClass: "btn btn-danger",
        showCancelButton: true, 
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: contextPath + "/comment/delete",
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			location.reload();
	           	},
	            error: function(response) {
	            	swal({title: "댓글 삭제를 실패하였습니다.", type: "error"})
	            }
	    	}); 
    	}
    });
}

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
	    		url: contextPath + "/subject/notice/delete",
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			swal({
	       				title: "과목별 공지사항이 삭제 되었습니다.", 
	       				type: "success"
	       			}).then(function(e) {
	       				location.replace(contextPath + "/subject/notice/list/" + $('#subjectId').val());
	       			});
	           	},
	            error: function(response) {
	            	swal({title: "과목별  공지사항 삭제를 실패하였습니다.", type: "error"})
	            }
	    	}); 
    	}
    });
}
</script>