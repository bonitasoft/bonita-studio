(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbCurrencyInput', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbCurrencyInputCtrl($scope, $log, widgetNameFactory) {

  'use strict';
  var mySelf = this;
  this.name = widgetNameFactory.getName('pbCurrencyInput');
  this.inputId = widgetNameFactory.getId('pbCurrencyInput');

  var orientation = {
    "right": "r",
    "left": "l"
  }

  var indentation = {
    "whitespace": " "
  }

  var decimal = {
    ",": ",",
    ".": "."
  }
  var groupSeparator = {
    "whitespace": " ",
    ",": ",",
    ".": "."
  }

  this.maskCurrencyConfig = getMaskCurrencyConfig();

  $scope.$watchGroup(['properties.orientation','properties.indentation','properties.decimal','properties.decimalSize','properties.group', 'properties.min', 'properties.max'], function() {
    mySelf.maskCurrencyConfig =  getMaskCurrencyConfig();
  });

  function getMaskCurrencyConfig() {
    return {
      orientation: orientation[$scope.properties.orientation] || 'r',
      indentation: $scope.properties.indentation === 'none' ? '' : indentation[$scope.properties.indentation] || $scope.properties.indentation,
      decimal: decimal[$scope.properties.decimal] || $scope.properties.decimal || ',',
      decimalSize: parseInt($scope.properties.decimalSize) || 0,
      group: $scope.properties.group === 'none' ? '' : groupSeparator[$scope.properties.group] || $scope.properties.group,
      groupSize: 3
    }
  }

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbCurrencyInput property named "value" need to be bound to a variable');
  }

}
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}" ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}" ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <input\n                id="{{ctrl.inputId}}"\n                autocomplete="off"\n                type="text"\n                class="form-control"\n                placeholder="{{ properties.placeholder | uiTranslate }}"\n                ng-model="properties.value"\n                ng-model-options="{ allowInvalid: true }"\n                name="{{ctrl.name}}"\n                ng-required="properties.required"\n                ng-readonly="properties.readOnly"\n                min="{{properties.min}}"\n                max="{{properties.max}}"\n                mask-currency="properties.maskCurrency" config="ctrl.maskCurrencyConfig"/>\n\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error " ng-messages-include="forms-generic-errors.html" role="alert"></div>\n        </div>\n\n    </div>\n</div>\n'
    };
  });
