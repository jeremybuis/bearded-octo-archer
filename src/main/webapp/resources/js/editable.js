var cmpa = window.cmpa || {};
cmpa.ela = cmpa.ela || {};

if (undefined === typeof Aloha) {
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

			var formSelector = '.form-' + contentId;
			console.log(formSelector);
			//set our hidden inputs
			$(formSelector + " :eq(1)").val(contentId);
			$(formSelector + " :eq(2)").val(content);
			//$(formSelector + " :eq(4)").click();
			$.post(window.location.href, $(formSelector).serialize());
		});
	});
}
