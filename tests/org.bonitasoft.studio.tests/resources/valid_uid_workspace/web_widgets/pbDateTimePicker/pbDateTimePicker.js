(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbDateTimePicker', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbDateTimePickerCtrl($scope, $log, widgetNameFactory, $element, $locale, $bsDatepicker, moment) {

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

,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n        <div ng-if="environment && environment.editor && properties.value" class="col-xs-12">\n            <i class="fa fa-link"/></i>\n            {{properties.value}}\n        </div>\n        <div\n            class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}\n            {{properties.inlineInput ? \'form-horizontal\' : \'\'}}">\n\n            <p ng-class="{\'col-xs-12 col-sm-6\': properties.inlineInput}"\n               class="input-group"\n               ng-style="properties.inlineInput ? {\'float\': \'left\'} : {}">\n                <input class="form-control"\n                       id="{{ctrl.inputId}}"\n                       name="{{ctrl.name}}date"\n                       type="text"\n                       data-date-type="{{properties.withTimeZone ? \'iso\' : \'string\'}}"\n                       data-model-date-format="{{properties.withTimeZone ? null : \'yyyy-MM-ddTHH:mm:ss\'}}"\n                       placeholder="{{properties.placeholder | uiTranslate}}"\n                       autocomplete="off"\n                       ng-model="properties.dateValue"\n                       ng-change="updateDateValue()"\n                       ng-readonly="properties.readOnly"\n                       ng-required="properties.required"\n                       bs-datepicker\n                       data-container="body"\n                       data-autoclose="1"\n                       data-date-format="{{properties.dateFormat | uiTranslate}}"\n                       data-trigger="focus"\n                       data-start-week="{{ctrl.firstDayOfWeek}}">\n\n                <span class="input-group-btn">\n                <button ng-if="properties.showToday" type="button"\n                        class="btn btn-default today\n                               {{$form[ctrl.name+\'date\'].$dirty && (($form[ctrl.name+\'date\'].$error.date || $form[ctrl.name+\'date\'].$error.parse) ||\n                               (properties.required && $form[ctrl.name+\'date\'].$error.required)) ? \'btn-invalid\':\'\'}}"\n                        ng-click="ctrl.setDateToToday()"\n                        ng-disabled="properties.readOnly" ui-translate>\n                           {{properties.todayLabel || \'Today\' | uiTranslate}}\n                </button>\n                <button type="button"\n                        class="btn btn-default calendar\n                               {{$form[ctrl.name+\'date\'].$dirty && (($form[ctrl.name+\'date\'].$error.date || $form[ctrl.name+\'date\'].$error.parse) ||\n                               (properties.required && $form[ctrl.name+\'date\'].$error.required)) ? \'btn-invalid\':\'\'}}"\n                        ng-click="ctrl.openDatePicker()"\n                        ng-disabled="properties.readOnly"\n                        aria-label="{{\'Open calendar\' | uiTranslate}}">\n                    <i class="glyphicon glyphicon-calendar"></i>\n                </button>\n                </span>\n            </p>\n            <p ng-class="{\'col-xs-12 col-sm-6\': properties.inlineInput}"\n               class="input-group"\n               ng-style="properties.inlineInput ? {\'float\': \'left\'} : {}">\n                <input class="form-control"\n                       aria-labelledby="{{ctrl.inputId}}"\n                       name="{{ctrl.name}}time"\n                       type="text"\n                       data-time-type="{{properties.withTimeZone ? \'iso\' : \'string\'}}"\n                       data-model-time-format="{{properties.withTimeZone ? null : \'yyyy-MM-ddTHH:mm:ss\'}}"\n                       placeholder="{{properties.timePlaceholder | uiTranslate}}"\n                       autocomplete="off"\n                       ng-model="properties.timeValue"\n                       ng-change="updateTimeValue()"\n                       ng-readonly="properties.readOnly"\n                       ng-required="properties.required"\n                       bs-timepicker\n                       data-container="body"\n                       data-autoclose="0"\n                       data-time-format="{{properties.timeFormat | uiTranslate}}"\n                       data-length="1"\n                       data-minute-step="5"\n                       data-second-step="5"\n                       data-round-display="true"\n                       data-arrow-behavior="picker"\n                       data-trigger="focus">\n\n                <span class="input-group-btn">\n                      <button ng-if="properties.showNow" type="button"\n                              class="btn btn-default now\n                               {{$form[ctrl.name+\'time\'].$dirty && (($form[ctrl.name+\'time\'].$error.date || $form[ctrl.name+\'time\'].$error.parse) ||\n                               (properties.required && $form[ctrl.name+\'time\'].$error.required)) ? \'btn-invalid\':\'\'}}"\n                              ng-disabled="properties.readOnly"\n                              ng-click="ctrl.setDateAndTimeToNow()" ui-translate>\n                          {{properties.nowLabel || \'Now\' | uiTranslate}}\n                      </button>\n                      <button type="button"\n                              class="btn btn-default timepicker\n                                {{$form[ctrl.name+\'time\'].$dirty && (($form[ctrl.name+\'time\'].$error.date || $form[ctrl.name+\'time\'].$error.parse) ||\n                                (properties.required && $form[ctrl.name+\'time\'].$error.required)) ? \'btn-invalid\':\'\'}}"\n                              ng-click="ctrl.openTimePicker()"\n                              ng-disabled="properties.readOnly"\n                              aria-label="{{\'Open clock\' | uiTranslate}}">\n                        <i class="glyphicon glyphicon-time"></i>\n                      </button>\n                  </span>\n            </p>\n\n            <div ng-messages="($form[ctrl.name+\'time\'].$dirty && $form[ctrl.name+\'time\'].$error) ||\n                                      ($form[ctrl.name+\'date\'].$dirty && $form[ctrl.name +\'date\'].$error)"\n                 ng-messages-include="forms-generic-errors.html" role="alert">\n                <div ng-message="date" ng-if="!environment || !environment.editor" class="text-danger">\n                    {{ \'This is not a valid date or time\' | uiTranslate }}\n                </div>\n                <div ng-message="parse" ng-if="!environment || !environment.editor" class="text-danger">\n                    {{ \'This is not a valid date or time\' | uiTranslate }}\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n'
    };
  });
