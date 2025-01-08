


function RichTextAreaWidgetController($scope, $log, widgetNameFactory) {
  'use strict';
  // 9007199254740991 it's value of MAX_SAFE_INTEGER, we override this here because this const isn't be support on IE11
  var MAX_LENGTH = 9007199254740991;
  // Avoid errors in developer console
  // see https://bonitasoft.atlassian.net/browse/BS-16345
  this.maxText = $scope.properties.maxLength || MAX_LENGTH;
  this.minText = $scope.properties.minLength || 0;

  //Init cannot do length on undefined object
  // see https://bonitasoft.atlassian.net/browse/BS-16736
  if($scope.properties.value === undefined) {
    $scope.properties.value = "";
  }

  this.name = widgetNameFactory.getName('richTextAreaWidget');
    this.toolbars = [];
    if(angular.isArray($scope.properties.toolbarsGrp1) && $scope.properties.toolbarsGrp1.length > 0) {
      this.toolbars.push($scope.properties.toolbarsGrp1);
    }
    if(angular.isArray($scope.properties.toolbarsGrp2) && $scope.properties.toolbarsGrp2.length > 0) {
      this.toolbars.push($scope.properties.toolbarsGrp2);
    }
    if(angular.isArray($scope.properties.toolbarsGrp3) && $scope.properties.toolbarsGrp3.length > 0) {
      this.toolbars.push($scope.properties.toolbarsGrp3);
    }
    if(angular.isArray($scope.properties.toolbarsGrp4) && $scope.properties.toolbarsGrp4.length > 0) {
      this.toolbars.push($scope.properties.toolbarsGrp4);
    }

  if (!$scope.properties.isBound('value')) {
    $log.error('the richTextAreaWidget property named "value" need to be bound to a variable');
  }
}
