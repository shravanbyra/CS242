$(document).ready(function(){
	var selectedWeapon = $('.weapon-offer').click(function(e){
		e.preventDefault();
		
		selectedWeapon.removeClass('selected');
		$(this).addClass('selected');
		console.log($(this));
		
		var attrs = $(this).children();
		var type = attrs[0].innerHTML;
		var skin = attrs[1].innerHTML;
		var wear = attrs[2].innerHTML;
		
	});
});