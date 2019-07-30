/* ------------------------------------------------------------------------------
 *
 *  # Login form with validation
 *
 * ---------------------------------------------------------------------------- */

var LoginValidation = function() {
	// Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        $('.form-input-styled').uniform();
    };
    
    // Validation config
    var _componentValidation = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }
        
        $.extend( $.validator.messages, {
        	required: "필수 항목입니다.",
        	number: "유효한 숫자가 아닙니다."
        });
        
        // Initialize
        var validator = $('.form-validate').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
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
            	username: {
            		minlength: 2
            	},
                password: {
                    minlength: 4
                }
            },
            messages: {
            	username: {
            		required: "사용자 아이디를 입력하세요.",
            		minlength: jQuery.validator.format("{0} 자 이상 입력하세요.")
            	},
                password: {
                    required: "비밀번호를 입력하세요.",
                    minlength: jQuery.validator.format("{0} 자 이상 입력하세요.")
                }
            }
        });
    };
    
    return {
        init: function() {
            _componentUniform();
            _componentValidation();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
    LoginValidation.init();
});