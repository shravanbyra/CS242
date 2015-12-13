$(document).ready(function(){
	var header = $('#header');
	var navPanel = $('#nav-panel');
	
	header.hover(function(){
			navPanel.slideDown(150)
		},
		function(){
			navPanel.hide(250);
	});
});