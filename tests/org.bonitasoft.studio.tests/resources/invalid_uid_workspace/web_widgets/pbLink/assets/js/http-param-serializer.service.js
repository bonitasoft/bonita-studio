/**
 * This is a partial copy of AngularJS's $httpParamSerializer.
 * It can be removed when upgrading to Angular 1.4.x
 */
angular.module('bonitasoft.ui.services').factory('httpParamSerializer', function() {

    'use strict';

    function sortedKeys(obj) {
        return Object.keys(obj).sort();
    }

    function forEachSorted(obj, iterator, context) {
        var keys = sortedKeys(obj);
        for (var i = 0; i < keys.length; i++) {
            iterator.call(context, obj[keys[i]], keys[i]);
        }
        return keys;
    }

    function encodeUriQuery(val, pctEncodeSpaces) {
        return encodeURIComponent(val).
            replace(/%40/gi, '@').
            replace(/%3A/gi, ':').
            replace(/%24/g, '$').
            replace(/%2C/gi, ',').
            replace(/%3B/gi, ';').
            replace(/%20/g, (pctEncodeSpaces ? '%20' : '+'));
    }

    function serializeValue(v) {
        if (isObject(v)) {
            return isDate(v) ? v.toISOString() : toJson(v);
        }
        return v;
    }

    function isUndefined(value) { return typeof value === 'undefined'; }

    var isArray = Array.isArray;

    function isObject(value) {
        // http://jsperf.com/isobject4
        return value !== null && typeof value === 'object';
    }

    return {
        paramSerializer: function(params) {
            if (!params) return '';
            var parts = [];
            forEachSorted(params, function(value, key) {
                if (value === null || isUndefined(value)) return;
                if (isArray(value)) {
                    forEach(value, function(v, k) {
                        parts.push(encodeUriQuery(key)  + '=' + encodeUriQuery(serializeValue(v)));
                    });
                } else {
                    parts.push(encodeUriQuery(key) + '=' + encodeUriQuery(serializeValue(value)));
                }
            });
            return parts.join('&');
        }
    };
});