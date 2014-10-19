'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:EstadoCtrl
 * @description
 * # EstadoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('EstadoCtrl', function ($scope, $modalInstance, toastr, bundle, Estado) {
   
	  $scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.delete = function(){
	    Estado.delete({id:$scope.endereco.estado.id}, function(data){
	    	$scope.getEstados($scope.endereco.pais.id);
	    	toastr.success('Estado removido com sucesso');
	    	$scope.endereco.estado = null;
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover estado');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.endereco.estado.id){
	      msg = 'atualizado com sucesso';
	    }
	    $scope.endereco.estado.pais = $scope.endereco.pais;
	    Estado.save($scope.endereco.estado, function(data){
	      $scope.getEstados($scope.endereco.pais.id);
	      toastr.success(msg,$scope.endereco.estado.nome);
	      $scope.endereco.estado = null;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };	

  });

