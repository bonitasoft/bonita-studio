function PbLinkCtrl($scope, $location, $window, httpParamSerializer) {

  'use strict';

  this.getHref = function () {
    var appToken = getAppToken('app');
    if ($scope.properties.type === 'page') {
      return getPortalUrl() + '/apps/' + (appToken || 'APP_TOKEN_PLACEHOLDER') + '/' + $scope.properties.pageToken + buildQueryString();
    } else if ($scope.properties.type === 'process') {
      return getPortalUrl() + '/portal/form/process/' + $scope.properties.processName + '/' + $scope.properties.processVersion + buildQueryString(appToken ? {app: appToken} : null);
    } else if ($scope.properties.type === 'task') {
      return getPortalUrl() + '/portal/form/taskInstance/' + $scope.properties.taskId + buildQueryString(appToken ? {app: appToken} : null);
    } else if ($scope.properties.type === 'overview') {
      return getPortalUrl() + '/portal/form/processInstance/' + $scope.properties.caseId + buildQueryString(appToken ? {app: appToken} : null);
    } else {
      return $scope.properties.targetUrl;
    }
  };

  this.getTarget = function () {
    if ($scope.properties.type === 'page') {
      return '_top';
    }
    return $scope.properties.target;
  };

  function buildQueryString(additionalParams) {
    var params = angular.extend({}, $scope.properties.urlParams || {}, additionalParams || {});
    var queryString = httpParamSerializer.paramSerializer(params);
    return queryString ? '?' + queryString : '';
  }

  function getAppToken(paramName) {
    if ($scope.properties.appToken) {
      return $scope.properties.appToken;
    }
    var appTokenParam = getUrlParam(paramName);
    if (appTokenParam) {
      return appTokenParam;
    }
    var appsURLPattern = '\/apps\/([^/]*)\/';
    var urlMatches = $location.absUrl().match(appsURLPattern) || $window.top.location.href.match(appsURLPattern);
    if (urlMatches) {
      return urlMatches[1];
    }
    return null;
  }

  /**
   * Extract the param value from a URL query
   * e.g. if param = "id", it extracts the id value in the following cases:
   *  1. http://localhost/bonita/portal/resource/process/ProcName/1.0/content/?id=8880000
   *  2. http://localhost/bonita/portal/resource/process/ProcName/1.0/content/?param=value&id=8880000&locale=en
   *  3. http://localhost/bonita/portal/resource/process/ProcName/1.0/content/?param=value&id=8880000&locale=en#hash=value
   * @returns {id}
   */
  function getUrlParam(paramName) {
    var paramValue = $location.absUrl().match('[\/?&]' + paramName + '=([^&#]*)($|[&#])');
    if (paramValue) {
      return paramValue[1];
    }
    return '';
  }

  function getPortalUrl() {
    var locationHref = $location.absUrl();
    var indexOfPortal = locationHref.indexOf('/portal/');
    if (indexOfPortal >= 0) {
      return locationHref.substring(0, indexOfPortal);
    } else {
      //in case of a layout instead of a page/form, the servlet mapping is /apps/* instead of /portal/*
      var indexOfApps = locationHref.indexOf('/apps/');
      if (indexOfApps >= 0) {
        return locationHref.substring(0, indexOfApps);
      } else {
        //Make the link work in case we are in the preview and the target process is deployed in the portal
        return '/bonita';
      }
    }
  }
}
