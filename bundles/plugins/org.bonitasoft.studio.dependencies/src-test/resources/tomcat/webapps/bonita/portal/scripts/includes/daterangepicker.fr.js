var localeRange = [{
				text : "Aujourd'hui",
				dateStart : "today",
				dateEnd : "today"
			}, {
				text : "Semaine précédente",
				dateStart : "today-7days",
				dateEnd : "today"
			}, {
				text : "Mois encours",
				dateStart : function() {
					return Date.parse("today").moveToFirstDayOfMonth()
				},
				dateEnd : "today"
			}, {
				text : "Année encours",
				dateStart : function() {
					var w = Date.parse("today");
					w.setMonth(0);
					w.setDate(1);
					return w
				},
				dateEnd : "today"
			}, {
				text : "Mois précédent",
				dateStart : function() {
					return Date.parse("1 month ago").moveToFirstDayOfMonth()
				},
				dateEnd : function() {
					return Date.parse("1 month ago").moveToLastDayOfMonth()
				}
			} ];

var localePresets = {
	specificDate : "Date spécifique",
	dateRange : "Intervalle de date"
};