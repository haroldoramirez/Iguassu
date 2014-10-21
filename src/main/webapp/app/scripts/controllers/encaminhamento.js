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
    
    
    $scope.init = function(){
	    if ($routeParams.id) {
	      Encaminhamento.get({id: $routeParams.id}, function(data){
	        $scope.encaminhamento = data;
	      });
	      $rootScope.openAll();
	    }else{
	      $scope.clear(); 
	    };
	    if(!$scope.encaminhamento){
	      $scope.getEncaminhamentos();
	    };
	    $scope.getCandidatos();
	    $scope.getVagas();
	  };

	  $scope.save = function(){
	    var msg = 'Encaminhamento cadastrado com sucesso';
	    if($scope.encaminhamento.id){
	      msg = 'Encaminhamento atualizado com sucesso';
	    }
	    console.log($scope.encaminhamento);
	    Encaminhamento.save($scope.encaminhamento, function(data){
	      $scope.encaminhamento = data;
	      $location.path('/encaminhamentos/'+$scope.encaminhamento.id);
	      $scope.getEncaminhamentos();
	      toastr.success(msg,$scope.encaminhamento.nome);
	      $document.scrollTopAnimated(0, 700);
	      $scope.init();
	    });
	  };    

	  $scope.edit = function(encaminhamento){
	    $document.scrollTopAnimated(0, 700);
	    $location.path('/encaminhamentos/'+encaminhamento.id);
	  }

	  $scope.clear = function(){
	    $scope.encaminhamento = {};
	    $document.scrollTopAnimated(0, 700);
	    if ($routeParams.id) {
	      $location.path('/encaminhamentos');
	    };
	  }
	});