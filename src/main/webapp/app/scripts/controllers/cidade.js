'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:CidadeCtrl
 * @description
 * # CidadeCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('CidadeCtrl', function ($scope, $modalInstance, bundle, Cidade, toastr) {
  	$scope.endereco.cidade = bundle.cidade;
  	$scope.endereco.estado = bundle.estado;
     	
    $scope.edit = function(cidade){
			$scope.endereco.cidade = cidade;
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.endereco.cidade);
	  };

	  $scope.delete = function(){
	    Cidade.delete({id:$scope.endereco.cidade.id}, function(data){
	    	$scope.getCidades($scope.endereco.estado.id);
	     	toastr.success('Cidade removida com sucesso');
	     	$scope.endereco.cidade = null;
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover cidade');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.endereco.estado.id){
	      msg = 'atualizado com sucesso';
	    }
	    $scope.endereco.cidade.estado = $scope.endereco.estado;
	    Cidade.save($scope.endereco.cidade, function(data){
	      $scope.getCidades($scope.endereco.estado.id);
	      toastr.success(msg,$scope.endereco.cidade.nome);
	      $scope.endereco.cidade = null;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
