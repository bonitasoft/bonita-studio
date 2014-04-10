/*
 * Copyright (c) 2011, Séverin MOUSSEL
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of jQuery+ nor the names of its contributors may be
 *       used to endorse or promote products derived from this software without
 *       specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * CSS helpers
 */

$(function() {

	/**
	 * Calcul ou attribution de la couleur de fond * CALCUL - si {c} n'est pas
	 * définit
	 * 
	 * @return string Cette fonction retourne la couleur de fond du premier
	 *         parent la définissant * ATTRIBUTION Mapping de
	 *         this.css('background-color', c);
	 * @param c
	 *            Couleur de fond à attribuer
	 * @return $ Cette fonction retourne {this}
	 */
	$.fn.backgroundColor = function(c) {
		if ($.isNull(c)) {
			var bg = this.css('background-color');
			var p = this.parent();
			while (bg == 'transparent' && p.length && !p.is('body')) {
				bg = p.css('background-color');
				p = p.parent();
			}
			return bg;
		} else {
			return this.css('background-color', c);
		}
	}


	// Affiche temporairement un élément le temps d'effectuer la fonction
	// définie.
	$.fn.displayMakeHide = function(c) {
		// Mémorise l'état initial
		var visibility = this.css('visibility');
		var display = this.css('display');

		// Affiche l'élément
		this.css({
		    visibility : 'hidden',
		    display : 'block'
		});

		// Appel la focntion
		var res = c.call(this);

		// Retour à létat initial
		this.css({
		    visibility : visibility,
		    display : display
		});

		return $.isNull(res) ? this : res;
	}

	// Calcul la largeur d'un élément même si il est en display none.
	$.fn.widthForced = function() {
		return this.filter(':first').displayMakeHide(function() {
			return this.width();
		});
	}

	// Calcul la largeur d'un élément même si il est en display none.
	$.fn.outerWidthForced = function(m) {
		return this.filter(':first').displayMakeHide(function() {
			return this.outerWidth(m);
		});
	}

	// Calcul la hauteur d'un élément même si il est en display none.
	$.fn.heightForced = function() {
		return this.filter(':first').displayMakeHide(function() {
			return this.height();
		});
	}

	// Calcul la hauteur d'un élément même si il est en display none.
	$.fn.outerHeightForced = function(m) {
		return this.filter(':first').displayMakeHide(function() {
			return this.outerHeight(m);
		});
	}

	$.fn.dimensions = $.fn.dim = function(dim, overlay) {
		if (!$.isNull(dim) && !$.isJQ(dim)) {
			return this.each(function() {
				var e = $(this);
				e.css({
				    display : 'block',
				    height : dim.height,
				    width : dim.width
				});

				if (overlay) {
					e.css({
					    position : 'absolute',
					    top : dim.top,
					    left : dim.left
					});
				}
			});
		}
		var e = this
		var result = {
		    left : parseInt(e.get(0) == window ? 0 : e.offset()['left']),
		    top : parseInt(e.get(0) == window ? 0 : e.offset()['top']),
		    width : parseInt(e.get(0) == window ? e.width() : e.outerWidth()),
		    height : parseInt(e.get(0) == window ? e.height() : e.outerHeight())
		}

		if ($.isJQ(dim) && dim.length) {
			result.left -= dim.get(0) == window ? 0 : dim.offset()['left'];
			result.top -= dim.get(0) == window ? 0 : dim.offset()['top']
		}

		result.right = result.left + result.width;
		result.bottom = result.top + result.height;

		return result;

	}

	/**
	 * min-width, min-height, max-width, max-height.
	 */
	$.each([ 'min', 'max' ], function(i, name) {
		$.each([ 'Width', 'Height' ], function(i, name2) {
			var n2 = name2.toLowerCase();
			$.fn[name + name2] = function(value) {
				if (!$.isNull(value)) {
					return this.css(name + '-' + n2, value);
				}
				var value = this.css(name + '-' + n2);
				return ($.isNull(value) || value === 'none' || value === '' || parseInt(value) === -1) ? Number.MAX_VALUE : parseInt(value)
			}
		});
	});

	/**
	 * Top, Left, Bottom, Right
	 */
	$.each([ 'Top', 'Left', 'Bottom', 'Right' ], function(i, name) {
		var n = name.toLowerCase();
		$.fn[n] = function(value) {
			if ($.isNull(value)) {
				return this.dim()[n];
			}
			return this.css(n, value)
		}

		$.fn[n + 'Spacing'] = function(margin) {
			if ($.isNull(margin)) {
				margin = true;
			}
			return this['border' + name]() + this['padding' + name]() + (margin ? this['margin' + name]() : 0);
		}
	});

	/**
	 * verticalBorder, verticalMargin, verticalPadding, verticalSpacing
	 * horizontalBorder, horizontalMargin, horizontalPadding, horizontalSpacing
	 * 
	 * spacing = margin + border + padding
	 */
	$.each([ 'vertical', 'horizontal' ], function(i, name) {
		$.each([ 'Border', 'Margin', 'Padding' ], function(i, name2) {
			var n2 = name2.toLowerCase();
			$.fn[name + name2] = function(value) {
				var items = (name == 'horizontal') ? [ 'left', 'right' ] : [ 'top', 'bottom' ];
				if (!$.isNull(value)) {
					if (name == 'horizontal') {
						this[n2 + 'Left'](value);
						this[n2 + 'Right'](value);
					} else {
						this[n2 + 'Top'](value);
						this[n2 + 'Bottom'](value);
					}
					return this;
				}
				if (name == 'horizontal') {
					return this[n2 + 'Left']() + this[n2 + 'Right']();
				} else {
					return this[n2 + 'Top']() + this[n2 + 'Bottom']();
				}
			}
		})

		$.fn[name + 'Spacing'] = function(margin) {
			if ($.isNull(margin)) {
				margin = true;
			}
			return this[name + 'Border']() + this[name + 'Padding']() + (margin ? this[name + 'Margin']() : 0);
		}

	});

	/**
	 * border, margin and padding.
	 */
	$.each([ 'border', 'margin', 'padding' ], function(i, name) {
		$.fn[name] = function(value) {
			var self = this
			var suffix = (name == 'border') ? '-width' : ''
			if (!$.isNull(value)) {
				$.each([ 'top', 'left', 'bottom', 'right' ], function(i, name2) {
					if (!$.isNull(value[name2])) {
						self.css(name + '-' + name2 + suffix, value[name2])
					}
				})
				return self
			}
			return {
			    top : parseInt(self.css(name + '-top' + suffix)),
			    left : parseInt(self.css(name + '-left' + suffix)),
			    bottom : parseInt(self.css(name + '-bottom' + suffix)),
			    right : parseInt(self.css(name + '-right' + suffix))
			}
		}

		$.each([ 'Top', 'Left', 'Bottom', 'Right' ], function(i, name2) {
			var n2 = name2.toLowerCase();
			$.fn[name + name2] = function(value) {
				var suffix = (name == 'border') ? '-width' : '';
				if (!$.isNull(value)) {
					return this.css(name + '-' + n2 + suffix, value);
				}
				var value = this.css(name + '-' + n2 + suffix);
				return ($.isNull(value) || value === 'none' || value === '' || parseInt(value) === -1) ? 0 : parseInt(value)
			}
		});

	});


});
