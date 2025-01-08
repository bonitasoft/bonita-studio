(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbTable', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbTableCtrl($scope) {

  this.isArray = Array.isArray;

  this.isClickable = function () {
    return $scope.properties.isBound('selectedRow');
  };

  this.selectRow = function (row) {
    if (this.isClickable()) {
      $scope.properties.selectedRow = row;
    }
  };

  this.isSelected = function(row) {
    return angular.equals(row, $scope.properties.selectedRow);
  }
}
,
      template: '<div class="table-responsive">\n    <table class="table" ng-class="{\'table-hover\': ctrl.isClickable(), \'table-striped\': properties.striped, \'table-condensed\': properties.condensed, \'table-bordered\': properties.bordered}">\n        <caption ng-if="properties.caption">{{properties.caption}}</caption>\n        <thead>\n            <tr>\n                <th ng-repeat="header in properties.headers">\n                     <span ng-if="properties.allowHTML" ng-bind-html="header | uiTranslate"></span>\n                  	 <span ng-if="!properties.allowHTML">{{ header | uiTranslate }}</span>\n                </th>\n            </tr>\n        </thead>\n        <tbody ng-if="ctrl.isArray(properties.content) && ctrl.isArray(properties.columnsKey)">\n            <tr ng-repeat="row in properties.content" ng-click="ctrl.selectRow(row)" ng-class="{\'info\': ctrl.isSelected(row)}">\n                <td ng-if="!properties.allowHTML" ng-repeat="column in properties.columnsKey track by $index">\n                    {{ $eval(column, row) | uiTranslate }}\n                </td>\n                <td ng-if="properties.allowHTML" ng-repeat="column in properties.columnsKey track by $index">\n                    <div ng-if="properties.allowHTML" ng-bind-html="$eval(column, row) | uiTranslate"></div>\n                </td>\n            </tr>\n        </tbody>\n        <tbody ng-if="ctrl.isArray(properties.content) && !ctrl.isArray(properties.columnsKey)">\n            <tr ng-repeat="row in properties.content" ng-click="ctrl.selectRow(row)" ng-class="{\'info\': ctrl.isSelected(row)}">\n                <td ng-if="!properties.allowHTML"> {{ row | uiTranslate }}</td>\n                <td ng-if="properties.allowHTML" ng-bind-html="row | uiTranslate"></td>\n            </tr>\n        </tbody>\n    </table>\n</div>\n'
    };
  });
