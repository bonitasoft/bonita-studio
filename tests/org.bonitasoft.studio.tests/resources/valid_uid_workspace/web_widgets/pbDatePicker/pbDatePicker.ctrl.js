function PbDatePickerCtrl($scope, $log, widgetNameFactory, $element, $locale, $bsDatepicker) {

  'use strict';

  this.name = widgetNameFactory.getName('pbDatepicker');
  this.inputId = widgetNameFactory.getId('pbDatepicker');

  this.firstDayOfWeek = ($locale && $locale.DATETIME_FORMATS && $locale.DATETIME_FORMATS.FIRSTDAYOFWEEK) || 0;

  $bsDatepicker.defaults.keyboard = false;

  this.setDateToToday = function() {
    var today = new Date();
    if(today.getDay() !== today.getUTCDay()) {
      //we need to add this offset for the displayed date to be correct
      if(today.getTimezoneOffset() > 0) {
        today.setTime(today.getTime() - 1440 * 60 * 1000);
      } else if(today.getTimezoneOffset() < 0) {
        today.setTime(today.getTime() + 1440 * 60 * 1000);
      }
    }
    today.setUTCHours(0);
    today.setUTCMinutes(0);
    today.setUTCSeconds(0);
    today.setUTCMilliseconds(0);
    $scope.properties.value = today;
  };

  this.openDatePicker = function () {
    $element.find('input')[0].focus();
  };


  if (!$scope.properties.isBound('value')) {
    $log.error('the pbDatepicker property named "value" need to be bound to a variable');
  }


}
