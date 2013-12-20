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

	$.fn.fillHeight = function(container, footer) {
		var $container = $($.isNull(container) ? window : container);

		return this.each(function() {
			var $self = $(this);

			function resize() {
				var absoluteTop = $self.top();

				$self.height('auto');
				var absoluteBottom = $container.bottom();

				var height = absoluteBottom - absoluteTop - $self.verticalPadding();

				if (!$.isNull(footer)) {
					if ($.isNumber(footer)) {
						height -= footer
					} else {
						height -= $(footer).height() + $(footer).verticalSpacing();
					}
				}

				$self.height(height);

			}

			resize();
			$container.unbind('resize.fillHeight').bind('resize.fillHeight', function() {
				resize();
			})

		})
	}


	$.fn.socialBar = function(action) {

		return this.each(function() {
			var $self = $(this);

			if (!$.isNull(action)) {
				return $self.slidePanel(action);
			}

			// Initializing
			var button = $('<a href="#" class="toggle_panel button">X</a>')
			$('.page>.header', $self).append(button)
			$self.slidePanel()
		});
	}


	$.fn.slidePanel = function(action, options) {
		var $all = this;

		if ($.isObject(action)) {
			options = action;
			action = null;
		}

		var INITIAL_ANIMATION_VALUE = 'slidepanel.initialAnimationValue'
		var MASTER_ELEMENT = 'slidepanel.masterElement'

		// Public actions
		var groupactions = {
			isOpen : function() {
				return $all.hasClass("open");
			}
		}
		// Call action if needed
		if (!$.isNull(action) && !$.isNull(groupactions[action])) {
			return groupactions[action].call();
		}

		return $all.each(function() {
			var $self = $(this);

			// If the call target the container
			if ($self.data(MASTER_ELEMENT)) {
				$self = $self.data(MASTER_ELEMENT);
			}

			// Read settings
			var settings = $.extend({
			    minAnimationValue : 0,
			    animatedValue : 'width',
			    hideDefault : true,
			    animationDuration : 150
			}, options)

			// Private methods
			function getCurrentAnimatedValue() {
				switch (settings.animatedValue) {
					case 'width':
						return $self.width();
					case 'height':
						return $self.height();
					default:
						return $self.css(settings.animatedValue);
				}
			}

			// Public actions
			var actions = {
			    close : function() {
				    var anim = {};
				    anim[settings.animatedValue] = settings.minAnimationValue;
				    $self.removeClass('open').animate(anim, settings.animationDuration, function() {
					    $self.addClass('close')
					    if (settings.minAnimationValue == 0) {
						    $self.hide();
					    }
				    });
			    },

			    toggle : function() {
				    if (groupactions.isOpen()) {
					    actions.close();
				    } else {
					    actions.open();
				    }
			    },

			    open : function() {
				    var anim = {};
				    anim[settings.animatedValue] = $self.data(INITIAL_ANIMATION_VALUE);
				    if (settings.minAnimationValue == 0) {
					    $self.show();
				    }
				    $self.removeClass('close').animate(anim, settings.animationDuration, function() {
					    $self.addClass('open')

				    });
			    }
			}

			// Call action if needed
			if (!$.isNull(action) && !$.isNull(actions[action])) {
				return actions[action].call();
			}

			// Initializing
			var initialValue = getCurrentAnimatedValue();
			$self.data(INITIAL_ANIMATION_VALUE, initialValue)

			// Init container to fix the content even on width changes.

			if ($self.css('overflow') == 'visible') {
				$self.css('overflow', 'hidden')
			}

			var $container = $('<div>').append($self.children()).css(settings.animatedValue, initialValue);
			$container.data(MASTER_ELEMENT, $self)
			$self.append($container)
			$self.addClass('clrfx');

			// $container.attr('id', $self.attr('id'))
			// $self.removeAttr('id')

			// Close button event
			$('.toggle_panel', $self).click(function() {
				actions.toggle();
				return false
			});

			// Close by default
			if (settings.hideDefault) {
				$self.css(settings.animatedValue, settings.minAnimationValue);
				if (settings.minAnimationValue == 0) {
					$self.hide();
				}
			}
		});
	}

/*	$.uiManager.addMaker(function(c) {

		$('.filters_panel', c).slidePanel({
		    minAnimationValue : '20',
		    hideDefault : false
		})

		$('.details_panel', c).slidePanel()

		$('.tables_panel .tr', c).live('click', function() {
			$(this).closest('.page').find('.tr.current').removeClass('current');
			$(this).addClass('current');
		})

		$('.filters_panel a', c).live('click', function() {
			$(this).closest('.filters_panel').find('a.current').removeClass('current');
			$(this).addClass('current');
		})


	});*/

});