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
	$.fn.waitForContent = function(callback, time) {
		if ($.isNull(time)) {
			time = 200;
		}
		if (!this.children().length) {
			setTimeout(function() {
				this.waitForContent(callback);
			}, time);
		} else {
			setTimeout(function() {
				callback.call();
			}, time);

		}
	}

});


function triggerEvent(element, eventName) {
	$(element).trigger(eventName);
}

function updateGWT(e) {
	$(e).updateUI(true);
}

function historyBack() {
	window.history.back();
}
