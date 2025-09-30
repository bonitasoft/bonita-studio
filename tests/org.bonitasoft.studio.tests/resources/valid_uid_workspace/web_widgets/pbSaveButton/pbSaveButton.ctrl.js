function PbSaveButtonCtrl($scope, $log, $window, $timeout, localStorageService) {

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
