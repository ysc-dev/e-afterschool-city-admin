<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-clipboard3"/>
  	<c:param name="title" value="만족도 및 설문 조사 조회(학부모용)"/>
  	<c:param name="middlename" value="만족도 및 설문 조사 조회"/>
  	<c:param name="lastname" value="학부모용"/>
</c:import>

<div class="content">
	<div id="list_content" class="card mb-0">
		<div class="card-body">
			<div class="d-flex mt-1 mb-3">
				<label class="col-form-label mr-2">도시 :</label>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="city" data-width="120" id="citySelect">
						<c:forEach var="city" items="${cities}" varStatus="status">
							<option value="${city.id}">${city.name}</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-form-label mr-2">안내장 :</label>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="invitation" data-width="400" id="invitationSelect">
						<c:forEach var="invitation" items="${invitations}" varStatus="status">
							<option value="${invitation.id}">${invitation.name}(${invitation.city.name})</option>
						</c:forEach>
					</select>
				</div>
				<label class="col-form-label mr-2">과목 :</label>
				<div class="mr-3">
					<select class="form-control select-search" name="subject" data-width="300" id="subjectSelect">
						<option value="0">- 전 체 -</option>
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
						<th>Actions</th>
					</tr>
				</thead>
				<tbody class="text-center"></tbody>
			</table>
		</div>
	</div>
</div>

<div id="informationModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-notification2 mr-2"></i>만족도 및 설문 조사 결과
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<div class="form-group">
					<label class="col-form-label">1. 프로그램명 </label>
					<div class="ml-2">
						<span id="programName" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">2. 수업 시간을 정확하게 잘 지킵니까?</label>
					<div class="ml-2">
						<span id="value1" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">3. 학생의 질문에 성실하게 응답해 줍니까?</label>
					<div class="ml-2">
						<span id="value2" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">4. 프로그램 내용을 이해하기 쉽게 설명해 줍니까?</label>
					<div class="ml-2">
						<span id="value3" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">5. 수업 시간에 수업만 전념합니까?</label>
					<div class="ml-2">
						<span id="value4" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">6. 프로그램 운영과 관련된 안내가 이루어집니까?</label>
					<div class="ml-2">
						<span id="value5" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">7. 학습이나 활동하기에 적절한 프로그램의 내용과 분량입니까?</label>
					<div class="ml-2">
						<span id="value6" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">8. 학생의 출결사항 및 평가 결과를 안내합니까?</label>
					<div class="ml-2">
						<span id="value7" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">9. 프로그램에 적극 참여하도록 관심을 가지고 지도합니까?</label>
					<div class="ml-2">
						<span id="value8" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">10. 프로그램이 특기 적성 계발 및 실력 향상에 도움이 됩니까?</label>
					<div class="ml-2">
						<span id="value9" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">11. 앞으로 이 프로그램에 계속 참여하거나 다른 친구에게 권유하겠습니까?</label>
					<div class="ml-2">
						<span id="value10" class="font-weight-bold"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-form-label">12. 현재 진행되고있는 방과후 학교 프로그램 중 재개설 되었으면 하는 강좌</label>
					<div class="ml-2">
						<span id="value11" class="font-weight-bold"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
<script>
$("#citySelect").change(function() {
	$("#invitationSelect").empty();

	$.ajax({
		url: contextPath + "/survey/invitation/list",
		type: "GET",
		data: {"cityId": $(this).val()},
		success: function(response) {
			if (response.length > 0) {
				$.each(response, function (i, item) {
					$('#invitationSelect').append($('<option>', {
					    value: item.id,
					    text: item.name + '(' + item.city.name + ')'
					}));
				});

				$('#invitationSelect').val(response[0].id).trigger('change');
			} else {
				$('#invitationSelect').append($('<option>', {
				    value: -1,
				    text: "데이터 없음"
				}));
				$('#invitationSelect').val(-1).trigger('change');
			} 
       	}
	}); 
});

$("#invitationSelect").change(function() {
	$("#subjectSelect").empty();
	
	$.ajax({
		url: contextPath + "/apply/subject/list",
		type: "GET",
		data: {"invitationId": $(this).val()},
		success: function(response) {
			if (response.length > 0) {
				$('#subjectSelect').append($('<option>', {
				    value: 0,
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
				    value: -1,
				    text: "데이터 없음"
				}));
			}
       	}
	}); 
});

const SurveyManager = function() {
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
		    { data: "totalScore" },
		    { 
		    	width: "15%",
			    render: function(data, type, row, meta) {
	    			return moment(new Date(row.createDate)).format("YYYY-MM-DD HH:mm:ss");
	    		}
		    },{
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm" ' +
		    				'onClick="SurveyManager.modal(' + row.id + ')"><i class="icon-notification2"></i></button>';
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, " _TOTAL_ 개의 수강 신청이 있습니다.", null, [1,2,3,4,5,6,7,8,9,10]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.cityId = $("select[name=city]").val();
			param.subjectId = $("select[name=subject]").val();
			param.surveyType = 'Parents';
			Datatables.rowsAdd(this.table, contextPath + "/survey/search", param);
		}
	}
	
	const searchControl = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}

	const selectScore = function(value) {
		if (value == "SCORE10")
			return "매우만족(10)";
		else if (value == "SCORE8")
			return "만족(8)";
		else if (value == "SCORE6")
			return "보통(6)";
		else if (value == "SCORE4")
			return "불만(4)";
		else if (value == "SCORE0")
			return "매우불만(0)";
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/survey/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(survey) {
		    		$("#programName").html(survey.subject.name + " (" + survey.subject.teacher.name + ")");
		    		$("#value1").html(selectScore(survey.value1));
		    		$("#value2").html(selectScore(survey.value2));
		    		$("#value3").html(selectScore(survey.value3));
		    		$("#value4").html(selectScore(survey.value4));
		    		$("#value5").html(selectScore(survey.value5));
		    		$("#value6").html(selectScore(survey.value6));
		    		$("#value7").html(selectScore(survey.value7));
		    		$("#value8").html(selectScore(survey.value8));
		    		$("#value9").html(selectScore(survey.value9));
		    		$("#value10").html(selectScore(survey.value10));
		    		$("#value11").html(survey.subjects);
	    			$("#informationModal").modal();
	           	}
	    	}); 
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SurveyManager.init();
});
</script>