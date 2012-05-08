(function(window, undefined) {
	var jQuery = window.jQuery
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
			},
			"align": {
				alignment: [ 'right', 'left', 'center', 'justify' ]
			},
			"list": {
				config : [ 'ul' ],
				editables : {
				}
			},
			"undo": {
				config: [],
				editables : {
				}
			},
			"contenthandler": {
			 	config: [],
			 	editables : {
			 	}
			 },
			"paste": {
				config: [ 'word', 'generic', 'sanitize'],
				editables : {
				}
			},
			"toolbar": {
				tabs : {
					// will generate a tab labeled "formatting"
					formatting : {
						// group "flow"
						flow : [ 'b', 'em', 'u' ]
					}
				}
			},
			"floatingmenu": {
				width: 630, // with of the floating menu; auto calculated when not set
				behaviour: 'float', // 'float' (default), 'topalign', 'append'
				element: document, // use with 'append' behaviour option: HTML DOM ID of the element the FM should get the position from
				pin: false, // boolean if set to true with behaviour 'append' the fm will be pinned to that position and scroll with the window
				draggable: true, // boolean
				marginTop: 10, // number in px
				horizontalOffset: 0, // number in px -- used with 'topalign' behaviour
				topalignOffset: 0, // number in px -- used with 'topalign' behaviour
			}
		},
		"sidebar": {
			disabled: true
		}
	};
})(window);