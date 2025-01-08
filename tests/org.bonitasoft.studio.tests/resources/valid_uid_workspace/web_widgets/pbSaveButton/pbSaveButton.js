(function () {
  try {
    return angular.module('bonitasoft.ui.widgets');
  } catch(e) {
    return angular.module('bonitasoft.ui.widgets', []);
  }
})().directive('pbSaveButton', function() {
    return {
      controllerAs: 'ctrl',
      controller: function PbSaveButtonCtrl($scope, $log, $window, $timeout, localStorageService) {

  'use strict';

  var vm = this;

  $scope.$watch('properties.formInput', function (newVal) {
    if (newVal) {
      vm.storageData = newVal;
      vm.notifyDataLoadable();
    }
  });

  /*
  * Load data from local storage only once (to init the form).
  */
  this.notifyDataLoadable = function notifyDataLoadable() {
    if (!vm.initialized && vm.isStorageAvailable()) {
      vm.loadFromLocalStorage();
      vm.initialized = true;
    }
  }

  this.isStorageAvailable = function isStorageAvailable() {
    return (localStorageService.isAvailable() && vm.storageData !== undefined);
  }

  this.loadFromLocalStorage = function loadFromLocalStorage() {
    var cachedValue = localStorageService.read($window.location.href);
    if (cachedValue) {
      // only override the formInput if there is data in LocalStorage. Otherwise keep the value defined in the form.
      $scope.properties.formInput = cachedValue;
    }
  }

  /*
  * Save in local storage.
  */
  this.saveInLocalStorage = function saveInLocalStorage() {
    if (vm.isStorageAvailable()) {
      localStorageService.save($window.location.href, $scope.properties.formInput);
      vm.saving = true;
      $timeout(vm.toggleSaveState, 500);
    } else {
      throw new Error("Ouups ! You are trying to save data in LocalStorage but it is not available on your Web Browser :-(");
    }

  };

  this.toggleSaveState = function toggleSaveState() {
    vm.saving = false;
  }
}
,
      template: '<div class="text-{{ properties.alignment }}">\n    <button ng-class="\'btn btn-\' + properties.buttonStyle" ng-click="ctrl.saveInLocalStorage()" type="button"\n            ng-disabled="properties.disabled || !ctrl.isStorageAvailable()">\n        <span\n            ng-class="{glyphicon:true, \'glyphicon-floppy-disk\':!ctrl.saving, \'glyphicon-floppy-saved\':ctrl.saving}"></span>\n        <ng-bind-html ng-if="properties.allowHTML" ng-bind-html="properties.label | uiTranslate"></ng-bind-html>\n        <span ng-if="!properties.allowHTML" ng-bind="properties.label | uiTranslate"></span>\n    </button>\n</div>\n'
    };
  });
