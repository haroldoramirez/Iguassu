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

  $scope.order = 'dataDeCadastro';

   $scope.init = function(){
    if ($routeParams.id) {
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresa = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
        console.log($scope.endereco);
        $rootScope.openAll();
      });      
    }
    $scope.getEmpresas();
   };

  $scope.save = function(){
    $scope.empresa.endereco = createAddress.formateEndereco($scope.endereco);
    console.log($scope.empresa);
    Empresa.save($scope.empresa, function(data){
      toastr.success("Salvo com sucesso");
      $scope.clear();
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
      Empresa.query({nome: $scope.empresa.nome, CNPJ: $scope.empresa.cnpj, 
                     order: $scope.order, pagina: $scope.pagina, paginas:20}, function(data){
        $scope.empresas = data;
      });
    };
  };


  $scope.edit = function(empresa){
    $location.path('/empresas/'+empresa.id);
  }

  $scope.next = function(){
    $scope.pagina = $scope.pagina + 1;
    Empresa.query({nome: $scope.empresa.nome, CNPJ: $scope.empresa.cnpj, 
      order: $scope.order, pagina: $scope.pagina, paginas:20}, function(data){
      if (data.length===0) {
        $scope.pagina = $scope.pagina - 1;
      }else{
        $scope.empresas = data;
      };
    });
  }

  $scope.older = function(){
    $scope.pagina = $scope.pagina - 1;
    Empresa.query({nome: $scope.empresa.nome, CNPJ: $scope.empresa.cnpj, 
      order: $scope.order, pagina: $scope.pagina, paginas:20}, function(data){
      $scope.empresas = data;
    });
  }

  $scope.clear = function(){
    // $scope.endereco.pais = null;
    // $scope.endereco.estado = null;
    // $scope.endereco.cidade = null;
    // $scope.endereco.bairro = null;
    // $scope.endereco = null;
$scope.endereco = {};
    $scope.getPaises();
    $scope.empresa = {};
    // $scope.empresa.id = null;
    if ($routeParams.id) {
      $location.path('/empresas');
    };
  }

});
