(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbSelect', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbSelectCtrl($scope, $parse, $log, widgetNameFactory) {
  var ctrl = this;

  ctrl.internalValue = undefined;

  function comparator(initialValue, item) {
    return angular.equals(initialValue, ctrl.getValue(item));
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

  this.findSelectedItem = function (items) {
    return items.filter(comparator.bind(null, $scope.properties.value))
      .map(function (item) {
        return ctrl.getValue(item);
      })[0];
  };

  this.setSelectedValue = function (foundItem) {
    if (angular.isDefined(foundItem)) {
      ctrl.internalValue = foundItem;
    } else {
      ctrl.internalValue = null;
      $scope.properties.value = null;
    }
  };

  this.updateValue = function () {
    $scope.properties.value = ctrl.internalValue;
  };

  $scope.$watchCollection('properties.availableValues', function(items) {
    if (Array.isArray(items)) {
      var foundItem = ctrl.findSelectedItem(items);

      ctrl.setSelectedValue(foundItem);
    }
  });

  $scope.$watch('properties.value', function(value) {
    if (angular.isDefined(value) && value !== null) {
      var items = $scope.properties.availableValues;
      if (Array.isArray(items)) {
        var foundItem = ctrl.findSelectedItem(items);
        ctrl.setSelectedValue(foundItem);
      }
    }
  });

  this.name = widgetNameFactory.getName('pbSelect');

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbSelect property named "value" need to be bound to a variable');
  }
}
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}" >\n            <select\n                class="form-control"\n                name="{{ctrl.name}}"\n                ng-model="ctrl.internalValue"\n                ng-change="ctrl.updateValue()"\n                ng-model-options="{ allowInvalid: true }"\n                ng-options="ctrl.getValue(option) as (ctrl.getLabel(option) | uiTranslate) for option in properties.availableValues"\n                ng-required="properties.required"\n                ng-disabled="properties.disabled">\n                <option style="display:none" value="">\n                    {{ properties.placeholder | uiTranslate }}\n                </option>\n            </select>\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error " ng-messages-include="forms-generic-errors.html" role="alert"></div>\n        </div>\n    </div>\n</div>\n'
    };
  });
