<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-users"/>
  	<c:param name="title" value="학교 관리"/>
  	<c:param name="lastname" value="학교관리"/>
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
					<div class="font-size-xs text-muted mb-2">타입 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="school">
							<option value="">- 전 체 -</option>
							<c:forEach var="type" items="${schoolTypes}" varStatus="status">
								<option value="${type}">${type.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input type="search" class="form-control" placeholder="학교 이름" name="name" autocomplete="off">
					</div>
					<button type="button" id="searchBtn" class="btn bg-blue-400 btn-block">
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
					<form id="registForm" action="${pageContext.request.contextPath}/school/regist" method="post">
						<div class="form-group">
							<label>학교 이름:</label>
							<input type="text" name="name" class="form-control" placeholder="학교 이름" required>
						</div>
						<div class="form-group">
							<label>학생 수:</label>
							<input type="number" name="number" class="form-control" placeholder="학생 수" required>
						</div>
						<div class="form-group">
							<label>학교 타입:</label>
							<select class="form-control form-control-select2" name="schoolType">
								<c:forEach var="type" items="${schoolTypes}" varStatus="status">
									<option value="${type}">${type.name}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn bg-teal-400 btn-block"><i class="icon-paperplane mr-2"></i>학교 추가</button>
					</form>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">학교 목록</h5>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered text-nowrap" id="schoolTable">
						<thead class="text-center">
							<tr class="table-active">
								<th>번호</th>
								<th>학교 명</th>
								<th>학생 수</th>
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
		    {
		    	width: "10%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
    				 + 'onClick="SchoolManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
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
			param.schoolType = $("select[name=schoolType]").val();
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
			
		    registCommon(url, form.serializeObject(), "학교", SchoolManager);
		});
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		_delete: function(id) {
			
		},
		modal: function(id) {
			
		},
		success: function() {
			DataTable.search();
			$('#registForm input[name="name"]').val("");
        	$('#registForm input[name="number"]').val("");
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SchoolManager.init();
});
</script>