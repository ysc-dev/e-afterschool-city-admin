<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-notification2"/>
  	<c:param name="title" value="안내장 관리"/>
  	<c:param name="lastname" value="안내장 관리"/>
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
					<div class="font-size-xs text-muted mb-2">지역 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="city">
							<option value="0">- 전 체 -</option>
							<c:forEach var="city" items="${cities}" varStatus="status">
								<option value="${city.id}">${city.name}</option>
							</c:forEach>
						</select>
					</div>
					<button type="button" id="searchBtn" class="btn bg-teal-400 btn-block">
						<i class="icon-search4 mr-2"></i>검 색
					</button>
				</div>
			</div>
			
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="text-uppercase font-size-md font-weight-bold">Action</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<a href="regist" class="btn bg-blue-400 btn-block"><i class="icon-pencil5 mr-2"></i>안내장 등록</a>
				</div>
			</div>
		</div>
		
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">안내장 목록</h5>
				</div>
				<table class="table table-bordered" id="invitationTable">
					<thead class="text-center">
						<tr class="table-active">
							<th>번호</th>
							<th>안내장 제목</th>
							<th>신청 마감일</th>
							<th>수강 신청 현황</th>
							<th>지역</th>
							<th>첨부파일</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody class="text-center"></tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="updateInvitationModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>안내장 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<form id="updateForm" class="form-horizontal" enctype="multipart/form-data" role="form"
				action="${pageContext.request.contextPath}/invitation/update/file">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row">
						<label class="col-md-3 col-form-label text-md-right">지역 선택 :</label>
						<div class="col-md-8">
							<select class="form-control form-control-select2" name="city">
								<c:forEach var="city" items="${cities}" varStatus="status">
									<option value="${city.id}">${city.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-3 text-md-right">안내장 제목 : </label>
						<div class="col-md-8">
							<input type="text" class="form-control" name="name" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-3 text-md-right">신청 마감일 : </label>
						<div class="col-md-8">
							<input type="text" class="form-control" name="deadlineDate" placeholder="예) 2019년 9월 30일" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-md-3 text-md-right">타 입 : </label>
						<div class="col-md-8">
							<select class="form-control form-control-select2" name="type">
								<c:forEach var="type" items="${invitationTypes}" varStatus="status">
									<option value="${type}">${type.type}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- <div class="form-group row mb-2">
						<label class="col-form-label col-md-3 text-md-right">설 명 : </label>
						<div class="col-md-7">
							<textarea rows="5" class="form-control" name="description"></textarea>
						</div>
					</div> -->
					<div class="form-group row mb-0">
						<label class="col-md-3 col-form-label text-md-right">첨부파일 :</label>
						<div class="col-md-8">
							<input type="file" class="file-input" data-show-upload="false" name="images" 
								accept="image/*" multiple="multiple" data-fouc>
							<span class="form-text text-muted">※ 이미지 파일만 업로드 가능합니다.</span>
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

<!-- 이미지 모달창 -->
<div id="imageModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <h5 class="modal-title">
                    <i class="icon-images2 mr-2"></i>안내장 첨부파일
                </h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
               <div id="image-viewer"></div>
            </div>
        </div>
    </div>
</div>
	
<script>
$('[name="deadlineDate"]').formatter({
    pattern: '{{9999}}년 {{99}}월 {{99}}일'
});

var InvitationManager = function() {
	var DataTable = {
		ele: "#invitationTable",
		table: null,
		option: {
			columns: [{
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { data: "name" }, 
		    { data: "deadlineDate" },
		    {
		    	render: function(data, type, row, meta) {
			    	if (row.type == "마감") {
			    		return '<label class="col-form-label text-warning">' + row.typeContent + '</label>';
			    	} else if (row.type == "수강신청") {
			    		return '<label class="col-form-label text-info-600">' + row.typeContent + '</label>';
			    	} else {
			    		return row.typeContent;
			    	}
		    	}
		    },
		    { data: "city.name" },
		    { 
		    	width: "10%",
			    render: function(data, type, row, meta) {
				    return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
				    	+ 'onClick="InvitationManager.imageModal(' + row.id + ')"><i class="icon-images2"></i></button>';
			    }
			},
		    {
		    	width: "10%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm"'
    				 + 'onClick="InvitationManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>';
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, [1]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.cityId = $("select[name=city]").val();
			Datatables.rowsAdd(this.table, contextPath + "/invitation/search", param);
		}
	}

	var Control = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});

		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			var formData = new FormData($("#updateForm")[0]);

		    $.ajax({
				type: "PUT",
		       	url: url,
		       	data: formData,
		       	processData: false,
		       	contentType: false,
		       	success: function(response) {
		       		$("#updateInvitationModal").modal('hide');
		       		
		       		swal({
		   				title: "안내장 수정 되었습니다.", 
		   				type: "success"
		   			}).then(function(e) {
		   				DataTable.search();
		   			});
		       	},
		        error: function(response) {
		        	swal({title: "안내장 수정을 실패하였습니다.", type: "error"})
		        }
			});
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/invitation/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm select[name="city"]').val(response.city.id).trigger('change');
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$('#updateForm input[name="deadlineDate"]').val(response.deadlineDate);
	    			$('#updateForm select[name="type"]').val(response.type).trigger('change');
	    			$('#updateForm textarea[name="description"]').val(response.description);
	    			$("#updateInvitationModal").modal();
	           	}
	    	}); 
		},
		imageModal: function(id) {
			$("#image-viewer").empty();
			
			$.ajax({
		        url: contextPath + "/invitation/file/get",
		        type: "GET",
		        data: {"id" : id},
		        success : function(response) {
			        $.each(response, function(index, file){ 
			        	var imageContent = `<img src="data:\${file.contentType};base64,\${file.content}" class="img-fluid"/>`;
				        $("#image-viewer").append(imageContent);
			        });

		        	$("#imageModal").modal();
		        }
		    });
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	InvitationManager.init();
});
</script>