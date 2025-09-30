var module;
try {
  module = angular.module('bonitasoft.ui.fragments');
} catch (e) {
  module = angular.module('bonitasoft.ui.fragments', []);
  angular.module('bonitasoft.ui').requires.push('bonitasoft.ui.fragments');
}
module.directive('pbFragmentMyFragment', function() {
  return {
    template: '<div>    <div class="row">\n        <div pb-property-values=\'11458ce5-1338-4ec8-b50a-c104f36c6a24\'>\n    <div ng-if="!properties.hidden" class="component col-xs-12  col-sm-12  col-md-12  col-lg-12" ng-class="properties.cssClasses">\n        <custom-my-widget></custom-my-widget>\n    </div>\n</div>\n    </div>\n</div>'
  };
});
