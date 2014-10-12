'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('MenuCtrl', function ($scope, $http, $modal, $log, $location, $routeParams, BaseUrl) {


  $scope.isActive = function(viewLocation) {
    return viewLocation === $location.path();
  };


});
