'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('MenuCtrl', function ($rootScope, $scope, $http, $modal, $log, $location, $routeParams, BaseUrl, Empresa, Cargo, Pais, Candidato) {

  $rootScope.candidato = {};

  $rootScope.getCandidatos = function(){
      $rootScope.candidatos = Candidato.getAll();
  };

  $rootScope.getPaises = function(){
     $rootScope.paises = Pais.getAll();
  };

  $rootScope.getEmpresas = function(){
    $rootScope.empresas = Empresa.getAll();
  };

  $rootScope.getCargos = function(){
    $rootScope.cargos = Cargo.getAll();
  };

  $scope.isActive = function(viewLocation) {
    return viewLocation === $location.path();
  };


});
