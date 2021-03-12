<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-clipboard3"/>
  	<c:param name="title" value="만족도 및 설문 조사 조회"/>
  	<c:param name="lastname" value="만족도 및 설문 조사 조회"/>
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
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered" id="surveyTable">
				<thead class="text-center">
					<tr class="table-active">
						<th>순번</th>
						<th>과목</th>
						<th>강사</th>
						<th>점수</th>
						<th>등록시간</th>
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

var SurveyManager = function() {
	var DataTable = {
		ele: "#surveyTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "subject.name" },
		    { data: "subject.teacher.name" },
		    { data: "subject.score" },
		    { 
			    render: function(data, type, row, meta) {
	    			return moment(new Date(row.createDate)).format("YYYY-MM-DD HH:mm:ss");
	    		}
		    }]
		},
		init: function() {
			this.table = Datatables.download(this.ele, this.option, " _TOTAL_ 개의 수강 신청이 있습니다.", null, [1,2,3,4,5,6,7,8,9,10]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.subjectId = $("select[name=subject]").val();
			Datatables.rowsAdd(this.table, contextPath + "/survey/search", param);
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
	SurveyManager.init();
});
</script>