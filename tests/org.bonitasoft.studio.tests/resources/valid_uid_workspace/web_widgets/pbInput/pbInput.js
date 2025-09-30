(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbInput', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbInputCtrl($scope, $log, widgetNameFactory) {

  'use strict';

  this.name = widgetNameFactory.getName('pbInput');
  this.inputId = widgetNameFactory.getId('pbInput');
  this.ngModelOptions = { allowInvalid: true, debounce: $scope.properties.debounce }

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbInput property named "value" need to be bound to a variable');
  }
}
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}" ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}" ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <input\n                id="{{ctrl.inputId}}"\n                type="{{properties.type}}"\n                class="form-control"\n                placeholder="{{ properties.placeholder | uiTranslate }}"\n                ng-model="properties.value"\n                ng-model-options="ctrl.ngModelOptions"\n                name="{{ctrl.name}}"\n                ng-required="properties.required"\n                ng-minlength="properties.minLength"\n                ng-maxlength="properties.maxLength"\n                min="{{properties.min}}"\n                max="{{properties.max}}"\n                step="{{properties.step}}"\n                ng-readonly="properties.readOnly">\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error " ng-messages-include="forms-generic-errors.html" role="alert"></div>\n        </div>\n    </div>\n</div>\n'
    };
  });
