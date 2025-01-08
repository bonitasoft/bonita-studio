(function () {
  'use strict';

  angular
    .module('bonitasoft.ui.directives')
    .directive('boxViewer', function($filter) {
      return {
        require: '^pbFileViewer',
        restrict: 'A',
        link: function(scope, element, attr, ctrl) {
          scope.$watch(function() {
            return ctrl.fileName;
          }, function() {
            var opts = {
              width: '100%',
              height: '100%',
              autoScale: true,
              closeClick: true,
              enableEscapeButton: true,
              errorMsg: $filter('uiTranslate')('The requested content cannot be loaded.<br />Please try again later.'),
              type: getType(),
              title: ctrl.fileName
            };

            // On mobile device, do not apply fancybox but open the file in new tab
            // see https://bonitasoft.atlassian.net/browse/BS-16997
            if (ctrl.isMobileDevice()) {
              element.attr('target', '_blank');
            } else {
              $(element).fancybox(opts);
            }

            function getType() {
              if(ctrl.isImage()) {
                return 'image';
              } else {
                return 'iframe';
              }
            }
          });
        }
      };
    });

}());

