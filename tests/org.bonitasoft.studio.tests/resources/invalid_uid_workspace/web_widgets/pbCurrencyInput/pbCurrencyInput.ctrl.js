function PbCurrencyInputCtrl($scope, $log, widgetNameFactory) {

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
