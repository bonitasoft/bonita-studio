/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

$(function() {
	$.fn.wizard = function(action) {
		// Init
		if ($.isNull(action)) {
			return this.each(function(){
				var wizard = $(this);
				var pages = $('.wizardpage', wizard);
				
				pages.not(':first').hide();
			});
		}

		// actions
		var actions = {
				'next':function(){
					var step = $('.wizardpage:visible', this); 
					step.fadeOut(200, function(){
						step.next().fadeIn(200);
					})
				},
				'previous':function(){
					var step = $('.wizardpage:visible', this); 
					step.fadeOut(200, function(){
						step.prev().fadeIn(200);
					})
				},
				'finish':function(){
					
				},
				'cancel':function(){
					
				}
		}
		
		// actions call
		if (!$.isNull(actions[action])) {
			var wizard  = this;
			if (!wizard.is('form')) {
				wizard = wizard.closest('form');
			}
			actions[action].call(wizard);
		}
		
		return this;
	}
	
	$.uiManager.addMaker(function(context){
		$('form:has(.wizardpage)', context).wizard();
	})
});