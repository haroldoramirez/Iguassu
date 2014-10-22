'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('HomeCtrl', function ($scope, $rootScope, $routeParams, Usuario) {
    
    if ($routeParams.success) {
			Usuario.getCurrent(function(data){
				$rootScope.usuario = data;
			});
    };
    
  });
