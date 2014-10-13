'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:VagaCtrl
 * @description
 * # VagaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('VagaCtrl', function ($scope, $routeParams, $location, Vaga, Empresa, Cargo) {
                          
     $scope.vaga = {};
    
     $scope.init = function(){
    
       if ($routeParams.id) {
         Vaga.get({id: $routeParams.id}, function(data){
           $scope.vaga = data;
         });
       };
         $scope.getVagas();
         $scope.getCargos();
         $scope.getEmpresas();
     };


    $scope.save = function(){
      Vaga.save($scope.vaga, function(data){
        $scope.vaga = data;
      });
    };

    $scope.delete = function(){
    
    };
    
    $scope.getVagas = function(){
      $scope.vagas = Vaga.getAll();
    };

    $scope.editVaga = function(vaga){
      $location.path('/vagas/'+vaga.id);
    }
    
    $scope.limpar = function(){
      $scope.vaga = null;
    }

    $scope.getCargos = function(){
      $scope.cargos = Cargo.getAll();
    };

    $scope.getEmpresas = function(){
      $scope.empresas = Empresa.getAll();
    };

});
