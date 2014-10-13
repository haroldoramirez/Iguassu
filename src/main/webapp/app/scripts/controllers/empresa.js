'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:EmpresaCtrl
 * @description
 * # EmpresaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('EmpresaCtrl', function ($scope, $routeParams, $location, Empresa) {

   $scope.empresa = {};

   $scope.init = function(){

    if ($routeParams.id) {
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresa = data;
      });
    };
    $scope.getEmpresa();
   };


  $scope.save = function(){
    Empresa.save($scope.empresa, function(data){
      $scope.empresa = data;
    });
  };

  $scope.delete = function(){

  };

  $scope.getEmpresa = function(){
    $scope.empresas = Empresa.getAll();
  };

  $scope.editEmpresa = function(empresa){
    $location.path('/empresas/'+empresa.id);
  }

  $scope.limpar = function(){
    $scope.empresa = null;
  }


});
