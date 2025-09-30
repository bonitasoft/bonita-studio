function PbChartCtrl($scope, $log, uiTranslateFilter) {

  function isMultiSeriesChart(chartType) {
    return ["Line", "Bar", "Radar"].indexOf(chartType) > -1;
  }

  function isFlatArray(array) {
    return array && array[0] && !Array.isArray(array[0]);
  }

  function translateArray(array) {
    return (array || []).map(function(item) {
      return uiTranslateFilter(item);
    })
  }

  $scope.$watch('properties.data', function(value) {
    if (isMultiSeriesChart($scope.properties.type) && isFlatArray($scope.properties.data)) {
      $scope.data = [$scope.properties.data];
    } else {
      $scope.data = $scope.properties.data;
    }
  });

  $scope.$watch('properties.colors', function(value) {
    $scope.colors = ($scope.properties.colors || []).length > 0 ? $scope.properties.colors : null;
  });

  $scope.$watch('properties.options', function(value) {
    if (angular.isString(value)) {
      try {
        $scope.options = angular.fromJson(value);
      } catch (e) {
        $log.error('[Chart widget] Advanced options property should be a valid json object, ex: { "animateRotate" : false }');
      }
    } else {
      $scope.options = value;
    }
  });

  $scope.$watch('properties.labels', function(labels) {
    if(angular.isArray(labels)) {
      $scope.labels = translateArray(labels);
    } else {
      $log.error('[Chart widget] Property named "labels" should be bound to an array');
    }
  });

  $scope.$watch('properties.setLabels', function(setLabels) {
    if(angular.isArray(setLabels)) {
      $scope.setLabels = translateArray(setLabels);
    } else {
      $log.error('[Chart widget] Property named "setLabels" should be bound to an array');
    }
  });
}
