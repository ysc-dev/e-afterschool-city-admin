<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-users"/>
  	<c:param name="title" value="학생 관리"/>
  	<c:param name="lastname" value="학생 관리"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-2">
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-md font-weight-bold">검색 조건</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<div class="font-size-xs text-muted mb-2">학교 선택</div>
					<div class="form-group">
						<select class="form-control select-search" name="school">
							<option value="">- 전 체 -</option>
							<c:forEach var="school" items="${schools}" varStatus="status">
								<option value="${school.name}">${school.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="font-size-xs text-muted mb-2">학년 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="grade">
							<option value="0">- 전 체 -</option>
							<c:forEach var="item" begin="1" end="6" step="1">
								<option value="${item}">${item} 학년</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input type="search" class="form-control" placeholder="학생 이름" name="name" autocomplete="off">
					</div>
					<button type="button" id="searchBtn" class="btn bg-teal-400 btn-block">
						<i class="icon-search4 mr-2"></i>검 색
					</button>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">등록된 학생 목록</h5>
				</div>
				<div class="table-responsive">
					<table class="table" id="studentTable">
						<thead class="text-center">
							<tr class="table-active">
								<th>번호</th>
								<th>이름</th>
								<th>소속(학교 명)</th>
								<th>학년</th>
								<th>학급(반)</th>
								<th>번호</th>
								<th>연락처</th>
								<th>개인정보동의</th>
								<th>주민번호</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody class="text-center"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
	
<script>
var StudentManager = function() {
	var DataTable = {
		ele: "#studentTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "name" },
		    { data: "school" },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.grade + " 학년";
		    	}
		    },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.classType + " 반";
		    	}
		    },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.number + " 번";
		    	}
		    },
		    { data: "tel" },
		    { 
		    	render: function(data, type, row, meta) {
	    			return row.agree ? "동의" : "미동의";
	    		} 
		 	},
		    { data: "residentNumber" },
		    {
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn btn-outline bg-primary text-primary-800 btn-sm" ' +
		    				'onClick="StudentManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>' +
    					'<button type="button" class="btn btn-outline bg-danger text-danger-800 btn-sm" ' + 
	    					'onClick="StudentManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>'
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.download(this.ele, this.option, " _TOTAL_ 명의 학생이 있습니다.", [7, 8], [1,2,3,4,5,6,7,8]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.school = $("select[name=school]").val();
			param.grade = $("select[name=grade]").val();
			param.name = $("input[name=name]").val();
			Datatables.rowsAdd(this.table, contextPath + "/student/search", param);
		}
	}
	
	var searchControl = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}
	
	var controlStudentData = function() {
		$('#updateStudentForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "학생", DataTable, "updateStudentModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
			controlStudentData();
		},
	 	modal: function(id) {
	 		$.ajax({
	    		url: contextPath + "/student/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateStudentForm input[name="id"]').val(response.id);
	    			$('#updateStudentForm input[name="name"]').val(response.name);
	    			$('#updateStudentForm select[name="school"]').val(response.school).trigger('change');
	    			$('#updateStudentForm select[name="grade"]').val(response.grade).trigger('change');
	    			$('#updateStudentForm select[name="classType"]').val(response.classType).trigger('change');
	    			$('#updateStudentForm select[name="number"]').val(response.number).trigger('change');
	    			$('#updateStudentForm input[name="tel"]').val(response.tel);
	    			$("#updateStudentModal").modal();
	           	}
	    	}); 
	 	},
		_delete: function(id) {
        	deleteCommon(contextPath + "/student/delete", id, "학생", DataTable);
        }
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	StudentManager.init();
});
</script>