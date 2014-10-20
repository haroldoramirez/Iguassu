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
     	
	  $scope.cancel = function() {
	    $modalInstance.dismiss();
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.cidade);
	  };

	  $scope.delete = function(){
	    Cidade.delete({id:$scope.cidade.id}, function(data){
	    	$scope.getCidades($scope.estado.id);
	     	toastr.success('Cidade removida com sucesso');
	     	$scope.cidade.nome = null;
	     	$scope.cidade.id = null;
	     	$scope.close();
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
	      $scope.cidade.nome = data.nome;
	      $scope.cidade.id = data.id;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };
  });
