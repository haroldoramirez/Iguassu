'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('MenuCtrl', function ($rootScope, $location) {

  $rootScope.isActive = function(viewLocation) {
    return viewLocation === $location.path();
  };

  $rootScope.isCollapsed = false;

 $rootScope.isOpen = false;

 $rootScope.panel = {
   open1 : null,
   open2 : null,
   open3 : null,
   open4 : null,
   open5 : null,
   open6 : null,
   endereco : null
 };

});
