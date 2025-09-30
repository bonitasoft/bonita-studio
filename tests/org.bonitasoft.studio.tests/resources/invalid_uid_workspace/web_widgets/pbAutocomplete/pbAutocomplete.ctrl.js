function PbAutocompleteCtrl($scope, $parse, $log, widgetNameFactory) {

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
