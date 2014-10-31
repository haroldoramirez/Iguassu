'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:EmpresaCtrl
 * @description
 * # EmpresaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('EmpresaCtrl', function ($scope, $rootScope, $routeParams, $location, Empresa, $document, toastr, createAddress) {

    var inicio = 0;
    var fim = 10;

   $scope.init = function(){
    if ($routeParams.id) {
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresa = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
      });
      $rootScope.openAll();
    }else{
      $scope.clear(); 
      $scope.endereco = createAddress.desformateEndereco(null);
    };
    $scope.getEmpresas();
   };

  $scope.save = function(){
    $scope.empresa.endereco = $scope.endereco;
    if ($scope.empresa.endereco.bairro.id===null) {
      $scope.empresa.endereco.bairro = null;
    }
    var msg = 'cadastrada com sucesso';
    if($scope.empresa.id){
      msg = 'atualizada com sucesso';
    }
    console.log($scope.empresa);
    Empresa.save($scope.empresa, function(data){
      $scope.empresa = data;
      $location.path('/empresas/'+$scope.empresa.id);
      $scope.getEmpresas();
      toastr.success(msg,$scope.empresa.nome);
      $document.scrollTopAnimated(0, 700);
      $scope.init();
    });
  };    

  $scope.search = function(){
    if ($scope.empresa.id) {
      Empresa.get({id: $scope.empresa.id}, function(data){
        $scope.empresas = [];
        $scope.empresas[0] = data;
      });
    }else if ($routeParams.id){
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresas = [];
        $scope.empresas[0] = data;
      });
    }else {
      Empresa.query({nome: $scope.empresa.nome, CNPJ: $scope.empresa.cnpj, inicio: inicio, fim:fim}, function(data){
        $scope.empresas = data;
      });
    };
  };


  $scope.edit = function(empresa){
    $document.scrollTopAnimated(0, 700);
    $location.path('/empresas/'+empresa.id);
  }

  $scope.next = function(){
    // inicio = inicio + 10;
    fim = fim + 10;
    Empresa.query({nome: $scope.empresa.nome, CNPJ: $scope.empresa.cnpj, inicio: inicio, fim:fim}, function(data){
      $scope.empresas = data;
    });
  }

  $scope.older = function(){

  }

  $scope.clear = function(){
    $scope.endereco = createAddress.desformateEndereco(null);
    $scope.empresa = {};
    $document.scrollTopAnimated(0, 700);
    if ($routeParams.id) {
      $location.path('/empresas');
    };
  }


});
