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
 * Panel that easily toggle its visibility with the click on an icon
 * 
 * @return plugin
 */
$(function() {

	/**
	 * @param handlerSelector
	 * @param bodySelector
	 * @param close
	 * @return (jQuery)
	 */

	// @todo : Set params with a settings array
	$.fn.togglePanel = function(handlerSelector, bodySelector, closed) {

		function open(panel) {
			$(bodySelector, panel).filter(':first').slideDown(100, function() {
				panel.removeClass('toggleclose').addClass('toggleopen');
			});
		}

		function close(panel) {
			$(bodySelector, panel).filter(':first').slideUp(100, function() {
				panel.addClass('toggleclose').removeClass('toggleopen');
			});
		}

		function toggle(panel) {
			if ($(bodySelector, panel).filter(':first').is(':visible')) {
				close(panel);
			} else {
				open(panel);
			}
		}

		return this.addClass('togglepanel').each(function() {
			$(handlerSelector, $(this)).filter(':first').click(function() {
				toggle($(this).closest('.togglepanel'));
			});

			$(this).addClass('toggleopen');

			if (!$.isNull(closed) && closed) {
				close($(this));
			}
		});
	};

});