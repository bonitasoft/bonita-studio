(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbAutocomplete', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbAutocompleteCtrl($scope, $parse, $log, widgetNameFactory) {

  'use strict';

  function createGetter(accessor) {
    return accessor && $parse(accessor);
  }
  
  this.ngModelOptions = { allowInvalid: true, debounce: $scope.properties.debounce }

  this.getLabel = createGetter($scope.properties.displayedKey) || function (item) {
    return typeof item === 'string' ? item : JSON.stringify(item);
  };

  this.getValue = createGetter($scope.properties.returnedKey) || function (item) {
    return item;
  };

  this.onSelectedCallback = function ($item, $model, $label) {
    this.selectedItem = $item;
  };

  this.formatLabel = function ($model) {
    if (this.selectedItem) {
      return this.getLabel(this.selectedItem);
    } else {
      if (typeof $model === 'object') {
        return this.getLabel($model);
      } else {
        return $model;
      }
    }
  };

  this.name = widgetNameFactory.getName('pbAutocomplete');
  this.inputId = widgetNameFactory.getId('pbAutocomplete');

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbAutocomplete property named "value" need to be bound to a variable');
  }
}
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}" >\n            <input\n                id="{{ctrl.inputId}}"\n                type="text"\n                class="form-control"\n                placeholder="{{ properties.placeholder | uiTranslate }}"\n                typeahead-append-to-body="true"\n                typeahead="ctrl.getValue(item) as ctrl.getLabel(item) for item in properties.availableValues | filter:$viewValue"\n                typeahead-template-url="customTypeaheadForInputAutocomplete.html"\n                typeahead-wait-ms="300"\n                typeahead-on-select="ctrl.onSelectedCallback($item, $model, $label)"\n                typeahead-input-formatter="ctrl.formatLabel($model)"\n                ng-model="properties.value"\n                ng-model-options="ctrl.ngModelOptions"\n                name="{{ctrl.name}}"\n                ng-required="properties.required"\n                ng-readonly="properties.readOnly">\n\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error " ng-messages-include="forms-generic-errors.html" role="alert"></div>\n\n            <!-- It doesn\'t work if we put it inside form.html -->\n            <script type="text/ng-template" id="customTypeaheadForInputAutocomplete.html">\n                <a  bind-html-unsafe="match.label | typeaheadHighlight:query"></a>\n            </script>\n        </div>\n    </div>\n</div>\n'
    };
  });
