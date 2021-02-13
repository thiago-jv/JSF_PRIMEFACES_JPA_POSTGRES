jQuery(document).ready(function(jQuery) {
	// Chamada da funcao upperText(); ao carregar a pagina
	upperText();
});
// Funcao que faz o texto ficar em uppercase
function upperText() {
	// Para tratar o colar
	jQuery(".up").bind('paste', function(e) {
		var el = jQuery(this);
		setTimeout(function() {
			var text = jQuery(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
	// Para tratar quando � digitado
	jQuery(".up").keypress(function() {
		var el = jQuery(this);
		setTimeout(function() {
			var text = jQuery(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
}

if (jQuery.browser.mozilla) {
	// Para tratar quando  digitado
	jQuery(".up").keyup(function() {
		var el = jQuery(this);
		setTimeout(function() {
			var text = jQuery(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
}
if (jQuery.browser.msie || jQuery.browser.safari || jQuery.browser.chrome) {
	// Para tratar quando  digitado
	jQuery(".up").keypress(function() {
		var el = jQuery(this);
		setTimeout(function() {
			var text = jQuery(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
}
