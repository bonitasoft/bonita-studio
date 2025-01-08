(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbChecklist', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbChecklistCtrl($scope, $parse, widgetNameFactory, $log) {

  'use strict';
  var ctrl = this;

  /**
   * Watch the data source and set wrapChoices and $scope.properties.selectedValues
   */
  function comparator(item, initialValue) {
    return angular.equals(item, initialValue);
  }

  function createGetter(accessor) {
    return accessor && $parse(accessor);
  }

  this.getLabel = createGetter($scope.properties.displayedKey) || function (item) {
      return typeof item === 'string' ? item : JSON.stringify(item);
    };

  this.getValue = createGetter($scope.properties.returnedKey) || function (item) {
      return item;
    };


  /**
   * update the scope.properties.selectedValues with the selected items
   */
  this.updateValues = function () {
    $scope.properties.selectedValues = ctrl.selectedItems
      .map(function (checked, index) {
        if (checked !== true) {
          return false;
        }
        var item = $scope.properties.availableValues[index];
        return ctrl.getValue(item);
      }).filter(function (item) {
        return item !== false;
      });
  };

  function updateSelectedValues() {
    ctrl.selectedItems = ($scope.properties.availableValues || []).map(function (item) {
      if (Array.isArray($scope.properties.selectedValues)) {
        return $scope.properties.selectedValues.some(comparator.bind(null, ctrl.getValue(item)));
      }
      return false;
    });
  }

  $scope.$watchCollection('properties.availableValues', updateSelectedValues);
  $scope.$watchCollection('properties.selectedValues', updateSelectedValues);

  this.name = widgetNameFactory.getName('pbChecklist');
  this.inputId = widgetNameFactory.getId('pbChecklist');

  if (!$scope.properties.isBound('selectedValues')) {
    $log.error('the pbCheckList property named "selectedValues" need to be bound to a variable');
  }
}
,
      template: '<div class="row form-group"\n     ng-class="{ \'form-horizontal\':  !properties.labelHidden && properties.labelPosition === \'left\' }">\n    <fieldset>\n    <legend\n        ng-if="!properties.labelHidden && properties.allowHTML"\n        class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n        ng-bind-html="properties.label | uiTranslate">\n    </legend>\n    <legend\n        ng-if="!properties.labelHidden && !properties.allowHTML"\n        class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n        ng-bind="properties.label | uiTranslate">\n    </legend>\n\n    <div ng-if="properties.inline"\n         class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <label class="checkbox-inline" ng-repeat="choice in properties.availableValues track by $index"\n                   for="{{ctrl.inputId}}-{{$index}}">\n                <input\n                    id="{{ctrl.inputId}}-{{$index}}"\n                    type="checkbox"\n                    name="{{ctrl.name}}"\n                    ng-model="ctrl.selectedItems[$index]"\n                    ng-change="ctrl.updateValues()"\n                    ng-disabled="properties.disabled">\n                {{ (ctrl.getLabel(choice) || choice) | uiTranslate }}\n            </label>\n\n    </div>\n\n    <div ng-if="!properties.inline"\n         class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <div ng-if="!properties.inline" class="checkbox checklist-margin"\n                 ng-repeat="choice in properties.availableValues track by $index">\n                <label for="{{ctrl.inputId}}-{{$index}}">\n                    <input\n                        id="{{ctrl.inputId}}-{{$index}}"\n                        type="checkbox"\n                        name="{{ctrl.name}}"\n                        ng-model="ctrl.selectedItems[$index]"\n                        ng-change="ctrl.updateValues()"\n                        ng-disabled="properties.disabled">\n                    {{ (ctrl.getLabel(choice) || choice) | uiTranslate }}\n                </label>\n            </div>\n    </div>\n    </fieldset>\n</div>\n'
    };
  });
