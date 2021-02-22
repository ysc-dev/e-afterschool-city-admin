/**
 * input maxLength 설정
 * @param object
 * @returns
 */
function maxLengthCheck(object){
	if (object.value.length > object.maxLength){
      	object.value = object.value.slice(0, object.maxLength);
    }    
}

/**
 * 등록 공통 기능
 * @returns
 */
function registCommon(url, object, name, SettingManager) {
    $.ajax({
       	url: url,
		type: "POST",
       	data: object, // serializes the form's elements.
       	success: function(response) {
       		swalInit.fire({
   				title: name + " 등록 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				SettingManager.success();
   			});
       	},
        error: function(response) {
        	swalInit.fire({title: name + " 등록을 실패하였습니다.", type: "error"})
        }
	});
}

/**
 * 모달에서 등록 공통 기능
 * @returns
 */
function registModalCommon(url, object, name, Datatable, modalId) {
	$.ajax({
       	url: url,
		type: "POST",
       	data: object,
       	success: function(response) {
       		$("#" + modalId).modal('hide');
       		
       		swalInit.fire({
   				title: name + " 등록 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				Datatable.search();
   			});
       	},
        error: function(response) {
        	swalInit.fire({title: name + " 등록을 실패하였습니다.", type: "error"})
        }
	});
}

/**
 * 등록 후 목록화면으로 이동
 * @returns
 */
function registToMove(form, name, href) {
	$.ajax({
       	url: form.attr('action'),
		type: "POST",
       	data: form.serializeObject(),
       	success: function(response) {
       		swalInit.fire({
   				title: name + "가(이) 등록 되었습니다.",
   				type: "success"
   			}).then(function(e) {
   				location.href = href;
   			});
       	},
        error: function(response) {
        	swalInit.fire({title: name + " 등록을 실패하였습니다.", type: "error"})
        }
	});
}

/**
 * 모달에서 수정 공통 기능
 * @returns
 */
function updateModalCommon(url, object, name, Datatable, modalId) {
	$.ajax({
       	url: url,
		type: "PUT",
       	data: object,
       	success: function(response) {
       		$("#" + modalId).modal('hide');
       		
       		swalInit.fire({
   				title: name + "가(이) 수정 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				Datatable.search();
   			});
       	},
        error: function(response) {
        	swalInit.fire({title: name + " 수정을 실패하였습니다.", type: "error"})
        }
	});
}

/**
 * 삭제 공통 기능
 * @returns
 */
function deleteCommon(url, id, name, Datatable, title) {
	swalInit.fire({
        title: title ? title : "선택된 " + name + "을 삭제하시겠습니까?",
        type: "warning",
        confirmButtonText: "삭제",
        confirmButtonClass: "btn btn-danger",
        showCancelButton: true, 
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: url,
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			swalInit.fire({
	       				title: name + "가(이) 삭제 되었습니다.", 
	       				type: "success"
	       			}).then(function(e) {
	       				Datatable.search();
	       			});
	           	},
	            error: function(response) {
	            	if (isEmpty(response.responseText)) {
	            		swalInit.fire({title: name + " 삭제를 실패하였습니다.", type: "error"});
	            	} else {
	            		swalInit.fire({title: response.responseText, type: "error"});
	            	}
	            	
	            }
	    	}); 
    	}
    });
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}