function PbDateTimePickerCtrl($scope, $log, widgetNameFactory, $element, $locale, $bsDatepicker, moment) {

  'use strict';
  this.name = widgetNameFactory.getName('pbDateTimepicker');
  this.inputId = widgetNameFactory.getId('pbDateTimepicker');
  this.firstDayOfWeek = ($locale && $locale.DATETIME_FORMATS && $locale.DATETIME_FORMATS.FIRSTDAYOFWEEK) || 0;

  $bsDatepicker.defaults.keyboard = false;

  var minuteStep = 5;

  $scope.$watch('properties.value', function() {
    refreshInputs();
  },true);

  var refreshInputs = function(){
    var value = moment($scope.properties.value);
    if ($scope.properties.value && value.isValid()) {
      $scope.properties.dateValue = formatToIso(moment({
        year: value.year(),
        month: value.month(),
        date: value.date()
      }));
      $scope.properties.timeValue = formatToIso(moment({
        hours: value.hours(),
        minutes: value.minutes(),
        seconds: value.seconds()
      }));
    }
  }

  $scope.updateTimeValue = function() {
    if ($scope.properties.timeValue && moment($scope.properties.timeValue).isValid()) {
      var date = moment($scope.properties.dateValue)
      var time = moment($scope.properties.timeValue);
      if (!date.isValid()) {
        date = moment();
      }
      $scope.properties.value = formatToIso(date
        .hours(time.hours())
        .minutes(time.minutes())
        .seconds(time.seconds())
      );
    } else {
      $scope.properties.value = undefined;
    }
  };

  $scope.updateDateValue = function() {
    if ($scope.properties.dateValue && moment($scope.properties.dateValue).isValid()) {
      var date = moment($scope.properties.dateValue);
      var dateTime = moment($scope.properties.timeValue);
      if ($scope.properties.timeValue && dateTime.isValid()) {
        $scope.properties.value = formatToIso(dateTime
          .year(date.year())
          .month(date.month())
          .date(date.date())
        );
      } else {
        var now = moment();
        $scope.properties.value = formatToIso(date
          .hours(now.hours())
          .minutes(roundToMinuteStep(now.minute()))
          .seconds(0)
        );
      }
    } else {
      $scope.properties.value = undefined;
    }
  };

  var roundToMinuteStep = function(minutes) {
    return Math.round(minutes / minuteStep) * minuteStep;
  };

  var formatToIso = function(moment) {
    var isoFormat = 'YYYY-MM-DDTHH:mm:ss';
    if ($scope.properties.withTimeZone) {
      return moment.utc().format(isoFormat) + 'Z';
    } else {
      return moment.format(isoFormat);
    }
  };


  this.setDateAndTimeToNow = function() {
    var now = moment();
    now.minute(roundToMinuteStep(now.minute())).second(0);
    $scope.properties.value = formatToIso(now);
    refreshInputs();
  };

  this.setDateToToday = function() {
    if ($scope.properties.timeValue && moment($scope.properties.timeValue).isValid()) {
      // Set the date at today but don't change time
      var timeValue = moment($scope.properties.timeValue);
      $scope.properties.value = formatToIso(moment({
        hour: timeValue.hours(),
        minute: timeValue.minutes(),
        seconds: timeValue.seconds()
      }));
    } else {
      var now = moment();
      $scope.properties.value = formatToIso(now
        .minutes(roundToMinuteStep(now.minute()))
        .seconds(0)
      );
    }
    refreshInputs();
  };

  this.openDatePicker = function() {
    $element.find('input')[0].focus();
  };

  this.openTimePicker = function() {
    $element.find('input')[1].focus();
  };

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbDateTimepicker property named "value" need to be bound to a variable');
  }
}

