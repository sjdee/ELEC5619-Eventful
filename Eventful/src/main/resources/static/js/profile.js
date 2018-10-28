$(document).ready(function(){
	$('.slider').slider();
	
	const starTotal = 5;
	const rating = $("#rating").data("value");
	const starPercentage = (rating / starTotal) * 100;
	const starPercentageRounded = `${(Math.round(starPercentage / 10) * 10)}%`;
	document.querySelector('#rating .stars-inner').style.width = starPercentageRounded; 
});