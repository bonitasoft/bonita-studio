function PbRadioBoxCtrl($scope, $parse, $log, widgetNameFactory) {

  'use strict';
  var ctrl = this;
  /**
   * Watch the data source and set wrapChoices and $scope.properties.values
   */
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

  $scope.$watchCollection('properties.availableValues', function(items){
    if (Array.isArray(items)) {
      var foundValue = items
        .filter(comparator.bind(null, $scope.properties.selectedValue))
        .reduce(function (acc, item) {
          return ctrl.getValue(item);
        }, undefined);
      if (foundValue) {
        $scope.properties.selectedValue = foundValue;
      }
    }
  });

  this.name = widgetNameFactory.getName('pbRadioBox');
  this.inputId = widgetNameFactory.getId('pbRadioBox');

  if (!$scope.properties.isBound('selectedValue')) {
    $log.error('the pbRadioBox property named "selectedValue" need to be bound to a variable');
  }
}
