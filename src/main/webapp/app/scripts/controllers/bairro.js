'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:BairroCtrl
 * @description
 * # BairroCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('BairroCtrl', function ($scope, $modalInstance, bundle, Bairro, toastr) {
  	
		$scope.bairro = bundle.bairro;
		$scope.cidade = bundle.cidade;
		
		$scope.close = function() {
	    $modalInstance.close($scope.bairro);
	  };

	  $scope.cancel = function() {
	    $modalInstance.dismiss();
	  };

	  $scope.delete = function(){
	    Bairro.delete({id:$scope.bairro.id}, function(data){
	    	$scope.getBairros($scope.bairro.cidade.id);
	    	toastr.success('Bairro removido com sucesso');
	    	$scope.bairro.nome = null;
	     	$scope.bairro.id = null;
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover bairro');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.bairro.id){
	      msg = 'atualizado com sucesso';
	    }
	    $scope.bairro.cidade = $scope.cidade;
	    Bairro.save($scope.bairro, function(data){
	    	$scope.getBairros(data.cidade.id);
	      toastr.success(msg,data.nome);
	      $scope.bairro.nome = data.nome;
	      $scope.bairro.id = data.id;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
