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

	/**
	 * Plugin to create a dropdown
	 */
	$.fn.dropdown = function(action, settings) {

		// Switch action into settings for initialization
		// This simulate the constructor dropdown(settings)
		if ($.isObject(action)) {
			settings = action;
			action = undefined;
		}

		// Statics
		var SETTINGS = 'dropdown.settings'
		var CLOSETIMER = 'dropdown.closetimer'

		// ************************************************************************************************************************/
		// * LOOP TO APPLY PLUGIN TO EACH ELEMENT
		// ************************************************************************************************************************/

		return this.each(function() {

			// ************************************************************************************************************************/
			// * PLUGIN PRIVATE ATTRIBUTES
			// ************************************************************************************************************************/

			var $self = $(this)
			var self = this
			var content = $self.children().not(":first")
			var $label = $self.children(":first")

			if (!content.length) {
				return;
			}


			// ************************************************************************************************************************/
			// * SETTINGS
			// ************************************************************************************************************************/

			settings = $self.data(SETTINGS) ? $self.data(SETTINGS) : $.extend(true, {

			    // SlideUp and SlideDown animation duration
			    duration : 75,

			    // Time to wait after mouseLeave before auto close
			    close_delay : 1500,

			    // Indicate on which border of the parent to align (left or
			    // right only)
			    align : 'left',

			    // Callback before showing an Item
			    onBeforeShow : function() {
			    },

			    // Indicate that the label must change depending on the last
			    // selected item
			    selectMode : false,

			    // Delay between each visibility check
			    check_delay : 500

			}, settings, $self.getOptions());
			$self.data(SETTINGS, settings);


			// ************************************************************************************************************************/
			// * PRIVATE FUNCTIONS
			// ************************************************************************************************************************/

			/**
			 * Check if the dropdown is still visible on defined interval (see
			 * plugin settings)
			 */
			function checkTimer() {
				if (!$self.is(':visible')) {
					hide();
				} else if (content.is(':visible')) {
					setTimeout(checkTimer, settings.check_delay)
				}
			}

			/**
			 * Switch between hide and display
			 */
			function toggle() {
				if (content.is(':visible')) {
					hide();
				} else {
					show();
				}
			}

			/**
			 * Display the dropdown
			 */
			function show() {
				if (!content.is(':visible')) {
					var ref = $self.closest(':css(position!=static)')

					if (!ref.length) {
						ref = $(window);
					}

					// Align dropdown on its label
					var dim = $label.dim(ref)
					if (settings.align == 'right') {
						content.css('right', ref.width() - dim.right);
					} else {
						content.css('left', dim.left);
					}

					// Trigger event
					settings.onBeforeShow.call(self);

					// Hide all other dropdown
					$('.dropdownitem').dropdown('close');

					// Display this dropdown
					content.css({
					    'z-index' : 10,
					    'top' : dim.bottom,
					    'min-width' : dim.width
					}).slideDown(settings.duration);

					// Run the check timer that will hide the menu if it is
					// hidden
					checkTimer();
				}
			}


			/**
			 * Hide the dropdown
			 */
			function hide() {
				stopLeaveTimer();

				content.css('z-index', 1);
				content.is(':visible') ? content.slideUp(settings.duration) : content.hide()
			}

			/**
			 * Start the timer that detect that the mouse has leave the dropdown<br />
			 * That will close the dropdown after a defined time (see plugin
			 * settings)
			 */
			function startLeaveTimer() {
				if ($self.data(CLOSETIMER)) {
					clearTimeout($self.data(CLOSETIMER));
				}
				$self.data(CLOSETIMER, setTimeout(function() {
					$self.removeData(CLOSETIMER);
					hide();
				}, 2000));
			}

			/**
			 * Stop the timer that detect that the mouse has leave the dropdown
			 */
			function stopLeaveTimer() {
				if ($self.data(CLOSETIMER)) {
					clearTimeout($self.data(CLOSETIMER));
					$self.removeData(CLOSETIMER);
				}
			}

			/**
			 * Initialize the dropdown
			 */
			function init() {
				$self.addClass('dropdownitem');

				content.css('position', 'absolute').hide()

				/** ****** SET EVENTS ********* */

				// Timer on mouse leave
				$self.mouseenter(stopLeaveTimer).mouseleave(startLeaveTimer)

				// Open close on label click
				$label.click(function(e) {
					e.stopPropagation();
					toggle(this);
					return false;
				})

				// Close menu while clicking on an item
				$('a, button, :button, :submit', content).click(function() {
					var $button = $(this)

					// Timer to allow other animations to display
					setTimeout(function() {
						$self.removeData('closeTimer');

						if (settings.selectMode) {
							$label.text($button.text());
						}

						hide();
					}, 50);
				})

				// Keyboard shortcuts
				// .keypress(function(e){
				// switch (e.keyCode) {
				// case 38 : // up
				// var prev = $(':focus', $self).prev();
				// if (prev.length == 0) {
				// hide();
				// prev = $('>a:first', $self);
				// }
				// prev.focus();
				// break;
				// case 40 : // down
				// var next = $(':focus', $self).next();
				// if ($(this).parent() == $self || next.length == 0) {
				// next = $self.find('>:nth-child(2)>:first');
				// }
				// next.focus();
				// break;
				// case 27 : // esc
				// hide();
				// break;
				// default:
				// return true;
				// break;
				// }
				// return false;
				// })

			}

			/** ****** ACTIONS ********* */
			switch (action) {
				case 'open':
					show();
					break;
				case 'close':
					hide();
					break;
				case 'toggle':
					toggle();
					break;
				default:
					init();
			}
		})
	};

	/**
	 * Plugin entrypoint for a list of dropdowns
	 */
	$.fn.dropDownMenu = function(settings) {
		return this.each(function() {
			$(this).children("li").dropdown(settings);
		})
	};

	/**
	 * JQuery+ maker for .dropdownmenu
	 */
	$.uiManager.addMaker(function(c) {
		$('.dropdownmenu', c).dropDownMenu({
			align : (c.is('#login') ? 'right' : 'left')
		});
	});

	/**
	 * Close dropdowns while clicking anywhere else in the document
	 */
	$(document).click(function() {
		// Close all menus
		$('.dropdownitem').dropdown('close');
	})

	/**
	 * Automatic dropdown menu with table actions
	 */
//	$.uiManager.addMaker(function(c) {
//		$('.td_actions', c).each(function() {
//			var $self = $(this), self = this
//
//			if ($self.closest('.table_view_tree').length || $('.groupedactions', $self).length) {
//				return;
//			}
//
//			var output = $('<div class="groupedactions"><a></a><ul></ul></div>')
//			var container = $('ul', output);
//
//			/** ****** INIT ********* */
//			$self.children(':not(.nogroup)').each(function() {
//				container.append($('<li>').append($(this)));
//			});
//
//			if (container.children().length > 0) {
//				$self.append(output);
//				output.dropdown({
//					align : 'right'
//				});
//			}
//		});
//	});

});