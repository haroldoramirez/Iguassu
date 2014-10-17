'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:CargoCtrl
 * @description
 * # CargoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('CargoCtrl', function ($scope, $modalInstance, Cargo, toastr) {
  	
	$scope.save = function(){
    var msg = 'Cargo cadastrado com sucesso';
    if($scope.cargo.id) {msg = 'Cargo atualizado com sucesso';}
    
    Cargo.save($scope.cargo, function(data){
      $scope.getCargos();
     	toastr.success(msg);
     	$scope.clear();
    }, function(error){
    	toastr.error('Erro ao salvar cargo');
    	$scope.clear();
    });
  };

  $scope.edit = function(cargo){
    $scope.cargo = cargo;
  };

  $scope.clear = function(){
    $scope.cargo = {};
  };

  $scope.close = function() {
    $modalInstance.close();
  };
    
});
