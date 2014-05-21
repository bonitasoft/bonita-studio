var localeRange = [{
				text : "Today",
				dateStart : "today",
				dateEnd : "today"
			}, {
				text : "Last 7 days",
				dateStart : "today-7days",
				dateEnd : "today"
			}, {
				text : "Month to date",
				dateStart : function() {
					return Date.parse("today").moveToFirstDayOfMonth()
				},
				dateEnd : "today"
			}, {
				text : "Year to date",
				dateStart : function() {
					var w = Date.parse("today");
					w.setMonth(0);
					w.setDate(1);
					return w
				},
				dateEnd : "today"
			}, {
				text : "The previous Month",
				dateStart : function() {
					return Date.parse("1 month ago").moveToFirstDayOfMonth()
				},
				dateEnd : function() {
					return Date.parse("1 month ago").moveToLastDayOfMonth()
				}
			} ];

var localePresets = {
	specificDate : "Specific Date",
	allDatesBefore : "All Dates Before",
	allDatesAfter : "All Dates After",
	dateRange : "Date Range"
};