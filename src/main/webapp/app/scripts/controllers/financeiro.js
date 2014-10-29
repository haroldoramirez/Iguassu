'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FinanceiroCtrl
 * @description
 * # FinanceiroCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('FinanceiroCtrl', function ($scope, Financeiro, $modal) {
    $scope.init = function(){
    	Financeiro.getAll(function(data){
				$scope.lancamentos = data;
			});
    };
    
		$scope.openLancamento = function(lancamento) {
    
	    $modal.open({
	      templateUrl : 'lancamento.html',
	      controller : 'FinanceiroModalCtrl',
	      size : 'lg',
	      resolve : {
	       bundle : function() {
	          return {
	  					lancamento : lancamento            
	          }
	        }
	      }
	    }).result.then(function() {
	  			$scope.init();
	      }, function(){
	        $scope.init();
	    });
	  };
  }).controller('FinanceiroModalCtrl', function ($scope, $rootScope, $modalInstance, bundle, $modal, Financeiro, toastr) {
        
    $scope.lancamento = bundle.lancamento;

		$scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.save = function(){
	  	if (!$scope.lancamento.id) {
				Financeiro.save($scope.lancamento, function(data){
		      toastr.success('Informações salvas com sucesso');
		      $scope.close();
				}, function(){
					toastr.error('Erro ao atualizar informaçẽos');
				});
	  	}else{
	  		$scope.lancamento.authorities = null;
	  		Financeiro.update({id:$scope.lancamento.id}, $scope.lancamento, function(data){
		      toastr.success('Informações salvas com sucesso');
		      $scope.close();
		    }, function(error){
		    	toastr.error('Erro ao atualizar informaçẽos');
		    });
	  	};
	    
	  };

	  $scope.delete = function(){
	    Financeiro.delete({id:$scope.lancamento.id}, function(data){
	     toastr.success('Lancamento removido com sucesso');
	     $scope.close();
	    }, function(error){
	     toastr.error('Erro ao remover lancamento');
	    });
	  };  

	  $scope.openDatePicker = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened = !$scope.opened;
	  };
    
  });
