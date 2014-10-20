'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:PaisCtrl
 * @description
 * # PaisCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('PaisCtrl', function ($scope, bundle, $modalInstance, Pais, toastr) {
    
		$scope.pais = bundle.pais;
				
		$scope.cancel = function() {
	    $modalInstance.dismiss();
	  };

	  $scope.close = function() {
	    $modalInstance.close($scope.pais);
	  };

	  $scope.delete = function(){
	    Pais.delete({id:$scope.pais.id}, function(data){
	     	toastr.success('Pais removido com sucesso');
	     	$scope.pais.nome = null;
     		$scope.pais.id = null;
	     	$scope.close();
	    }, function(error){
	     	toastr.error('Erro ao remover pais');
	    });
	  };

		$scope.save = function(){
	    var msg = 'cadastrado com sucesso';
	    if($scope.pais.id){
	      msg = 'atualizado com sucesso';
	    }
	    Pais.save($scope.pais, function(data){
	      $scope.getPaises();
	      toastr.success(msg,$scope.pais.nome);
	      $scope.pais.nome = data.nome;
	      $scope.pais.id = data.id;
	      $scope.close();
	    }, function(data){
	    	toastr.error('Não foi possível salvar essas informações');
	    });
	  };	

  });
