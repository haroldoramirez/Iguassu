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

//OPÇÕES DAS FERRAMENTAS

	$rootScope.panel = {
     open1 : null,
     open2 : null,
     open3 : null,
     open4 : null,
     open5 : null,
     open6 : null,
     endereco : null
   };

  $rootScope.isCollapsed = false;

  $rootScope.isOpen = false;

  $rootScope.closeAll = function(){
    $rootScope.isOpen = true;
    $rootScope.panel = {
      open1 : false,
      open2 : false,
      open3 : false,
      open4 : false,
      open5 : false,
      open6 : false,
      endereco : false
    };
  };

  $rootScope.openAll = function(){
    $rootScope.isOpen = false;
    $rootScope.panel = {
      open1 : true,
      open2 : true,
      open3 : true,
      open4 : true,
      open5 : true,
      open6 : true,
      endereco : true
    };
  }; 

});
