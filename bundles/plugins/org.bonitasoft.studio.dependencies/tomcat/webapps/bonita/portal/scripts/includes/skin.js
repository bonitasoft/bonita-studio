/**
 * Copyright (C) 2011 BonitaSoft S.A. BonitaSoft, 32 rue Gustave Eiffel - 38000
 * Grenoble This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 2.0 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

$(function() {
	$.uiManager.addMaker(function(context) {
		if (context.is(".datatable")){
			// hack for datatable where there is not a label after the checkbox
			// or radio
			var dataTable = context;
			$("input[type=checkbox],input[type=radio]", context).each(function(i){
				if($("label", $(this).parent()).length>0){
					return;
				}
				var input = $(this);
				var inputId = this.id;
				var forValue = "";
				if(inputId!= ""){
					forvalue = inputId;
				}
				label = $('<label for="'+forvalue+'" >&nbsp;</label>');
				label.click(function(e){
					e.stopPropagation();
				});
				
				input.parent().append(label);
				
			});
			$('input', context).customInput();
			
			$(".formentry.select .input", context).each(function(i,e){
				resizeSelect(e);
			});
			$(".formentry.select .input select", context).each(function(i,e){
				$(e).change(function(e){
					resizeSelect($(e).parent());
				});
			});
			
			//manage all lines checked label style
			$(context).bind("cssChange",function(){
				var checkAllCheckbox = $(".th_checkboxes input", context);
				if(checkAllCheckbox.prop('checked')){
					$("label", checkAllCheckbox.parent()).addClass("checked");
				}else{
					$("label", checkAllCheckbox.parent()).removeClass("checked");
				}
			});
			
			
		}
	});
});

function resizeSelect(f){
	var listContainer = $(f);
	if(listContainer.hasClass("resized")){
		return;
	}
	var arrowBgWidth = 35;
	var paddingRight = 10;
	var containerWidth = listContainer.outerWidth();
	var list = listContainer.find('select:first');
	var selectedOption = listContainer.find('select:first').prop("selectedIndex"); // backup the selected index

	//get the label
	var label = listContainer.siblings(".bonita_form_label:first");
	if(label.length == 0) label = listContainer.siblings(".label:first");
	var maxAvailable = 0;
	//if label position is top or bottom the max width of the select container will be the label width (100% of available space in the form width)
	if(label.hasClass("bonita_form_label_top") || label.hasClass("bonita_form_label_bottom")){
		maxAvailable = label.outerWidth();
	}else if(label.hasClass("bonita_form_label_left") || label.hasClass("bonita_form_label_right")){
		//else if label position is left or right the max width of the select container will be 66% of available space in the form width because the label takes 33%
		maxAvailable = label.outerWidth() * 2;
	}else{//default
		maxAvailable = label.outerWidth();
	}
	
	// remove css width to calculate max size
	list.css("width","100%");
		
	// init vars to search the longer string in options
	var lengths = Array();
	var maxI = 0;
	var max = 0;
	
	// retreive max characters and index in options value
	list.find('option').each(function(index, element){
		if(index==0 || $(this).text().length > max){
			maxI = index;
			max = $(this).text().length;
		}
	});
	// select the option with the highest number of character
	list.prop("selectedIndex",maxI);
	
	//get the size of the highest option
	var listWidth = list.outerWidth();
	
	// reselect the initial option
	list.prop("selectedIndex",selectedOption);
	
	//set the with of the select to the max select width including the arrow width	
	list.outerWidth(listWidth+ arrowBgWidth*2);
	
	//if the options has value
	if(listWidth>0){
		// set the container width to list width including arrow width & padding if the max available width is not reached
		var newListContainerWidth = listWidth + arrowBgWidth + paddingRight;
		if(maxAvailable<newListContainerWidth){
			//if the width is higher set to max available width
			newListContainerWidth =  maxAvailable;
			listContainer.css("width", newListContainerWidth);
		}else{
			listContainer.css("width", listWidth+arrowBgWidth+paddingRight);
		}
	}else{
		// if there is no option or options has no value, set only the arrow width
		listContainer.css("width", arrowBgWidth);
	}
	listContainer.addClass("resized");
}