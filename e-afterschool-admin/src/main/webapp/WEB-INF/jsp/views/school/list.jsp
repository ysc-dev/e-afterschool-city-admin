<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-users"/>
  	<c:param name="title" value="학교 관리"/>
  	<c:param name="lastname" value="학교 관리"/>
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
					<div class="font-size-xs text-muted mb-2">캠퍼스 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="city">
							<option value="NONE">- 전 체 -</option>
							<c:forEach var="city" items="${cities}" varStatus="status">
								<option value="${city.name}">${city.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="font-size-xs text-muted mb-2">타입 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="school">
							<option value="NONE">- 전 체 -</option>
							<c:forEach var="type" items="${schoolTypes}" varStatus="status">
								<option value="${type}">${type.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input type="search" class="form-control" placeholder="학교 이름" name="name" autocomplete="off">
					</div>
					<button type="button" id="searchBtn" class="btn bg-teal-400 btn-block">
						<i class="icon-search4 mr-2"></i>검 색
					</button>
				</div>
			</div>
			
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-md font-weight-bold">학교 등록</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<%-- <form id="registForm" action="${pageContext.request.contextPath}/school/regist" method="post">
						<div class="form-group">
							<label>학교 이름:</label>
							<input type="text" name="name" class="form-control" placeholder="예) 가고파초등학교" required>
						</div>
						<div class="form-group">
							<label>학생 수:</label>
							<input type="number" name="number" class="form-control" placeholder="학생 수" required>
						</div>
						<div class="form-group">
							<label>지 역:</label>
							<select class="form-control form-control-select2" name="city">
								<c:forEach var="city" items="${cities}" varStatus="status">
									<option value="${city.name}">${city.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>학교 타입:</label>
							<select class="form-control form-control-select2" name="schoolType">
								<c:forEach var="type" items="${schoolTypes}" varStatus="status">
									<option value="${type}">${type.name}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn bg-blue-400 btn-block"><i class="icon-paperplane mr-2"></i>학교 추가</button>
					</form> --%>
					
					<button type="button" class="btn bg-blue-400 btn-block" onClick="SchoolManager.regist()">
						<i class="icon-pencil5 mr-2"></i>학교 등록
					</button>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">학교 목록</h5>
				</div>
				<table class="table table-bordered" id="schoolTable">
					<thead class="text-center">
						<tr class="table-active">
							<th>번호</th>
							<th>학교 명</th>
							<th>학생 수</th>
							<th>지역</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody class="text-center"></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="registSchoolModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>학교 정보 등록
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<form id="registForm" action="${pageContext.request.contextPath}/school/regist" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">학교 명 : </label>
						<div class="col-md-6">
							<input type="text" name="name" class="form-control" placeholder="예) 가고파초등학교" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">학생 수 : </label>
						<div class="col-md-6">
							<input type="number" name="number" class="form-control" placeholder="학생 수" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">지 역 : </label>
						<div class="col-md-6">
							<select class="form-control form-control-select2" name="city">
								<c:forEach var="city" items="${cities}" varStatus="status">
									<option value="${city.name}">${city.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">학교 타입 : </label>
						<div class="col-md-6">
							<select class="form-control form-control-select2" name="schoolType">
								<c:forEach var="type" items="${schoolTypes}" varStatus="status">
									<option value="${type}">${type.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary mr-2 px-3"><i class="icon-pencil5 mr-2"></i>등 록</button>
					<button type="button" class="btn btn-light px-3" data-dismiss="modal"><i class="icon-cross2 mr-2"></i>닫 기</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="updateSchoolModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>학교 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<form id="updateForm" action="${pageContext.request.contextPath}/school/update" class="form-horizontal">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">학교 명 : </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="name" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">학생 수 : </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="number" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">지 역 : </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="city" placeholder="예) 창원">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary mr-2 px-3"><i class="icon-pencil5 mr-2"></i>수 정</button>
					<button type="button" class="btn btn-light px-3" data-dismiss="modal"><i class="icon-cross2 mr-2"></i>닫 기</button>
				</div>
			</form>
		</div>
	</div>
</div>
	
<script>
var SchoolManager = function() {
	var DataTable = {
		ele: "#schoolTable",
		table: null,
		option: {
			columns: [{
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { data: "name" }, 
		    { data: "number" }, 
		    { data: "city" }, 
		    {
		    	width: "10%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
    				 + 'onClick="SchoolManager.update(' + row.id + ')"><i class="icon-pencil7"></i></button>'
    				 + '<button type="button" class="btn btn-outline bg-danger text-danger-600 btn-sm" '
    				 + 'onClick="SchoolManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, [1]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.city = $("select[name=city]").val();
			param.schoolType = $("select[name=school]").val();
			param.name = $("input[name=name]").val();
			Datatables.rowsAdd(this.table, contextPath + "/school/search", param);
		}
	}

	var Control = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});

		$("#registForm").submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');

		    registModalCommon(url, form.serializeObject(), "학교", DataTable, "registSchoolModal");
		});

		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "학교", DataTable, "updateSchoolModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		_delete: function(id) {
			deleteCommon(contextPath + "/school/delete", id, "학교", DataTable);
		},
		regist: function() {
			$("#registSchoolModal").modal();
		},
		update: function(id) {
			$.ajax({
	    		url: contextPath + "/school/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$('#updateForm input[name="number"]').val(response.number);
	    			$('#updateForm input[name="city"]').val(response.city);
	    			$("#updateSchoolModal").modal();
	           	}
	    	}); 
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SchoolManager.init();
});
</script>