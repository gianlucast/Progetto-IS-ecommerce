var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

$(function (){
	var mousein=
	function(){
		$(this).css("opacity","0").addClass("over")
		.animate({height:"60%",width:"60%",opacity:"1"});
	}
	
	var mouseout=
		function(){$(this).removeClass("over")
		.css({height:"45%", width:"40%"})}
	
	$(".img").children().hover(mousein,mouseout);
});

