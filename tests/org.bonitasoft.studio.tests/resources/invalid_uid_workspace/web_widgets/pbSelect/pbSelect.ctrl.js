function PbSelectCtrl($scope, $parse, $log, widgetNameFactory) {
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
