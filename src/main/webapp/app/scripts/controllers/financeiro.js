'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FinanceiroCtrl
 * @description
 * # FinanceiroCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('FinanceiroCtrl', function ($scope, Financeiro, $modal, toastr, $location) {
    
    $scope.pagina = 0;

    $scope.lancamento = {pessoa:{}};

    $scope.init = function(){
    	$scope.data = new Date();
    	$scope.dataDeVencimento = null;
    	$scope.dataDePagamento = null;
    	var datas = [];
    	datas[0] = $scope.data;
    	datas[1] = $scope.dataDeVencimento;
    	datas[2] = $scope.dataDePagamento;
    	Financeiro.query({pagina: 0}, datas, function(data){
  			$scope.lancamentos = data;
    	});
    };

    $scope.openDatePickerDate = function($event) {
      $event.preventDefault();
	    $event.stopPropagation();   
	    $scope.openedDate = !$scope.openedDate;
	  };

	  $scope.openDatePickerDateVencimento = function($event) {
      $event.preventDefault();
	    $event.stopPropagation();   
	    $scope.openedDateVencimento = !$scope.openedDateVencimento;
	  };

	  $scope.openDatePickerDatePagamento = function($event) {
      $event.preventDefault();
	    $event.stopPropagation();   
	    $scope.openedDatePagamento = !$scope.openedDatePagamento;
	  };

	  $scope.clear = function($event) {
      $location.path('/financeiro');
	  };

	  $scope.next = function(){
	  	$scope.pagina = $scope.pagina + 1;
	  	var datas = [];
	  	datas[0] = $scope.data;
    	datas[1] = $scope.dataDeVencimento;
    	datas[2] = $scope.dataDePagamento;
	    Financeiro.query({pagina: $scope.pagina}, datas, function(data){
  			if (data.length===0) {
	        $scope.pagina = $scope.pagina - 1;
	      }else{
	        $scope.lancamentos = data;
	      };
    	});
	  }

	  $scope.older = function(){
	  	$scope.pagina = $scope.pagina - 1;
	  	var datas = [];
	  	datas[0] = $scope.data;
    	datas[1] = $scope.dataDeVencimento;
    	datas[2] = $scope.dataDePagamento;
	    Financeiro.query({pagina: $scope.pagina}, datas, function(data){
	    	$scope.lancamentos = data;
    	});
	  }

    $scope.class = function(lancamento){
    	if ($scope.today>lancamento.dataDeVencimento&&!lancamento.dataDeVencimento) {
    		return 'label-danger';
    	};
    	if (!lancamento.dataDePagamento) {
    		return 'label-warning';
    	}else{
    		return 'label-success';
    	};
    };

    $scope.search = function(){
    	var datas = [];
    	datas[0] = $scope.data;
    	datas[1] = $scope.dataDeVencimento;
    	datas[2] = $scope.dataDePagamento;
    	Financeiro.query({pagina: 0}, datas, function(data){
  			$scope.lancamentos = data;
  			console.log(data);
    	});
    };
    
		$scope.openLancamento = function(lancamento) {
    	if (!lancamento.pessoa.genero) {
	    		$modal.open({
		      templateUrl : 'lancamento.html',
		      controller : 'FinanceiroModalCtrl',
		      size : 'md',
		      resolve : {
		       bundle : function() {
		          return {
		  					lancamento : lancamento            
		          }
		        }
		      }
		    }).result.then(function() {
		  			var datas = [];
			    	datas[0] = $scope.data;
			    	datas[1] = $scope.dataDeVencimento;
			    	datas[2] = $scope.dataDePagamento;
			    	Financeiro.query({pagina: 0}, datas, function(data){
			  			$scope.lancamentos = data;
			  			console.log(data);
			    	});
		      }, function(){
		        var datas = [];
			    	datas[0] = $scope.data;
			    	datas[1] = $scope.dataDeVencimento;
			    	datas[2] = $scope.dataDePagamento;
			    	Financeiro.query({pagina: 0}, datas, function(data){
			  			$scope.lancamentos = data;
			  			console.log(data);
			    	});
		    });
		  }else{
	  		toastr.error('Desbloqueie o candidato no menu \"Candidatos\", ou defina uma data de pagamento no menu \"Encaminhamento\"','Não pode editar um lançamento relacionado á um candidato');
	  	};
  	};
	    
  }).controller('FinanceiroModalCtrl', function ($scope, $rootScope, Empresa, $modalInstance, bundle, $modal, Financeiro, toastr) {
        
    $scope.lancamento = bundle.lancamento;

    Empresa.getAll(function(data){
    	$scope.empresas = data;	
    });

		$scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.save = function(){
	  	if (!$scope.lancamento.pessoa.id) {
				$scope.lancamento.pessoa = null;
	  	};
	  	if (!$scope.lancamento.id) {
				Financeiro.save($scope.lancamento, function(data){
		      toastr.success('Informações salvas com sucesso');
		      $scope.close();
				}, function(){
					toastr.error('Erro ao atualizar informaçẽos');
				});
	  	}else{
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

	 $scope.openDatePickerDateVencimento = function($event) {
      $event.preventDefault();
	    $event.stopPropagation();   
	    $scope.openedDateVencimento = !$scope.openedDateVencimento;
	  };

	  $scope.openDatePickerDatePagamento = function($event) {
      $event.preventDefault();
	    $event.stopPropagation();   
	    $scope.openedDatePagamento = !$scope.openedDatePagamento;
	  };

    
  });
