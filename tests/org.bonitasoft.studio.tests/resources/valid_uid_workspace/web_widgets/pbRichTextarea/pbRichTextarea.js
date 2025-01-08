(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbRichTextarea', function() {
    return {
      controllerAs: 'ctrl',
      controller: 


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
,
      template: '<div ng-class="{\n    \'form-horizontal\': properties.labelPosition === \'left\' && !properties.labelHidden,\n    \'row\': properties.labelPosition === \'top\' && !properties.labelHidden || properties.labelHidden\n    }">\n    <div class="form-group">\n        <label\n            ng-if="!properties.labelHidden && properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind-html="properties.label | uiTranslate">\n        </label>\n        <label\n            ng-if="!properties.labelHidden && !properties.allowHTML"\n            ng-class="{ \'control-label--required\': properties.required }"\n            class="control-label col-xs-{{ properties.labelPosition === \'left\' ? properties.labelWidth : 12 }}"\n            ng-bind="properties.label | uiTranslate">\n        </label>\n        <div class="col-xs-{{ 12 - (!properties.labelHidden && properties.labelPosition === \'left\' ? properties.labelWidth : 0) }}">\n            <div ng-if="!environment">\n                <div text-angular\n                    name="{{ctrl.name}}"\n                    ta-disabled="properties.readOnly"\n                    ng-model="properties.value"\n                    ta-toolbar="{{ctrl.toolbars}}"\n                    ng-required="{{properties.required}}"\n                    ta-min-text="{{ctrl.minText}}"\n                    ta-max-text="{{ctrl.maxText}}">\n                </div>\n                <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error" ng-messages-include="forms-generic-errors.html" role="alert"></div>\n                <div ng-messages="$form[ctrl.name].$dirty && $form[ctrl.name].$error" role="alert">\n                    <div ng-message="taMinText" class="text-danger">\n                        {{\'This value is too small\' | uiTranslate }}\n                    </div>\n                    <div ng-message="taMaxText" class="text-danger">\n                        {{\'This value is too large\' | uiTranslate }}\n                    </div>\n                </div>\n            </div>\n            <div ng-if="environment">\n                <div class="col-xs-12" style="background-color: #F0F0F0;padding: 10px 10px 5px;margin-left: 0px;border: 1px solid #EEE;">\n                    <div>\n                        <div class="btn-group">\n                            <button type="button" class="btn btn-default " name="h1">H1</button>\n                            <button type="button" class="btn btn-default " name="h2">H2</button>\n                            <button type="button" class="btn btn-default " name="h3">H3</button>\n                            <button type="button" class="btn btn-default " name="h4">H4</button>\n                            <button type="button" class="btn btn-default " name="h5">H5</button>\n                            <button type="button" class="btn btn-default " name="h6">H6</button>\n                            <button type="button" class="btn btn-default " name="p">P</button>\n                            <button type="button" class="btn btn-default " name="ul"><i class="fa fa-list-ul"></i></button>\n                            <button type="button" class="btn btn-default " name="ol"><i class="fa fa-list-ol"></i></button>\n                        </div>\n                        <div class="btn-group">\n                            <button type="button" class="btn btn-default " name="bold"><i class="fa fa-bold"></i></button>\n                            <button type="button" class="btn btn-default " name="italics"><i class="fa fa-italic"></i></button>\n                            <button type="button" class="btn btn-default " name="underline"><i class="fa fa-underline"></i></button>\n                            <button type="button" class="btn btn-default " name="strikeThrough"><i class="fa fa-strikethrough"></i></button>\n                            <button type="button" class="btn btn-default " name="pre">pre</button>\n                            <button type="button" class="btn btn-default " name="quote"><i class="fa fa-quote-right"></i></button>\n                        </div>\n                        <div class="btn-group">\n                          <button type="button" class="btn btn-default " name="justifyLeft"><i class="fa fa-align-left"></i></button>\n                            <button type="button" class="btn btn-default " name="justifyCenter"><i class="fa fa-align-center"></i></button>\n                            <button type="button" class="btn btn-default " name="justifyRight"><i class="fa fa-align-right"></i></button>\n                            <button type="button" class="btn btn-default " name="indent"><i class="fa fa-indent"></i></button>\n                            <button type="button" class="btn btn-default " name="outdent"><i class="fa fa-outdent"></i></button>\n                        </div>\n                        <div class="btn-group">\n                            <button type="button" class="btn btn-default " name="html"><i class="fa fa-code"></i></button>\n                            <button type="button" class="btn btn-default " name="insertImage"><i class="fa fa-picture-o"></i></button>\n                            <button type="button" class="btn btn-default " name="insertLink"><i class="fa fa-link"></i></button>\n                            <button type="button" class="btn btn-default " name="insertVideo"><i class="fa fa-youtube-play"></i></button>\n                            <div style="display:block; min-width:100px;" class="btn btn-default " name="wordcount">\n                              Words: <span>0</span>\n                            </div>\n                            <div style="display:block; min-width:120px;" class="btn btn-default " name="charcount">\n                              Characters: <span>-1</span>\n                            </div>\n                            <button type="button" class="btn btn-default " name="undo"><i class="fa fa-undo"></i></button>\n                            <button type="button" class="btn btn-default " name="redo"><i class="fa fa-repeat"></i></button>\n                            <button type="button" class="btn btn-default " name="clear"><i class="fa fa-ban"></i></button>\n                        </div>\n                    </div>\n                </div>\n                <div>\n                    <textarea class="form-control" style="resize: none;"></textarea>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n'
    };
  });
