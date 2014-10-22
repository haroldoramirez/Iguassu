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

   $scope.init = function(){
    if ($routeParams.id) {
      Empresa.get({id: $routeParams.id}, function(data){
        $scope.empresa = data;
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
      });
      $rootScope.openAll();
    }else{
      $scope.clear(); 
    };
    $scope.getEmpresas();
   };

  $scope.save = function(){
    $scope.empresa.endereco = $scope.endereco;
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

  $scope.edit = function(empresa){
    $document.scrollTopAnimated(0, 700);
    $location.path('/empresas/'+empresa.id);
  }

  $scope.clear = function(){
    $rootScope.endereco = {};
    $scope.empresa = {};
    $document.scrollTopAnimated(0, 700);
    if ($routeParams.id) {
      $location.path('/empresas');
    };
  }


});
