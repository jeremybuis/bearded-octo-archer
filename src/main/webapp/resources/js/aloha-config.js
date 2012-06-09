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