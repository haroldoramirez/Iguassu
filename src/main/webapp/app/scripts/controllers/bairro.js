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
  	$scope.endereco.bairro = bundle.bairro;
  	$scope.endereco.cidade = bundle.cidade;
     	
    $scope.edit = function(bairro){
			$scope.endereco.bairro = bairro;
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.endereco.bairro);
	  };

	  $scope.delete = function(){
	    Bairro.delete({id:$scope.endereco.bairro.id}, function(data){
	    	$scope.getBairros($scope.endereco.cidade.id);
	    	toastr.success('Bairro removido com sucesso');
	    	$scope.endereco.bairro = null;
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
	    $scope.endereco.bairro.cidade = $scope.endereco.cidade;
	    Bairro.save($scope.endereco.bairro, function(data){
	    	$scope.getBairros($scope.endereco.cidade.id);
	      toastr.success(msg,$scope.endereco.bairro.nome);
	      $scope.endereco.bairro = null;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
