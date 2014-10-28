'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:ConfiguracoesCtrl
 * @description
 * # ConfiguracoesCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('ConfiguracoesCtrl', function ($scope, $rootScope, Usuario, toastr) {
    
		$scope.init = function(){
			Usuario.get({id:$rootScope.usuario.id}, function(data){
				$rootScope.usuario = data;
			});
		};

    $scope.validaSenha = function(){
    	if (!$rootScope.usuario) {
    		return false;
    	};
    	if (!$rootScope.usuario.senha) {
    		return false;
    	};
    	if ($rootScope.usuario.senha === '') {
    		return false;
    	};
    	if ($rootScope.usuario.senha === null) {
    		return false;
    	};
    	return true;
    };

	  $scope.save = function(){
	  	$rootScope.usuario.authorities = null;
	    Usuario.update({id:$rootScope.usuario.id}, $rootScope.usuario, function(data){
	      $rootScope.usuario = data;
	      toastr.success('Informações salvas com sucesso');
	      $scope.confirmacaoSenha = null;
	    }, function(error){
	    	toastr.error('Erro ao atualizar informaçẽos');
	    });
	  };    

	  $scope.openDatePicker = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened = !$scope.opened;
	  };

  });
