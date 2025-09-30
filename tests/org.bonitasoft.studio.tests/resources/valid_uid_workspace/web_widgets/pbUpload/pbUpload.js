(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbUpload', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbUploadCtrl($scope, $sce, $element, widgetNameFactory, $timeout, $log, $sanitize, gettextCatalog) {
  var ctrl = this;

  this.filename = '';
  this.filemodel = '';

  this.clear = clear;
  this.startUploading = startUploading;
  this.uploadError = uploadError;
  this.uploadComplete = uploadComplete;

  this.name = widgetNameFactory.getName('pbUpload');
  this.inputId = widgetNameFactory.getId('pbUpload');

  this.preventFocus = function($event) {
    $event.target.blur();
  };

  this.submitForm = function() {
    var form = $element.find('form');
    form.triggerHandler('submit');
    form[0].submit();
  };

  this.forceSubmit = function(event) {
    if(!event.target.value) {
      return;
    }
    ctrl.submitForm();
    event.target.value = null;
  };

  var input = $element.find('input');
  input.on('change', ctrl.forceSubmit);
  $scope.$on('$destroy', function() {
    input.off('change', ctrl.forceSubmit);
  });

  $scope.$watch('properties.url', function(newUrl, oldUrl){
    ctrl.url = $sce.trustAsResourceUrl(newUrl);
    if (newUrl === undefined) {
      $log.warn('you need to define a url for pbUpload');
    }
  });

  //the filename displayed is not bound to the value as a bidirectionnal
  //bond, thus, in case the value is updated, it is not reflected
  //to the filename (example with the BS-14498)
  //we watch the value to update the filename and the upload widget state
  $scope.$watch(function(){return $scope.properties.value;}, function(newValue){
    if (newValue && newValue.filename) {
      ctrl.filemodel = true;
      ctrl.filename = $sanitize(newValue.filename);
    } else if (!angular.isDefined(newValue) || newValue === null) {
      delete ctrl.filemodel;
      delete ctrl.filename;
    }
  });

  if (!$scope.properties.isBound('value')) {
    $log.error('the pbUpload property named "value" need to be bound to a variable');
  }

  function clear() {
    ctrl.filename = '';
    ctrl.filemodel = '';
    $scope.properties.value = {};
  }

  function uploadError(error) {
    $log.warn('upload fails too', error);
    ctrl.filemodel = '';
    ctrl.filename = gettextCatalog.getString('Upload failed');
  }

  function startUploading() {
    ctrl.filemodel = '';
    ctrl.filename  = gettextCatalog.getString('Uploading...');
  }

  function uploadComplete(response) {
    //when the upload widget return a String, it means an error has occurred (with a html document as a response)
    //if it's not a string, we test if it contains some error message
    if(angular.isString(response) || (response && response.type && response.message)){
      $log.warn('upload failed');
      ctrl.filemodel = '';
      ctrl.filename = gettextCatalog.getString('Upload failed');
      $scope.properties.errorContent = angular.isString(response) ? response : response.message;
      return;
    }
    // Restore error message when upload is complete
    $scope.properties.errorContent = undefined;
    $scope.properties.value = response;
  }
}
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            for="{{ctrl.inputId}}-label"\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            for="{{ctrl.inputId}}-label"\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n           <form action="{{ctrl.url}}"\n                 ng-upload="ctrl.uploadComplete(content)"\n                 ng-upload-loading="ctrl.startUploading()"\n                 error-catcher="ctrl.uploadError(error)"\n                 upload-options-enable-csrf\n                 upload-options-csrf-param="CSRFToken">\n                <div class="input-group file-upload">\n                    <input aria-labelledby="{{ctrl.inputId}}-label" title="{{ctrl.name}}-text" type="text" ng-focus="ctrl.preventFocus($event)" placeholder="{{properties.placeholder | uiTranslate}}" value="{{ctrl.filename}}" class="form-control">\n                    <button type="button" ng-if="ctrl.filemodel" ng-click="ctrl.clear()" class="file-upload-clear">\n                        <i class="glyphicon glyphicon-remove-circle"></i>\n                        <span class="hide" translate>Close</span>\n                    </button>\n                    <span class="input-group-btn">\n                        <span class="btn" ng-class="{\'btn-default disabled\':$isUploading, \'btn-primary\':!$isUploading}">\n                            <input class="file-upload-input"\n                                   id="{{ctrl.inputId}}-label"\n                                   ng-class="{\'file-upload-input--disabled\':$isUploading}"\n                                   name="{{ctrl.name}}" type="file"\n                                   ng-required="properties.required"\n                                   ng-model="ctrl.filemodel" />\n                            <i class="glyphicon" ng-class="{\'glyphicon-cloud-upload\':$isUploading, \'glyphicon-paperclip\':!$isUploading}"></i>\n                        </span>\n                    </span>\n                </div>\n            </form>\n            <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error " ng-messages-include="forms-generic-errors.html" role="alert"></div>\n        </div>\n    </div>\n</div>\n'
    };
  });
