<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp">
  	<c:param name="icon" value="icon-envelop2"/>
  	<c:param name="title" value="SMS 발송"/>
  	<c:param name="lastname" value="SMS 발송"/>
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-6 mx-md-auto">
			<div class="card">
				<form id="registForm" role="form" method="POST" action="${pageContext.request.contextPath}/sms/regist">
					<div class="card-header bg-transparent">
						<span class="font-weight-bold">선택된 과목을 신청한 학생들에게 SMS 발송</span>
					</div>
					<div class="card-body">
						<div class="form-group row mt-3">
							<label class="col-md-3 col-form-label text-md-right">내&nbsp;&nbsp;&nbsp;&nbsp;용 :</label>
							<div class="col-md-7">
								<textarea id="content" class="form-control" name="content" 
									rows="7" required placeholder="내용을 입력하세요."></textarea>
								<div class="d-flex justify-content-between mt-2">
									<!-- <span id="sms_cnt">(0 / 200)</span> -->
									<span id="sms_bytes">0 bytes</span>
									<span id="sms_info1" class="text-info">단문 (건수당 1건 차감)</span>
									<span id="sms_info2" class="text-warning display-none">장문 (건수당 3건 차감)</span>
								</div>
							</div>
						</div>
						
						<div class="form-group row mt-4">
							<label class="col-md-3 col-form-label text-md-right">안내장 :</label>
							<div class="col-md-7">
								<select class="form-control form-control-select2" name="invitation" id="invitationSelect">
									<c:forEach var="invitation" items="${invitations}" varStatus="status">
										<option value="${invitation.id}">${invitation.name}(${invitation.city.name})</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">과&nbsp;&nbsp;&nbsp;&nbsp;목 :</label>
							<div class="col-md-7">
								<select class="form-control form-control-select2" name="subjectId" id="subjectSelect">
									<c:forEach var="subject" items="${subjects}" varStatus="status">
										<option value="${subject.id}">${subject.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<div class="card-footer bg-white d-flex justify-content-center align-items-center py-3">
						<button type="submit" class="btn bg-blue-400 mr-3 px-5"><i class="icon-paperplane mr-2"></i>보내기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	String.prototype.getBytes = function() {
	    var contents = this;
	    var str_character;
	    var int_char_count;
	    var int_contents_length;
	 
	    int_char_count = 0;
	    int_contents_length = contents.length;
	 
	    for (k = 0; k < int_contents_length; k++) {
	        str_character = contents.charAt(k);
	        if (escape(str_character).length > 4)
	            int_char_count += 2;
	        else
	            int_char_count++;
	    }
	    return int_char_count;
	}

	String.prototype.getStringFromByteLength = function( byteLength ) {
	    var contents = this;
	    var str_character;
	    var int_char_count;
	    var int_contents_length;
	     
	    var returnValue = "";
	     
	    int_char_count = 0;
	    int_contents_length = contents.length;
	     
	    for (k = 0; k < int_contents_length; k++) {
	        str_character = contents.charAt(k);
	        if (escape(str_character).length > 4)
	            int_char_count += 2;
	        else
	            int_char_count++;
	         
	        if ( int_char_count > byteLength ) {
	            break;
	        }
	        returnValue += str_character;
	    }
	    return returnValue;
	}
	
	$('#content').on('keyup', function() {
		var content = $(this).val();
		var bytes = content.getBytes();
        //$('#sms_cnt').html("(" + content.length + " / 200)");
        $('#sms_bytes').html(content.getBytes() + " bytes");

		if (bytes > 90) {
			$('#sms_info1').addClass('display-none');
			$('#sms_info2').removeClass('display-none');
		} else {
			$('#sms_info2').addClass('display-none');
			$('#sms_info1').removeClass('display-none');
		}
        
        /* if (content.length > 200) {
            $(this).val(content.substring(0, 200));
            $('#sms_cnt').html("(200 / 200)");
        } */
    });
	
	$("#registForm").submit(function(e) {
		e.preventDefault();
		var form = $(this);
		var url = form.attr('action');
		var data = form.serializeObject();

		$.ajax({
			type: "POST",
	       	url: url,
	       	data: data,
	       	success: function(response) {
	       		swalInit.fire({
      				title: "SMS 발송이 완료 되었습니다.", 
      				type: "success",
      				position: 'top'
      			}).then(function(e) {
      				//location.href = contextPath + "/home/${city.id}";
      			});
	       	},
	       	error: function(response) {
	       		swalInit.fire({title: "SMS 발송을 실패하였습니다.", type: "error", position: 'top'});
	       	}
		});
	});
});
</script>