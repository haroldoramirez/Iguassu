'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:BairroCtrl
 * @description
 * # BairroCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('BairroCtrl', function ($scope, $modalInstance, Bairro, toastr) {
  	
		$scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.cancel = function() {
	    $modalInstance.dismiss();
	  };

	  $scope.delete = function(){
	    Bairro.delete({id:$scope.endereco.bairro.id}, function(data){
	    	$scope.getBairros($scope.endereco.bairro.cidade.id);
	    	toastr.success('Bairro removido com sucesso');
	    	$scope.endereco.bairro.nome = null;
	     	$scope.endereco.bairro.id = null;
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover bairro');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.endereco.bairro.id){
	      msg = 'atualizado com sucesso';
	    }
	    Bairro.save($scope.endereco.bairro, function(data){
	    	$scope.getBairros(data.cidade.id);
	      toastr.success(msg,data.nome);
	      $scope.endereco.bairro.nome = data.nome;
	      $scope.endereco.bairro.id = data.id;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
