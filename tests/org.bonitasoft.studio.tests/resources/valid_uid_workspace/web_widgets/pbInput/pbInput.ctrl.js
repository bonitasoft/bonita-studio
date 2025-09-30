function PbInputCtrl($scope, $log, widgetNameFactory) {

  'use strict';

  this.name = widgetNameFactory.getName('pbInput');
  this.inputId = widgetNameFactory.getId('pbInput');
  this.ngModelOptions = { allowInvalid: true, debounce: $scope.properties.debounce }

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbInput property named "value" need to be bound to a variable');
  }
}
