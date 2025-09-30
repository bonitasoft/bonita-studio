function PbCheckboxCtrl($scope, $log, widgetNameFactory) {

  $scope.$watch('properties.value', function(value) {
    if (value === 'true' || value === true) {
      $scope.properties.value = true;
    } else {
      $scope.properties.value = false;
    }
  });

  this.name = widgetNameFactory.getName('pbCheckbox');
  this.inputId = widgetNameFactory.getId('pbCheckbox');

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbCheckbox property named "value" need to be bound to a variable');
  }
}
