<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-stack"/>
  	<c:param name="title" value="횟수별 수업 관리"/>
  	<c:param name="lastname" value="횟수별 수업 관리"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-3">
			<div class="card">
				<div class="card-header bg-transparent header-elements-inline">
					<span class="font-size-md font-weight-bold">검색 조건</span>
					<div class="header-elements">
						<div class="list-icons">
	                		<a class="list-icons-item" data-action="collapse"></a>
	               		</div>
	          		</div>
				</div>
				<div class="card-body">
					<div class="font-size-xs text-muted mb-2">안내장 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="invitation" id="invitationSelect">
							<c:forEach var="invitation" items="${invitations}" varStatus="status">
								<option value="${invitation.id}">${invitation.name}(${invitation.city.name})</option>
							</c:forEach>
						</select>
					</div>
					<div class="font-size-xs text-muted mb-2">과목 선택</div>
					<div class="form-group">
						<select class="form-control form-control-select2" name="subject" id="subjectSelect">
							<c:forEach var="subject" items="${subjects}" varStatus="status">
								<option value="${subject.id}">${subject.name}</option>
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
					<button type="button" id="classRegistBtn" class="btn bg-blue-400 btn-block"><i class="icon-pencil5 mr-2"></i>수업 내용 등록</button>
				</div>
			</div>
		</div>
		
		<div class="col-md-9">
			<div class="card">
				<div class="card-header">
					<h5 class="card-title">수업 내용 목록</h5>
				</div>
				<table class="table table-bordered" id="classTable">
					<thead class="text-center">
						<tr class="table-active">
							<th>번호</th>
							<th>등록일</th>
							<th>사업내용</th>
							<th>미리보기</th>
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

<!-- 이미지 및 비디오 모달창 -->
<div id="fileModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <h5 class="modal-title">
                    <i class="icon-images2 mr-2"></i>수업내용 파일 미리보기
                </h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body text-center">
               <div id="file-viewer"></div>
            </div>
        </div>
    </div>
</div>
	
<script>
$("#invitationSelect").change(function() {
	$("#subjectSelect").empty();
	
	$.ajax({
		url: contextPath + "/classContent/subject/list",
		type: "GET",
		data: {"invitationId": $(this).val()},
		success: function(response) {
			if (response.length > 0) {
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

$("#classRegistBtn").click(function() {
	var subjectId = $("#subjectSelect option:selected").val();
	if (subjectId && subjectId != 0) {
		location.replace(contextPath + "/classContent/regist?subjectId=" + subjectId);
	} else {
		swalInit.fire({title: "과목을 선택하세요.<br>없을 경우 먼저 등록하세요.", type: "warning"});
	}
});

var ClassManager = function() {
	var DataTable = {
		ele: "#classTable",
		table: null,
		option: {
			columns: [{
		    	width: "8%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { 
		    	width: "16%",
		    	render: function(data, type, row, meta) {
				    return moment(row.createDate).format('YYYY-MM-DD HH:mm:ss');
			    }
			}, 
		    { data: "content" },
		    { 
		    	width: "10%",
			    render: function(data, type, row, meta) {
				    var type = '';
				    row.uploadedFiles.forEach(function(file, index) {
					    if (file.fileType == 'IMAGE') {
					    	type = file.fileType;
					    }
				    });

				    if (type == 'IMAGE') {
				    	return '<button type="button" class="btn btn-outline bg-primary text-primary-600 btn-sm font-weight-bold"'
				    	+ 'onClick="ClassManager.imageModal(' + row.id + ')">미리보기</button>';
				    } else {
					    return '없음';
				    }
			    }
			},
		    { 
		    	width: "10%",
			    render: function(data, type, row, meta) {
			    	return '<button type="button" class="btn btn-outline bg-info text-info-600 btn-sm font-weight-bold"'
			    		+ 'onClick="ClassManager.download(' + row.id + ')">다운로드</button>';
			    }
			},
		    {
		    	width: "6%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-danger text-danger-600 btn-sm"'
    					+ 'onClick="ClassManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, [1]);
			this.search();
		},
		search: function() {
			var param = new Object();
			param.invitationId = $("select[name=invitation]").val();
			param.subjectId = $("select[name=subject]").val();
			Datatables.rowsAdd(this.table, contextPath + "/classContent/search", param);
		}
	}

	var Control = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}
	
	return {
		init: function() {
			DataTable.init();
			Control();
		},
		imageModal: function(id) {
			$("#file-viewer").empty();
			
			$.ajax({
		        url: contextPath + "/classContent/file/get",
		        type: "GET",
		        data: {"id" : id},
		        success : function(response) {
			        response.uploadedFiles.forEach(function(file, index) {
				        var fileContent;
				        if (file.fileType == 'IMAGE') {
				        	fileContent = '<img src="' + contextPath + '/uploads/class/' + file.fileName + '" class="img-fluid"/>';
				        } else if (file.fileType == 'VIDEO') {
				        	fileContent = '<div class="card-img embed-responsive embed-responsive-16by9">'
					        	+ '<video class="embed-responsive-item" src="' + contextPath + '/uploads/class/' + file.fileName + '" controls></video>'
					        	+ '</div>';
				        }
						
				        $("#file-viewer").append(fileContent);
                    });
		        	$("#fileModal").modal();
		        }
		    });
		},
		download: function(id) {
			$.ajax({
		        url: contextPath + "/classContent/file/get",
		        type: "GET",
		        data: {"id" : id},
		        success : function(response) {
			        response.uploadedFiles.forEach(function(file, index) {
				        var url = contextPath + '/uploads/class/' + file.fileName;
				        
				        const a = document.createElement('a');
				        a.style.display = 'none';
				        a.href = url;
				        a.download = file.fileName;
				        document.body.appendChild(a);
				        a.click();
				        
				        window.URL.revokeObjectURL(url);
                    });
		        }
		    });
		},
		_delete: function(id) {
			deleteCommon(contextPath + "/classContent/delete", id, "수업 내용", DataTable);
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	ClassManager.init();
});
</script>