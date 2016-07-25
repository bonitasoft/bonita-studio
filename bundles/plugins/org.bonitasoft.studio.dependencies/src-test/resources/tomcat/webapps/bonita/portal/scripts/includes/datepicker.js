/**
 * Copyright (C) 2012 BonitaSoft S.A. BonitaSoft, 32 rue Gustave Eiffel - 38000
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

	$.fn.datePicker = function(options) {

		/*
		 * Enumeration of the days during a week
		 */
		var DAY = {
			MONDAY : 1,
			TUESDAY : 2,
			WEDNESDAY : 4,
			THURSDAY : 8,
			FRIDAY : 16,
			SATURDAY : 32,
			SUNDAY : 64
		}

		/*
		 * Settings used for the DatePicker
		 */
		var defaultSettings = {
			format : 'mm/dd/yyyy', // Display only format
			// startDate : undefined, // db format : yyyy-mm-dd
			// endDate : undefined, // db format : yyyy-mm-dd
			startDay : DAY.MONDAY//,
		// offDays : DAY.SATURDAY | DAY.SUNDAY | DAY.WEDNESDAY
		}


		/*
		 * Function to get the name of the day of the Date (ex: Monday, Tuesday, .. )
		 */
		function dayOfTheDate(date) {

			switch (date.getDay()) {
				case 0:
					return DAY.SUNDAY;
				case 1:
					return DAY.MONDAY;
				case 2:
					return DAY.TUESDAY;
				case 3:
					return DAY.WEDNESDAY;
				case 4:
					return DAY.THURSDAY;
				case 5:
					return DAY.FRIDAY;
				case 6:
					return DAY.SATURDAY;
				default:
					return undefined;
			}
		}

		return this.each(function() {

			var e = $(this);

			var settings = $.extend(defaultSettings, e.getOptions(), options);
			// Translate String to Date
			if ($.isSet(settings.startDate)) {
				var split = settings.startDate.split(' ')[0].split('/');
				if (settings.format == 'mm/dd/yyyy') {
					settings.startDate = new Date(split[2], split[0] - 1, split[1]);
				} else {
					settings.startDate = new Date(split[2], split[1] - 1, split[0]);
				}
			}

			// Translate String to Date
			if ($.isSet(settings.endDate)) {
				var split = settings.endDate.split(' ')[0].split('/');
				settings.endDate = new Date(split[2], split[0] - 1, split[1]);
			}

			settings.format = settings.format.replace('dd', 'd');
			settings.format = settings.format.replace('mm', 'm');
			settings.format = settings.format.replace('yyyy', 'Y');
			settings.showWeek = false;
			e.DatePicker({
				format : settings.format,
				date : e.val(),
				current : new Date(),
				starts : settings.startDay,
				position : 'r',
				locale : {
					days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
					daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
					daysMin: [settings.daysMin[0], settings.daysMin[1], settings.daysMin[2], settings.daysMin[3], settings.daysMin[4], settings.daysMin[5], settings.daysMin[6]],
					months: [settings.months[0], settings.months[1], settings.months[2], settings.months[3], settings.months[4], settings.months[5], settings.months[6], settings.months[7], settings.months[8], settings.months[9], settings.months[10], settings.months[11]],
					monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
					weekMin: 'wk'
				},
				onBeforeShow : function() {
					if (e.val()) {
						e.DatePickerSetDate(e.val(), true);
					}
				},

				onChange : function(formated, dates) {
					e.val(formated);
					e.DatePickerHide();
				},

				onRender : function(date) {
					// TODO: Use startDate as a String
					var disable = false;

					// Disable date before the startDate
					if ($.isSet(settings.startDate)) {
						disable = disable || (date.valueOf() < settings.startDate.valueOf());
					}
					// Disable date after the endDate
					if ($.isSet(settings.endDate)) {
						disable = disable || (date.valueOf() > settings.endDate.valueOf());
					}

					// Disable the offDays
					var day = dayOfTheDate(date);

					if ($.isSet(day)) {
						disable = disable || (day & settings.offDays);
					}

					return {
						disabled : disable
					}
				}

			});
			e.attr('readonly', 'readonly');
		})
	}

	$(document).bind('keydown', function(e) {
		if (e.keyCode == 27) {
			$('.datepicker').hide();
		}
	});


	/**
	 * JQuery+ maker for .datepicker
	 */
	$.uiManager.addMaker(function(c) {
		$('.inputDate', c).datePicker();
	});

});