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
       		swal({
   				title: name + " 등록이 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				SettingManager.success();
   			});
       	},
        error: function(response) {
        	swal({title: name + " 등록을 실패하였습니다.", type: "error"})
        }
	});
}

function registToMove(form, name, href) {
	$.ajax({
       	url: form.attr('action'),
		type: "POST",
       	data: form.serializeObject(),
       	success: function(response) {
       		swal({
   				title: name + " 등록이 되었습니다.",
   				type: "success"
   			}).then(function(e) {
   				location.href = href;
   			});
       	},
        error: function(response) {
        	swal({title: name + " 등록을 실패하였습니다.", type: "error"})
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
       		
       		swal({
   				title: name + " 수정 되었습니다.", 
   				type: "success"
   			}).then(function(e) {
   				Datatable.search();
   			});
       	},
        error: function(response) {
        	swal({title: name + " 수정을 실패하였습니다.", type: "error"})
        }
	});
}

/**
 * 삭제 공통 기능
 * @returns
 */
function deleteCommon(url, id, name, Datatable, title) {
	swal({
        title: title ? title : "선택된 " + name + "을 삭제하시겠습니까?",
        type: "warning",
        confirmButtonText: "삭제",
        confirmButtonClass: "btn btn-danger m-btn m-btn--custom",
        showCancelButton: true, 
        cancelButtonText: "취소",
    }).then(function(e) {
    	if (e.value) {
    		$.ajax({
	    		url: url,
	    		type: "DELETE",
	    		data: {"id": id},
	    		success: function(response) {
	    			Datatable.search();
	           	},
	            error: function(response) {
	            	swal({title: name + " 삭제를 실패하였습니다.", type: "error"})
	            }
	    	}); 
    	}
    });
}