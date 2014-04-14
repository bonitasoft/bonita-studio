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

$(function() {
	var DATA_LOADER = 'loader:loader', DATA_TIMER = 'loader:timer', OLD_POSITION = 'loader.old.position';

	$.fn.loader = function(action, settings) {

		if ($.isObject(action)) {
			settings = action;
			action = 'start';
		}

		settings = $.extend(true, {
		    template : '<div class="loading"><div class="anim"></div><div class="overlay"></div></div>',
		    delay : 0,
		    hide : false,
		    overlay : true
		}, settings);


		return $(this).each(function() {
			var e = $(this);

			function show() {

				// Make the loader DOM
				var loader = $(settings.template);
				if (!settings.overlay) {
					$('.overlay', loader).hide();
				}
				e.data(DATA_LOADER, loader);

				// Add the loader to the document
				if (e.data(OLD_POSITION)) {
					e.append(loader);
				} else {
					$('body').append(loader);
				}

				if (settings.hide) {
					e.css('visibility', 'hidden');
				} else {
					// Sizing the overlay
					if (e.data(OLD_POSITION)) {
						loader.css({
						    top : 0,
						    left : 0,
						    height : '100%',
						    width : '100%',
						    position : 'absolute'
						});
					} else {
						loader.dim(e.dim(), true);
					}
				}
			}

			switch (action) {
				default:
				case 'start':
					return;
					if ((settings.delay > 0 && e.data(DATA_TIMER)) || e.data(DATA_LOADER)) {
						break;
					}

					var position = e.css('position');
					if (e.attr('style') && position != 'absolute' && position != 'relative') {
						e.data(OLD_POSITION, e.css('position'));
						e.css('position', 'relative');
					}


					if (settings.delay > 0) {
						e.data(DATA_TIMER, setTimeout(function() {
							// Close the delayer
							e.removeData(DATA_TIMER);
							show();
						}, settings.delay))
					} else {
						show();
					}
					break;
				case 'stop':
					return;
					if (settings.delay > 0) {
						var dtimer = e.data(DATA_TIMER)
						if (dtimer) {
							clearTimeout(dtimer)
							e.removeData(DATA_TIMER);
						}
					}

					if (e.data(DATA_LOADER)) {
						e.data(DATA_LOADER).remove();
						e.removeData(DATA_LOADER);
					}

					if (e.data(OLD_POSITION)) {
						e.css('position', e.data(OLD_POSITION))
					}

					e.css('visibility', '');

					break;
			}
		});
	}


});