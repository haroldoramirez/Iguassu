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

  $scope.endereco = {};

  $scope.pagina = 0; 
  
  $scope.init = function(){
    if ($routeParams.id) {
      Vaga.get({id: $routeParams.id}, function(data){
        $scope.vaga = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
      });
      $rootScope.openAll();
    };
    $scope.getEmpresas();
    $scope.getCargos();
    Vaga.query({pagina: $scope.pagina},{},function(data){
      $scope.vagas = data;
    });
  };

  $scope.save = function(){
    $scope.vaga.endereco = createAddress.formateSaveEndereco($scope.endereco);
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
    }, function(){
      toastr.error('Verifique se há dados inconsistentes','Não foi possível salvar essas informações');
    });
  };

  $scope.search = function(){
    $scope.vaga.endereco = createAddress.formateEndereco($scope.endereco);
    console.log($scope.vaga);
    Vaga.query({pagina: $scope.pagina}, $scope.vaga, function(data){
      $scope.vagas = data;
    });
  };

  $scope.next = function(){
    $scope.pagina = $scope.pagina + 1;
    Vaga.query({pagina: $scope.pagina}, $scope.vaga, function(data){
      if (data.length===0) {
        $scope.pagina = $scope.pagina - 1;
      }else{
        $scope.vagas = data;
      };
    });
  }

  $scope.older = function(){
    $scope.pagina = $scope.pagina - 1;
    Vaga.query({pagina: $scope.pagina}, $scope.vaga, function(data){
      $scope.vagas = data;
    });
  }
  
  $scope.edit = function(vaga){
    $document.scrollTopAnimated(0, 700);
    $location.path('/vagas/'+vaga.id);
  }
  
  $scope.clear = function(){
    $scope.endereco = null;
    $scope.endereco = createAddress.desformateEndereco($scope.endereco);
    $scope.endereco = {};
    $scope.vaga = {};
    $document.scrollTopAnimated(0, 700);
    if ($routeParams.id) {
      $location.path('/vagas');
    };
  }

});
