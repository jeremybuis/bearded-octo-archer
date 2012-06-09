(function(window, undefined) {
	var jQuery = window.jQuery;
	if (window.Aloha === undefined || window.Aloha === null) {
		window.Aloha = {};
	}
	window.Aloha.settings = {
		bundles : {
			common:"http://aloha-editor.org/aloha-0.20/plugins/common/"
		},
		logLevels: {'error': true, 'warn': true, 'info': true, 'debug': false},
		errorhandling : false,
		ribbon: false,
		"i18n": {
			"current": "en"
		},
		"plugins": {
			"format": {
				config : [ 'b', 'i', 'p', 'sub', 'sup' ],
				editables : {
				},
				// those are the tags that will be cleaned when clicking "remove formatting"
				removeFormats : [ 'strong', 'em', 'b', 'i', 'cite', 'q', 'code', 'abbr', 'del', 'sub', 'sup']
			}
		},
		"sidebar": {
			disabled: true
		}
	};
})(window);

Aloha.ready(function() {
	
	Aloha.jQuery('.editable').aloha();

	Aloha.bind('aloha-editable-deactivated', function(event) {
		var content = Aloha.activeEditable.getContents();
		var contentId = Aloha.activeEditable.obj[0].id;
		var pageId = window.location.pathname;

		// textarea handling -- html id is "xy" and will be "xy-aloha" for the aloha editable
		if ( contentId.match(/-aloha$/gi) ) {
			contentId = contentId.replace( /-aloha/gi, '' );
		}

		console.log(contentId);

		//var formSelector = '.form-' + contentId;
		var formSelector = '.form-test';

		console.log(formSelector);
		
		//set our hidden inputs
		$(formSelector + " :eq(1)").val(contentId);
		$(formSelector + " :eq(2)").val(content);
		//$(formSelector + " :eq(4)").click();

		console.log($(formSelector).serialize());

		$.post(window.location.href, $(formSelector).serialize());
	});
});