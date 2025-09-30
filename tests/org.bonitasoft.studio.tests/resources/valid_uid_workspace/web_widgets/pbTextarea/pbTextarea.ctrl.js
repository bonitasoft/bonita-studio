function PbTextAreaCtrl($scope, $log, widgetNameFactory) {

  'use strict';

  this.name = widgetNameFactory.getName('pbTextArea');
  this.inputId = widgetNameFactory.getId('pbTextArea');

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbTextArea property named "value" need to be bound to a variable');
  }
}
