/*******************************************************************************************************
 * 위젯 공통 기능
*******************************************************************************************************/
var CommonWidget = function() {
	// Uniform
    var _componentUniform = function() {
    	if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        $('.form-check-input-styled').uniform();
    }
	
	var _componentSelect2 = function() {
		if (!$().select2) {
        	console.warn('Warning - select2.min.js is not loaded.');
            return;
        }
    	
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity,
            width: '100%',
            placeholder: 'Select a Data...'
        });
        
        $('.select2-size').select2({
            minimumResultsForSearch: Infinity,
            width: '90'
        });
        
        $('.select-search').select2();
        
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };
    
    var _componentSwal = function() {
    	swal.setDefaults({
	        buttonsStyling: false,
	        confirmButtonClass: 'btn btn-primary',
	        cancelButtonClass: 'btn btn-light'
	    });
    };
    
    var _componentJQuery = function() {
    	/** form 데이터들을 JSON 형식으로 변환 */
    	jQuery.fn.serializeObject = function() { 
    		var obj = null; 
    		try { 
    			if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
				var arr = this.serializeArray(); 
				if(arr){ 
					obj = {}; 
					jQuery.each(arr, function() { 
						obj[this.name] = this.value; }); 
					} 
				} 
			} catch(e) { 
				alert(e.message); 
			} finally {} 
			return obj; 
		}
    };
    
    // Bootstrap file upload
    var _componentFileUpload = function() {
    	if (!$().fileinput) {
            console.warn('Warning - fileinput.min.js is not loaded.');
            return;
        }
    	
    	 // Modal template
        var modalTemplate = '<div class="modal-dialog modal-lg" role="document">\n' +
            '  <div class="modal-content">\n' +
            '    <div class="modal-header align-items-center">\n' +
            '      <h6 class="modal-title">{heading} <small><span class="kv-zoom-title"></span></small></h6>\n' +
            '      <div class="kv-zoom-actions btn-group">{toggleheader}{fullscreen}{borderless}{close}</div>\n' +
            '    </div>\n' +
            '    <div class="modal-body">\n' +
            '      <div class="floating-buttons btn-group"></div>\n' +
            '      <div class="kv-zoom-body file-zoom-content"></div>\n' + '{prev} {next}\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>\n';
        
        // Buttons inside zoom modal
        var previewZoomButtonClasses = {
            toggleheader: 'btn btn-light btn-icon btn-header-toggle btn-sm',
            fullscreen: 'btn btn-light btn-icon btn-sm',
            borderless: 'btn btn-light btn-icon btn-sm',
            close: 'btn btn-light btn-icon btn-sm'
        };
        
     	// Icons inside zoom modal classes
        var previewZoomButtonIcons = {
            prev: '<i class="icon-arrow-left32"></i>',
            next: '<i class="icon-arrow-right32"></i>',
            toggleheader: '<i class="icon-menu-open"></i>',
            fullscreen: '<i class="icon-screen-full"></i>',
            borderless: '<i class="icon-alignment-unalign"></i>',
            close: '<i class="icon-cross2 font-size-base"></i>'
        };
     	
     	// File actions
        var fileActionSettings = {
            zoomClass: '',
            zoomIcon: '<i class="icon-zoomin3"></i>',
            dragClass: 'p-2',
            dragIcon: '<i class="icon-three-bars"></i>',
            removeClass: '',
            removeErrorClass: 'text-danger',
            removeIcon: '<i class="icon-bin"></i>',
            indicatorNew: '<i class="icon-file-plus text-success"></i>',
            indicatorSuccess: '<i class="icon-checkmark3 file-icon-large text-success"></i>',
            indicatorError: '<i class="icon-cross2 text-danger"></i>',
            indicatorLoading: '<i class="icon-spinner2 spinner text-muted"></i>'
        };
    	
    	$('.file-input').fileinput({
            browseLabel: '찾아보기',
            uploadUrl: "http://localhost", // server upload action
            uploadAsync: true,
            initialPreview: [],
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialCaption: "파일이 선택되지 않았습니다.",
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
    };
    
    return {
        init: function() {
        	_componentUniform();
        	_componentSelect2();
        	_componentSwal();
        	_componentJQuery();
        	_componentFileUpload();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	CommonWidget.init();
});