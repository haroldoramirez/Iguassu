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
  	$scope.cidade = bundle.cidade;
  	$scope.estado = bundle.estado;
     	
    $scope.edit = function(cidade){
			$scope.cidade = cidade;
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.cidade);
	  };

	  $scope.delete = function(id){
	    Cidade.delete({id:id}, function(data){
	    	$scope.getCidades($scope.estado.id);
	     	toastr.success('Cidade removida com sucesso');
	     	$scope.cidade = null;
	    }, function(error){
	     	toastr.error('Erro ao remover cidade');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.estado.id){
	      msg = 'atualizado com sucesso';
	    }
	    $scope.cidade.estado = $scope.estado;
	    Cidade.save($scope.cidade, function(data){
	      $scope.getCidades($scope.estado.id);
	      toastr.success(msg,$scope.cidade.nome);
	      $scope.cidade = null;
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
