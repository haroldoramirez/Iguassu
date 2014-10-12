'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
