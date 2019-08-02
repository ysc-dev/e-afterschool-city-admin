<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-display4" />
  	<c:param name="title" value="과목 그룹 관리" />
  	<c:param name="firstname" value="과목 관리" />
  	<c:param name="lastname" value="과목 그룹 관리" />
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-3">
			<div class="card">
				<div class="card-header bg-white">
					<h6 class="card-title">
						<i class="icon-cog3 mr-2"></i>과목 그룹 설정
					</h6>
				</div>
				<form id="registGroupForm" action="${pageContext.request.contextPath}/subject/group/regist">
					<div class="card-body">
						<div class="form-group">
							<label>그룹 이름:</label>
							<input type="text" class="form-control" name="name" placeholder="과목 그룹 이름" autocomplete="off" required>
						</div>
						<div class="form-group mb-2">
							<label>설명 :</label>
							<textarea rows="5" class="form-control" name="description" placeholder="과목 그룹에 대한 설명" required></textarea>
						</div>
					</div>
					<div class="card-footer bg-white d-flex justify-content-center align-items-center">
						<button type="submit" class="btn bg-blue-400 px-3"><i class="icon-plus-circle2 mr-2"></i>그룹 추가</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-9">
			<div class="card">
				<div class="card-header bg-white">
					<h6 class="card-title">
						<i class="icon-list mr-2"></i>과목 그룹 리스트
					</h6>
				</div>
				<div class="card-body table-no-responsive">
					<table class="table table-bordered" id="subjectGroupTable">
						<thead class="text-center">
							<tr class="table-active">
								<th>번호</th>
								<th>이름</th>
								<th>설명</th>
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
	
<div id="updateGroupModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>과목 그룹 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<form id="updateGroupForm" action="${pageContext.request.contextPath}/subject/group/update" class="form-horizontal">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row">
						<label class="col-form-label col-md-3 text-md-right">그룹 이름 : </label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name" placeholder="과목 그룹 이름" required>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-3 text-md-right">설 명 : </label>
						<div class="col-md-7">
							<textarea rows="4" class="form-control" name="description" placeholder="과목 그룹에 대한 설명" required></textarea>
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
var SettingManager = function() {
	var Datatable = {
		ele: "#subjectGroupTable",
		table: null,
		option: {
			columns: [{
		    	width: "12%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { 
		    	width: "16%",
		    	data: "name" 
		    }, 
		    { data: "description" }, 
		    {
		    	width: "15%",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn btn-outline bg-primary text-primary-800 btn-sm" ' +
		    			'onClick="SettingManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>' +
    					'<button type="button" class="btn btn-outline bg-danger text-danger-800 btn-sm" ' + 
		    				'onClick="SettingManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>'
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, 0);
			this.search();
		},
		search: function() {
			var param = new Object();
			Datatables.rowsAdd(this.table, contextPath + "/subject/group/search", param);
		}
	}
	
	var controlGroupData = function() {
		$('#registGroupForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		    registCommon(url, form.serializeObject(), "과목 그룹", SettingManager);
		});
		
		$('#updateGroupForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "과목 그룹", Datatable, "updateGroupModal");
		}); 
	}
	
	return {
        init: function() {
        	Datatable.init();
        	controlGroupData();
        },
        _delete: function(id) {
        	deleteCommon(contextPath + "/subject/group/delete", id, "과목 그룹", Datatable, "과목 그룹을 삭제하면 설정된 과목도 삭제가 됩니다.<br>그래도 삭제하시겠습니까?");
        },
        modal: function(id) {
        	$.ajax({
	    		url: contextPath + "/subject/group/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateGroupForm input[name="id"]').val(response.id);
	    			$('#updateGroupForm input[name="name"]').val(response.name);
	    			$('#updateGroupForm textarea[name="description"]').val(response.description);
	    			$("#updateGroupModal").modal();
	           	}
	    	}); 
        },
        success: function() {
        	Datatable.search();
        	$('#registGroupForm input[name="name"]').val("");
        	$('#registGroupForm textarea[name="description"]').val("");
        }
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SettingManager.init();
});
</script>