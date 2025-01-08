(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbDatePicker', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbDatePickerCtrl($scope, $log, widgetNameFactory, $element, $locale, $bsDatepicker) {

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
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n\n        <div\n            class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <p class="input-group">\n                <input class="form-control"\n                       id="{{ctrl.inputId}}"\n                       name="{{ctrl.name}}"\n                       type="text"\n                       placeholder="{{properties.placeholder | uiTranslate}}"\n                       autocomplete="off"\n                       ng-model="properties.value"\n                       ng-readonly="properties.readOnly"\n                       ng-required="properties.required"\n                       bs-datepicker\n                       data-container="body"\n                       data-autoclose="1"\n                       data-timezone="UTC"\n                       date-format="{{properties.dateFormat | uiTranslate}}"\n                       data-trigger="focus"\n                       data-start-week="{{ctrl.firstDayOfWeek}}">\n\n                <span class="input-group-btn">\n                    <button ng-if="properties.showToday"\n                            type="button"\n                            class="btn btn-default today\n                                {{$form[ctrl.name].$dirty && ($form[ctrl.name].$error.date || $form[ctrl.name].$error.parse ||\n                                (properties.required && $form[ctrl.name].$error.required)) ? \'btn-invalid\':\'\'}}"\n                            ng-click="ctrl.setDateToToday()"\n                            ng-disabled="properties.readOnly" ui-translate>\n                        {{properties.todayLabel || \'Today\' | uiTranslate}}\n                    </button>\n                    <button type="button"\n                            class="btn btn-default calendar\n                               {{$form[ctrl.name].$dirty && ($form[ctrl.name].$error.date || $form[ctrl.name].$error.parse ||\n                               (properties.required && $form[ctrl.name].$error.required)) ? \'btn-invalid\':\'\'}}"\n                            ng-click="ctrl.openDatePicker()"\n                            ng-disabled="properties.readOnly"\n                            aria-label="{{\'Open calendar\' | uiTranslate}}">\n                        <i class="glyphicon glyphicon-calendar"></i>\n                    </button>\n                </span>\n            </p>\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error "\n                 ng-messages-include="forms-generic-errors.html" role="alert">\n                <div ng-message="parse" ng-if="!environment || !environment.editor" class="text-danger">\n                    {{ \'This is not a valid date\' | uiTranslate }}\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n'
    };
  });
