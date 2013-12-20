function overrideBrowserNativeInputs() {
	$('input').customInput();
	$(".bonita_form_field.list_container select").each(function() {
		resizeSelect($(this).parent());
	});
}

function forceResizeSelect(e) {
	resizeSelect(e);
}

function resizeSelect(f) {
	//init vars
	var listContainer = $(f);
	var arrowBgWidth = 35;
	var paddingRight = 10;
	var browserElevatorWidth = 15;
	var containerWidth = listContainer.outerWidth();
	var maxAvailableWidth = 0;
	var listWidth = 0;
	var list = $("select", listContainer);
	list.css("width","");
	listContainer.css("width","");
	//get the label
	var label = listContainer.siblings(".bonita_form_label:first");

	// if the label is not found with the class bonita_form_label, let's try with portal class .label
	if (label.length == 0 && listContainer.siblings(".label").length == 1) {
		label = listContainer.siblings(".label:first");
	}
	
	//if label position is top or bottom the max width of the select container will be the label width (100% of available space in the form width)
	if (label.hasClass("bonita_form_label_top") || label.hasClass("bonita_form_label_bottom")) {
		maxAvailableWidth = label.outerWidth();
	}//else if label position is left or right the max width of the select container will be 66% of available space in the form width because the label takes 33%
	else {
		maxAvailableWidth = label.outerWidth() * 2;
	}

	// transform unique choice select into a multiple to get the final size
	list.prop("multiple", true);
	//get the width
	listWidth = list.outerWidth();

	// revert to unique choice the select box
	list.prop("multiple", false);

	
	//if select has options
	if ($("option", list).length > 0) {
		list.outerWidth(listWidth + paddingRight + arrowBgWidth + browserElevatorWidth);
		var newListWidth = list.outerWidth();
		// set the container width to list width including arrow width & padding if the max available width is not reached
		if (newListWidth < maxAvailableWidth) {
			listContainer.css("width", newListWidth - arrowBgWidth);
		} else {
			//if the width is higher set to max available width
			listContainer.css("width", maxAvailableWidth);
		}
	} else {
		// if there is no option or options has no value, set only the arrow width
		list.outerWidth(arrowBgWidth * 2);
		listContainer.css("width", arrowBgWidth);
	}
}