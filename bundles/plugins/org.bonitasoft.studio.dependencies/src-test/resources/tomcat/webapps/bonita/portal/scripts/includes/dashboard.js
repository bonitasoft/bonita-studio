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

	$.fn.dashboardWidget = function(method) {
		var widgets = this;
		
		 var methods = {
			open: function() {
				$('.widget-header .ui-icon', widgets)
					.removeClass('ui-icon-minusthick')
					.addClass('ui-icon-plusthick');
				$('.widget-content', widgets).slideDown();
            },
		 	close: function() {
				$('.widget-header .ui-icon', widgets)
					.addClass('ui-icon-minusthick')
					.removeClass('ui-icon-plusthick');
				$('.widget-content', widgets).slideUp();
		 	},
            toggle: function() {
				$('.widget-header .ui-icon', widgets)
					.toggleClass('ui-icon-minusthick')
					.toggleClass('ui-icon-plusthick');
				$('.widget-content', widgets).toggle();
            },
            openEdit: function() {
            },
            closeEdit: function() {
            },
            toggleEdit: function() {
            	
            }
        };

        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
    		return this.each(function(){
    			var widget = $(this);
    			widget.redirect(widget.attr('rel'));
    		});
        } else {
            $.error( 'Method "' +  method + '" does not exist in dashboardWidget plugin!');
        }
		
	};
	
	$.fn.dashboard = function() {
		var dashboard = $(this);
		

		// Initialize widgets
			$('.widget', dashboard).dashboardWidget();
			
		// Customize button
			$("#link_customize", dashboard).unbind('click').click(function() {
				switchCustomization();
				return false;
			});
		
		/**
		 * Change the dashboard to customization mode
		 * @private
		 */
		function openCustomization(){
			var widgets = $('.widget', dashboard);
			
			$('.column', dashboard)
				.sortable({
					connectWith: '.column'
				})
				.disableSelection()
			;

			widgets.addClass('ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-draggable');
			
			$('.widget-header', widgets)
				.addClass('ui-widget-header ui-corner-all')
				.prepend('<span class="ui-icon ui-icon-minusthick">');

			$('.widget-header .ui-icon', widgets).click(function() {
				var e = $(this);
				e
					.toggleClass('ui-icon-minusthick')
					.toggleClass('ui-icon-plusthick')
					.closest('.widget', widgets)
					.find(".widget-content").toggle();
			});
		
			/*Destroy widget on click*/
			$('.ui-dialog-titlebar-close', widgets).click(function() {
				$(this).closest('.widget').remove();
			});

			//load the customize panel
			$("#display_customize").load($("#link_customize").attr('href'), function() {
				
				//The button that allows to remove the rss widget
				$("#rss_remove").live('click', function() {
					$('#widget-5').remove();
				});
				//The button that allows to add calendar widget
				$("#calendar_add").live('click', function() {
					$('#widget-6').load('widgets/calendar.html');
				});
				
				//quicksearch in gadgets
				$('input#search_gadgets').live('click', function() {
					//when the user click, empty the val
					$('input#search_gadgets').val('');
				});
				$('input#search_gadgets').quicksearch('table.table_gadgets td');
				//Load lightbox
				$('.b_itemImage a').lightBox();
				
			});
		}
		
		/**
		 * Change the dashboard to read mode
		 * @private
		 */
		function closeCustomization(){
			
		}
		
		/**
		 * switch dashboard between customization and read mode 
		 * @private
		 */
		function switchCustomization(){
			if (dashboard.data('customization')) {
				closeCustomization();
				dashboard.data('customization', false);				
			} else {
				openCustomization();
				dashboard.data('customization', true);			
			}
		}

	};
});