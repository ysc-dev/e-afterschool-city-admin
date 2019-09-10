<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-user-tie"/>
  	<c:param name="title" value="강사 관리"/>
  	<c:param name="lastname" value="강사관리"/>
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
					<div class="form-group">
						<input type="search" class="form-control" placeholder="강사 이름" name="name" autocomplete="off">
					</div>
					<button type="button" id="searchBtn" class="btn bg-teal-400 btn-block">
						<i class="icon-search4 mr-2"></i>검 색
					</button>
				</div>
			</div>
			
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-md font-weight-bold">강사 등록</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<form id="registForm" action="${pageContext.request.contextPath}/teacher/regist" method="post">
						<div class="form-group">
							<label>대표 강사 이름:</label>
							<input type="text" name="name" class="form-control" placeholder="강사 이름" autocomplete="off" required>
						</div>
						<div class="form-group">
							<label>강사 이름:</label>
							<input type="text" name="content" class="form-control" autocomplete="off" placeholder="강사 전부 입력하세요." required>
						</div>
						<div class="form-group">
							<label>연락처:</label>
							<input type="text" name="tel" class="form-control" autocomplete="off" placeholder="핸드폰 번호">
						</div>
						<div class="form-group">
							<label>이메일:</label>
							<input type="email" name="email" class="form-control" placeholder="이메일" autocomplete="off">
						</div>
						<%-- <div class="form-group">
							<label>성별:</label>
							<select class="form-control form-control-select2" name="sex">
								<c:forEach var="sex" items="${sexList}" varStatus="status">
									<option value="${sex}">${sex}</option>
								</c:forEach>
							</select>
						</div> --%>
						<button type="submit" class="btn bg-blue-400 btn-block"><i class="icon-paperplane mr-2"></i>강사 추가</button>
					</form>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">등록된 강사 목록</h5>
				</div>
				<table class="table table-bordered" id="teacherTable">
					<thead class="text-center">
						<tr class="table-active">
							<th>번호</th>
							<th>대표 강사 이름</th>
							<th>전체 강사 이름</th>
							<th>연락처</th>
							<th>이메일</th>
							<!-- <th>성별</th> -->
							<th>Actions</th>
						</tr>
					</thead>
					<tbody class="text-center"></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="updateTeacherModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>강사 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<form id="updateForm" action="${pageContext.request.contextPath}/teacher/update" class="form-horizontal">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">대표 강사 이름 : </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="name" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">전체 강사 이름 : </label>
						<div class="col-md-6">
							<input type="text" name="content" class="form-control" autocomplete="off" placeholder="강사 전부 입력하세요." required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">연락처 : </label>
						<div class="col-md-6">
							<input type="tel" class="form-control" name="tel" placeholder="핸드폰 번호" autocomplete="off"> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-4 text-md-right">이메일 : </label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="email" placeholder="이메일">
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
$('[name="tel"]').formatter({
    pattern: '{{999}}-{{9999}}-{{9999}}'
});

var TeacherManager = function() {
	var DataTable = {
		ele: "#teacherTable",
		table: null,
		option: {
			columns: [{
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { data: "name" }, 
		    { data: "content" }, 
		    { data: "tel" }, 
		    { data: "email" }, 
		    /* { data: "sex" }, */ 
		    {
		    	width: "10%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
    				 + 'onClick="TeacherManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
    				 + '<button type="button" class="btn btn-outline bg-danger text-danger-600 btn-sm" '
    				 + 'onClick="TeacherManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, [1], " _TOTAL_ 명의 강사가 있습니다.");
			this.search();
		},
		search: function() {
			var param = new Object();
			param.name = $("input[name=name]").val();
			Datatables.rowsAdd(this.table, contextPath + "/teacher/search", param);
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
			
		    registCommon(url, form.serializeObject(), "강사", TeacherManager);
		});

		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "강사", DataTable, "updateTeacherModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		_delete: function(id) {
			deleteCommon(contextPath + "/teacher/delete", id, "강사", DataTable);
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/teacher/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$('#updateForm input[name="content"]').val(response.content);
	    			$('#updateForm input[name="tel"]').val(response.tel);
	    			$('#updateForm input[name="email"]').val(response.email);
	    			$("#updateTeacherModal").modal();
	           	}
	    	}); 
		},
		success: function() {
			DataTable.search();
			$('#registForm input[name="name"]').val("");
			$('#registForm input[name="content"]').val("");
        	$('#registForm input[name="tel"]').val("");
        	$('#registForm input[name="email"]').val("");
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	TeacherManager.init();
});
</script>