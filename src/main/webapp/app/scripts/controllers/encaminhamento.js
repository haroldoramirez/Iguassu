'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:EncaminhamentoCtrl
 * @description
 * # EncaminhamentoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('EncaminhamentoCtrl', function ($scope, $rootScope, $routeParams, $location, $document, Encaminhamento, Vaga, Candidato, toastr) {
    
    $scope.oldStatus = null;

    var oldDate = null;

    $scope.pagina = 0;

    $scope.init = function(){
	    if ($routeParams.id) {
	      Encaminhamento.get({id: $routeParams.id}, function(data){
	      	$scope.oldStatus = data.situacao;
	      	if (data.lancamento) {
	      		oldDate = data.lancamento.dataDePagamento;	
	      	};
	        $scope.encaminhamento = data;
	        $scope.valor = $scope.encaminhamento.vaga.salario / 3;
	        Encaminhamento.getContrato({id: $routeParams.id}, function(data){
		        $scope.contrato = '/Iguassu' + data[0];
		      });
	      });
	      $rootScope.openAll();
	    }else{
	      $scope.clear(); 
	    };
	    Encaminhamento.query({pagina: $scope.pagina},{},function(data){
	      $scope.encaminhamentos = data;
	    });
	    $scope.getCandidatos();
	    $scope.getVagas();
	  };

	  $scope.getContrato = function(){
		  $location.path('/encaminhamentos/'+$routeParams.id+'/contrato');
		};

	  $scope.save = function(){
	    var msg = 'Encaminhamento cadastrado com sucesso';
	    if($scope.encaminhamento.id){
	      msg = 'Encaminhamento atualizado com sucesso';
	    }
	    $scope.encaminhamento.usuario = null;
	    if ($scope.encaminhamento.lancamento) {
		    if ($scope.encaminhamento.lancamento.usuario) {
					$scope.encaminhamento.lancamento.usuario = null;
		    };
	    };
	    
	    if ($scope.encaminhamento.lancamento) {
	    	if (!$scope.encaminhamento.lancamento.dataDePagamento) {
		    	$scope.encaminhamento.lancamento.dataDePagamento = oldDate;	
		    };	
	    };
	    
	    Encaminhamento.save($scope.encaminhamento, function(data){
	    	$scope.encaminhamento = data;
	      $location.path('/encaminhamentos/'+$scope.encaminhamento.id);
	      $scope.getEncaminhamentos();
	      toastr.success(msg,$scope.encaminhamento.nome);
	      $document.scrollTopAnimated(0, 700);
	      $scope.init();
	    }, function(){
	    	toastr.error('Verifique se ' + $scope.encaminhamento.candidato.nome + ' já foi encaminhado (a) para essa vaga','Erro ao efetuar encaminhamento');
	    });
	  };   

	  $scope.search = function(){
	    Encaminhamento.query({pagina: $scope.pagina}, $scope.encaminhamento, function(data){
	      $scope.encaminhamentos = data;
	    });
	  };

	  $scope.next = function(){
	    $scope.pagina = $scope.pagina + 1;
	    Encaminhamento.query({pagina: $scope.pagina}, $scope.encaminhamento, function(data){
	      if (data.length===0) {
	        $scope.pagina = $scope.pagina - 1;
	      }else{
	        $scope.encaminhamentos = data;
	      };
	    });
	  }

	  $scope.older = function(){
	    $scope.pagina = $scope.pagina - 1;
	    Encaminhamento.query({pagina: $scope.pagina}, $scope.encaminhamento, function(data){
	      $scope.encaminhamentos = data;
	    });
	  } 

	  $scope.edit = function(encaminhamento){
	    $document.scrollTopAnimated(0, 700);
	    $location.path('/encaminhamentos/'+encaminhamento.id);
	  }

	  $scope.openDatePicker = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened = !$scope.opened;
	  };

	  $scope.clear = function(){
	    $scope.encaminhamento = {};
	    $document.scrollTopAnimated(0, 700);
	    if ($routeParams.id) {
	      $location.path('/encaminhamentos');
	    };
	  }
	});