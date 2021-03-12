<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-history"/>
  	<c:param name="title" value="수강 취소 조회"/>
  	<c:param name="content" value="수강 취소 목록"/>
  	<c:param name="lastname" value="수강 취소 조회"/>
</c:import>

<div class="content">
	<div id="list_content" class="card mb-0">
		<div class="card-body">
			<div class="d-flex mt-1 mb-3">
				<label class="col-form-label mr-2">안내장 :</label>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="invitation" data-width="380" id="invitationSelect">
						<c:forEach var="invitation" items="${invitations}" varStatus="status">
							<option value="${invitation.id}">${invitation.name}(${invitation.city.name})</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-form-label mr-2">과목 :</label>
				<div class="mr-3">
					<select class="form-control select-search" name="subject" data-width="300" id="subjectSelect">
						<option value="">- 전 체 -</option>
						<c:forEach var="subject" items="${subjects}" varStatus="status">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-form-label mr-2">학교 :</label>
				<div class="mr-3">
					<select class="form-control select-search" name="school" data-width="160">
						<option value="">- 전 체 -</option>
						<c:forEach var="school" items="${schools}" varStatus="status">
							<option value="${school.name}">${school.name}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-form-label mr-2">학년 :</label>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="grade" data-width="100">
						<option value="0">- 전 체 -</option>
						<c:forEach var="item" begin="1" end="6" step="1">
							<option value="${item}">${item} 학년</option>
						</c:forEach>
					</select>
				</div>
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered" id="applyCancelTable">
				<thead class="text-center">
					<tr class="table-active">
						<th>순번</th>
						<th>과목</th>
						<th>수강기간</th>
						<th>운영시간</th>
						<th>유형</th>
						<th>강사명</th>
						<th>학생명</th>
						<th>소속(학교명)</th>
						<th>학년 반 번호</th>
						<th>연락처</th>
						<th>취소시간</th>
					</tr>
				</thead>
				<tbody class="text-center"></tbody>
			</table>
		</div>
	</div>
</div>
	
<script>
$("#invitationSelect").change(function() {
	$("#subjectSelect").empty();
	
	$.ajax({
		url: contextPath + "/apply/subject/list",
		type: "GET",
		data: {"invitationId": $(this).val()},
		success: function(response) {
			if (response.length > 0) {
				$('#subjectSelect').append($('<option>', {
				    value: "",
				    text: "- 전 체 -"
				}));
				
				$.each(response, function (i, item) {
					$('#subjectSelect').append($('<option>', {
					    value: item.id,
					    text: item.name
					}));
				});
			} else {
				$('#subjectSelect').append($('<option>', {
				    value: 0,
				    text: "데이터 없음"
				}));
			}
       	}
	}); 
});

var ApplyCancelManager = function() {
	var DataTable = {
		ele: "#applyCancelTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "subject.name" },
		    { data: "subject.period" },
		    { data: "subject.time" },
		    { data: "subject.week" },
		    { data: "subject.teacher.name" },
		    { data: "student.name" },
		    { data: "student.school" },
		    { 
		    	render: function(data, type, row, meta) {
		    		return row.student.grade + "학년 " + row.student.classType + "반 " + row.student.number + "번";
		    	}
		    },
		    { data: "student.tel" },
		    { 
			    render: function(data, type, row, meta) {
	    			return moment(new Date(row.createDate)).format("YYYY-MM-DD HH:mm:ss");
	    		}
		    }]
		},
		init: function() {
			this.table = Datatables.download(this.ele, this.option, " _TOTAL_ 개의 수강취소가 있습니다.", null, [1,2,3,4,5,6,7,8,9,10]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.invitationId = $("select[name=invitation]").val();
			param.subjectId = $("select[name=subject]").val();
			param.school = $("select[name=school]").val();
			param.grade = $("select[name=grade]").val();
			Datatables.rowsAdd(this.table, contextPath + "/apply/cancel/search", param);
		}
	}
	
	var searchControl = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	ApplyCancelManager.init();
});
</script>