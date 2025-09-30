(function () {
  'use strict';
  angular.module('textAngular-i18n', ['bonitasoft.ui.i18n', 'textAngular']).config(
    function($provide) {
      $provide.decorator('taTranslations', function($delegate, gettextCatalog, i18n) {
        i18n.init();
        
        $delegate.clear.tooltip = 'Toggle the text area to HTML source code / Rich text area';
        Object.keys($delegate).forEach(function(item) {
          Object.keys($delegate[item]).forEach(function(key) {
            $delegate[item][key] = gettextCatalog.getString($delegate[item][key]);
          });
        });
        return $delegate;
      });
  });
})();
