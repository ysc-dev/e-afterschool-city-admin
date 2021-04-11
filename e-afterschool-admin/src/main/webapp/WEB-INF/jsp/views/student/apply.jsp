<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-books"/>
  	<c:param name="title" value="수강 이력 조회"/>
  	<c:param name="middlename" value="학생 관리"/>
  	<c:param name="lastname" value="수강 이력 조회"/>
</c:import>

<div class="content">
	<div id="list_content" class="card mb-0">
		<div class="card-body">
			<div class="d-flex mt-1 mb-3">
				<label class="col-form-label font-weight-bold mr-3">검색조건 <i class="icon-arrow-right13"></i></label>
				<div class="mr-3">
					<input type="search" class="form-control" placeholder="학생 이름" name="name" autocomplete="off">
				</div>
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered" id="studentApplyTable">
				<thead class="text-center">
					<tr class="table-active">
						<th>번호</th>
						<th>안내장</th>
						<th>과목</th>
						<th>수강기간</th>
						<th>학생명</th>
						<th>소속(학교명)</th>
						<th>학년 반 번호</th>
						<th>연락처</th>
						<th>신청시간</th>
					</tr>
				</thead>
				<tbody class="text-center"></tbody>
			</table>
		</div>
	</div>
</div>
	
<script>
var StudentManager = function() {
	var DataTable = {
		ele: "#studentApplyTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "invitation.name" },
		    { data: "subject.name" },
		    { data: "subject.period" },
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
			this.table = Datatables.download(this.ele, this.option, " _TOTAL_ 개의 수강 이력이 있습니다.", null, [1,2,3,4,5,6,7,8]);
		},
		search: function() {
			var param = new Object();
			param.name = $("input[name=name]").val();
			Datatables.rowsAdd(this.table, contextPath + "/student/apply/search", param);
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
		},
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	StudentManager.init();
});
</script>