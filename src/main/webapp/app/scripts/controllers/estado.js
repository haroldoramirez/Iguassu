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
    $scope.pais = bundle.pais;
    $scope.estado = bundle.estado;

	  $scope.edit = function(estado){
			$scope.estado = estado;
	  };

	  $scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.delete = function(){
	    Estado.delete({id:$scope.estado.id}, function(data){
	    	$scope.getEstados($scope.pais.id);
	     	toastr.success('Estado removido com sucesso');
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover estado');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.estado.id){
	      msg = 'atualizado com sucesso';
	    }
	    $scope.estado.pais = $scope.pais;
	    Estado.save($scope.estado, function(data){
	      $scope.getEstados($scope.pais.id);
	      toastr.success(msg,$scope.estado.nome);
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };	

  });

