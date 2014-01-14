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
	// Register maker
	$.uiManager.addMaker(function(c) {
		return $('.switchpanel', c).each(function() {
			var e = $(this);

			var title = e.children('.header').children('h1,h2,h3,h4,h5,h6').filter(':first');
			
			if (title.length) {
				title.append('<span>');
				e.addClass('switchpanel').togglePanel(title, '.body');
			}
		});

	}, 1);

});