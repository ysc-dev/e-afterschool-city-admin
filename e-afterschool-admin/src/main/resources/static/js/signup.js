
const SignupValidation = function() {
	// Validation config
	const _componentValidation = function() {
		if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }
		
		$.extend( $.validator.messages, {
        	required: "필수 항목입니다.",
        	number: "유효한 숫자가 아닙니다."
        });
		
		$.validator.addMethod("validPhone", function(value, element) {
            value = value.replace(/\s+/g, "");
            return this.optional(element) || value.length > 9 &&
                    value.match(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/);
        }, "유효한 전화 번호를 입력하세요.");
		
		var validator = $('.form-validate').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            focusCleanup: true,
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('입력 확인.'); // remove to hide Success message
            	//label.removeClass('validation-invalid-label');
            },
            errorPlacement: function(error, element) {
                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo(element.parents('.form-check').parent());
                }
                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo(element.parent());
                }
                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo(element.parent().parent());
                }
                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
            	userId: {
            		required: true,
            		minlength: 4,
                    maxlength: 100,
                    remote: {
                        url: "user/checkDuplicateId",
                        type: "post"
                    }
            	},
            	name: {
            		required: true,
            		minlength: 2,
            		maxlength: 30
            	},
                password: {
                	required: true,
                    minlength: 4,
                    maxlength: 100
                },
                confirmPassword: {
                	required: true,
                    equalTo: "#password"
                },
                tel: {
                    required: true,
                    validPhone:true
                }
            },
            messages: {
            	userId: {
                    required: "강사 ID를 입력하세요.",
                    minlength: jQuery.validator.format("{0}자 이상 입력하세요."),
                    maxlength: jQuery.validator.format("{0}자 이하로 입력하세요."),
                    remote: "강사 ID가 이미 존재합니다.",
                },
            	name: {
            		required: "강사 이름을 입력하세요.",
            		minlength: jQuery.validator.format("{0} 자 이상 입력하세요.")
            	},
                password: {
                    required: "비밀번호를 입력하세요.",
                    minlength: jQuery.validator.format("{0} 자 이상 입력하세요.")
                },
                confirmPassword: {
                    required: "비밀번호 확인을 입력하세요.",
                    minlength: "{0}자 이상 입력하세요.",
                    equalTo: "위와 동일한 비밀번호를 입력하세요."
                }
            }
        });
    }
	
	return {
        init: function() {
            _componentValidation();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	SignupValidation.init();
});