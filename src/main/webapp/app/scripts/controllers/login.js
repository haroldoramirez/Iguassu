'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('LoginCtrl', function ($routeParams, $scope, $rootScope, $http, $window, $cookieStore, Autenticacao, toastr) {
						// method="POST" action="http://localhost:8080/Iguassu/autenticacao" 
      //       enctype="application/x-www-form-urlencoded"
    if ($routeParams.error) {
			toastr.error('Erro ao efetuar login')
    };

  });
