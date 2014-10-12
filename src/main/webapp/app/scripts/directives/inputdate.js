'use strict';

/**
 * @ngdoc directive
 * @name iguassuApp.directive:InputDate
 * @description
 * # InputDate
 */
angular.module('iguassuApp')
  .directive('InputDate', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the InputDate directive');
      }
    };
  });
