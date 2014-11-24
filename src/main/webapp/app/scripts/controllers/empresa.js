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

  $scope.empresa = {};

  $scope.endereco = {};

  $scope.pagina = 0;

  $scope.init = function(){
    if ($routeParams.id) {
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresa = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
        console.log($scope.endereco);
        $rootScope.openAll();
      });      
    }
    Empresa.query({pagina: $scope.pagina},{},function(data){
      $scope.empresas = data;
    });
  };

  $scope.save = function(){
    $scope.empresa.endereco = createAddress.formateSaveEndereco($scope.endereco);
    if ($scope.empresa.cnpj == '') {
      $scope.empresa.cnpj = null;
    };
    console.log($scope.empresa);
    Empresa.save($scope.empresa, function(data){
      toastr.success("Salvo com sucesso");
      $scope.clear();
      $scope.init();
    }, function(){
      toastr.error('Verifique se há dados inconsistentes ou se o CNPJ já esta cadastrado','Não foi possível salvar essas informações');
    });
  };    

  $scope.search = function(){
    $scope.empresa.endereco = createAddress.formateEndereco($scope.endereco);
    console.log($scope.empresa);
    Empresa.query({pagina: $scope.pagina}, $scope.empresa, function(data){
      $scope.empresas = data;
    });
  };

  $scope.next = function(){
    $scope.pagina = $scope.pagina + 1;
    Empresa.query({pagina: $scope.pagina}, $scope.empresa, function(data){
      if (data.length===0) {
        $scope.pagina = $scope.pagina - 1;
      }else{
        $scope.empresas = data;
      };
    });
  }

  $scope.older = function(){
    $scope.pagina = $scope.pagina - 1;
    Empresa.query({pagina: $scope.pagina}, $scope.empresa, function(data){
      $scope.empresas = data;
    });
  }

  $scope.edit = function(empresa){
    $location.path('/empresas/'+empresa.id);
  }
 
  $scope.clear = function(){
    $scope.endereco = null;
    $scope.endereco = createAddress.desformateEndereco($scope.endereco);
    $scope.endereco = {};
    $scope.getPaises();
    $scope.empresa = {};
    $location.path('/empresas');
  }

});
