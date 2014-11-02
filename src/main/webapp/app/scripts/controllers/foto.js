'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FotoCtrl
 * @description
 * # FotoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('FotoCtrl', function ($scope, $routeParams) {
    $scope.id = $routeParams.id + '.jpg';
    console.log($scope.id);
  });
