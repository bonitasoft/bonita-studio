$(function() {
	$('input').customInput();
	$(".bonita_form_field.list_container").each(function(i,e){
		resizeSelect(e);
	});
});
function resizeSelect(e){
	var listContainer = $(e);
	var arrowBgWidth = 35;
	var containerWidth = listContainer.outerWidth();
	var list = listContainer.find('select:first');
	var listWidth = list.outerWidth();
	if(listWidth>arrowBgWidth){
		listContainer.css("width", listWidth+arrowBgWidth);
	}else{
		listContainer.css("width", arrowBgWidth);
	}
	list.css("width",  $(e).outerWidth()+arrowBgWidth);
}