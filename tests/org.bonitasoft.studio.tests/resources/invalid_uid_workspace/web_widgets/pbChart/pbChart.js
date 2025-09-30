(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbChart', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbChartCtrl($scope, $log, uiTranslateFilter) {

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
,
      template: '<canvas ng-if="!environment"\n        class="chart-base"\n        chart-type="properties.type"\n        chart-data="data"\n        chart-labels="labels"\n        chart-legend="{{ !properties.legendHidden }}"\n        chart-series="setLabels"\n        chart-colours="colors"\n        chart-options="options">\n</canvas>\n<svg ng-if="environment" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 20">\n    <path d="M20.53 20H15v-5.045h5.53V20zm7.21-7.44h-5.584V20h5.584v-7.44zm7.156-3.262h-5.584V20h5.584V9.298zM35 0l-4.525.736.996.946-6.805 6.148-2.472-2.196-6.92 6.48 1.702 1.575 5.147-4.84 2.55 2.27 8.495-7.798.967.918L35 0z" fill="#ccc"/>\n</svg>\n'
    };
  });
