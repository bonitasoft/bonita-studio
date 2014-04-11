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

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// / Add some forms cross platform compatibility
	// //////////////////////////////////////////////////////////////////////////////////////////////
	
	$.fn.formManager = function() {
		var forms = this;

		// Disable form submission
		forms.submit(function(e) {
			e.stopPropagation();
			return false;
		})

		// Fieldset that can be enabled/disabled by a radio or a checkbox
		$('fieldset legend input[type=radio], fieldset legend input[type=checkbox]').check(function() {
			$(this).closest('fieldset', forms).children().not('legend').enable();
		}, function() {
			$(this).closest('fieldset', forms).children().not('legend').disable();
		});

		// Lock textarea by CSS
		$('textarea', forms).each(function() {
			var e = $(this);
			if (e.css('max-width')) {
				e.css('max-width', e.css('max-width'));
			}
			if (e.css('max-height')) {
				e.css('max-height', e.css('max-height'));
			}
			if (e.css('min-width')) {
				e.css('min-width', e.css('min-width'));
			}
			if (e.css('min-height')) {
				e.css('min-height', e.css('min-height'));
			}
		});
		
		// set autocomplete width to field width
		$('.dropdown.autocomplete', forms).each(function(){
			var dropdown = $(this);
			var field = dropdown.siblings('.delegateinput.autocomplete:first');
			dropdown.css('width',field.outerWidth());
		});

		$('input', forms).customInput();
		$(".formentry.select .input, #report-form .input", forms).each(function(i,e){
			try{
				resizeSelect(e);
			}catch(er){
				;
			}
		});
		$(".formentry.select .input select , #report-form .input select", forms).each(function(i,e){
			$(e).change(function(e){
				try{
					resizeSelect($(e).parent());
				}catch(er){
					;
				}
			});
			
		});
		
		
		
		return this;
	}

	$.uiManager.addMaker(function(context) {
		$('form', context).formManager();
	});

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ALT input
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	$.fn.altInput = function(attrname) {
		function update(e) {
			var input = $(this);

			if (input.val().length == 0) {
				old_val_func_empty.call(input, input.data('altInput.alt'));
				input.addClass('empty');

			} else {
				input.removeClass('empty');
			}
		}

		return this.each(function() {
			var input = $(this);
			input.data('altInput.alt', input.attr(attrname))
			if (!input.attr(attrname)) {
				input.data('altInput.alt', input.closest('[' + attrname + ']').attr(attrname));
			}

			update.call(this);

			input.focus(function() {
				if (input.is('.empty')) {
					old_val_func_empty.call(input, '');
					input.removeClass('empty');
				}
			}).bind('blur change', update)
		});
	}

	var old_val_func_empty = $.fn.val;
	$.fn.val = function(value) {
		if ($.isNull(value)) {
			if ($(this).is('.empty')) {
				return '';
			}

			return old_val_func_empty.call(this);
		}

		if (old_val_func_empty.call(this) != value) {
			old_val_func_empty.call(this, value);
			this.removeClass('empty')
			this.trigger("change")
		} else {
			old_val_func_empty.call(this, value);
		}
		return this;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// DELEGATE INPUT - The input value will be set to a hidden instead of
	// itself
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	var DELEGATE_HIDDEN = 'delegate.hidden';

	$.fn.delegateInput = function() {
		return this.each(function() {
			var e = $(this);

			if (!e.data(DELEGATE_HIDDEN)) {
				var delegate = $('<input type="hidden" name="__d_' + e.attr('name') + '" value="' + e.attr('value') + '" />');
				e.before(delegate).data(DELEGATE_HIDDEN, delegate);
			}
		})
	}

	$.uiManager.addMaker(function(context) {
		$('.delegateinput', context).delegateInput();
	});

	var old_val_func_delegateInput = $.fn.val;
	$.fn.val = function(value) {
		var e = this;

		if (!$.isNull(this.data(DELEGATE_HIDDEN))) {
			e = this.data(DELEGATE_HIDDEN);
		} else if (this.is('.delegateinput')) {
			e = $(':hidden[name=__d_' + this.attr('name'), this.closest('form'));
		}

		if ($.isNull(value)) {
			return old_val_func_delegateInput.call(e);
		} else {
			old_val_func_delegateInput.call(e, value);
		}

		return this;
	}

	$.fn.label = function(value) {
		if (!this.is('.delegateinput') && $.isNull(this.data(DELEGATE_HIDDEN))) {
			return this.val(value);
		}

		return old_val_func_delegateInput.call(this, value);
	}

	$.uiManager.addMaker(function(context) {
		$('form fieldset legend input', context).filter('[type=radio], [type=checkbox]').check(function() {
			$(this).closest('fieldset', context).children().not('legend').enable();
		}, function() {
			$(this).closest('fieldset', context).children().not('legend').disable();
		});

		// TODO correct and reactivate
		// $(':text[label], textarea[label]',context).altInput('label');
		// $(':text:not([label]), textarea:not([label])',
		// context).altInput('title');

	});

	/**
	 * JQuery+ maker for the focus on a popup page
	 */
	$.uiManager.addMaker(function(context) {
		if (context.is("#popup")) {
			$('form :input:first', context).focus();
		}
	});


});