'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:VagaCtrl
 * @description
 * # VagaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('VagaCtrl', function ($scope, $rootScope, $routeParams, $location, $document, toastr, createAddress, Vaga, Empresa, Cargo) {
  
  $scope.vaga = {};
  
  $scope.init = function(){
    if ($routeParams.id) {
      Vaga.get({id: $routeParams.id}, function(data){
        $scope.vaga = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
      });
      $rootScope.openAll();
    };
    $scope.getVagas();
    $scope.getCargos();
    $scope.getEmpresas();
  };


  $scope.save = function(){
    $scope.vaga.endereco = $scope.endereco;
    var msg = 'Vaga cadastrada com sucesso';
    if($scope.vaga.id){
      msg = 'Vaga atualizada com sucesso';
    }
    Vaga.save($scope.vaga, function(data){
      $scope.vaga = data;
      $location.path('/vagas/'+$scope.vaga.id);
      $scope.getVagas();
      toastr.success(msg);
      $document.scrollTopAnimated(0, 700);
      $scope.init();
    });
  };
  
  $scope.edit = function(vaga){
    $document.scrollTopAnimated(0, 700);
    $location.path('/vagas/'+vaga.id);
  }
  
  $scope.clear = function(){
    $rootScope.endereco = {};
    $scope.vaga = {};
    $document.scrollTopAnimated(0, 700);
    if ($routeParams.id) {
      $location.path('/vagas');
    };
  }

});
