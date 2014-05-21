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
	$.fn.tabs = function() {

		return this
				.each(function() {

					var tabs_container = $(this);
					var tabs = $('>*', tabs_container).addClass('tab'), tabs_titles = $(
							'>.header', tabs);

					function showTab() {
						var tab_title = $(this);
						tabs.filter(':visible').fadeOut(
								100,
								function() {
									tabs_titles.removeClass('tabtitle_active');
									tab_title.addClass('tabtitle_active').data(
											'tab_content').fadeIn(100);
								});

					}

					// Link titles to their content
					tabs_titles.each(function() {
						var title = $(this);
						title.data('tab_content', title.closest('.tab',
								tabs_container));
					})

					// Move titles in the section header
					tabs_container.append($('<div class="header">').append(
							tabs_titles));
					tabs_titles.removeClass('header').addClass('tabtitle')
							.click(showTab);

					// Move contents in the section body
					tabs_container.append($('<div class="body">').append(tabs));

					/*if (tabs.closest('.popupcontainer').length) {
						
						// Fix the tabs container height
						var min = 0;
						tabs.each(function() {
							min = Math.max(min, $(this).outerHeight());
						});
						$('.body:first', tabs_container).height(min);

						// Fix the tabs container width
						min = 0;
						tabs.each(function() {
							min = Math.max(min, $(this).outerWidth());
						});
						$('.body:first', tabs_container).width('100%');
						min = Math.max(min, $(this).outerWidth());
						
						$('.body:first', tabs_container).width(min);
					}*/

					// Hide all but first tab
					tabs.not(':first').hide();
					tabs_titles.filter(':first').addClass('tabtitle_active');

				})
	}

	$.uiManager.addMaker(function(c) {
		var tab = $('.tab:first', c);
		while (tab.length) {
			var tabs = $('<div class="tabs">');
			tab.before(tabs);
			tabs.append(tab);

			tab = tabs.next();
			while (tab.length && tab.is('.tab')) {
				tabs.append(tab);
				tab = tabs.next();
			}
			tabs.children().removeClass('tab');

			tab = $('.tab:first', c);
		}
		$('.tabs', c).tabs();
	});
});