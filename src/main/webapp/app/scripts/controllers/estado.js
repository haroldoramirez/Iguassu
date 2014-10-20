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
   
	  $scope.estado = bundle.estado;
  	$scope.pais = bundle.pais;
     	
	  $scope.cancel = function() {
	    $modalInstance.dismiss();
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.estado);
	  };

	  $scope.delete = function(){
	    Estado.delete({id:$scope.estado.id}, function(data){
	    	$scope.getEstados($scope.pais.id);
	    	toastr.success('Estado removido com sucesso');
	    	$scope.estado.nome = null;
	      $scope.estado.sigla = null;
	      $scope.estado.id = null;
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
	      toastr.success(msg, data.nome);
	      $scope.estado.nome = data.nome;
	      $scope.estado.sigla = data.sigla;
	      $scope.estado.id = data.id;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };	

  });

