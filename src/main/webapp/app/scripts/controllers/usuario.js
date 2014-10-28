'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:UsuarioCtrl
 * @description
 * # UsuarioCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('UsuarioCtrl', function ($scope, $modal, Usuario) {
    
    $scope.init = function(){
    	Usuario.getAll(function(data){
				$scope.usuarios = data;
			});
    };
    
		$scope.openUsuario = function(usuario) {
    
	    $modal.open({
	      templateUrl : 'usuario.html',
	      controller : 'UsuarioModalCtrl',
	      size : 'lg',
	      resolve : {
	       bundle : function() {
	          return {
	  					usuario : usuario            
	          }
	        }
	      }
	    }).result.then(function() {
	  			$scope.init();
	      }, function(){
	        $scope.init();
	    });
	  };
  }).controller('UsuarioModalCtrl', function ($scope, $rootScope, $modalInstance, bundle, $modal, Usuario, toastr) {
        
    $scope.usuario = bundle.usuario;

		$scope.validaRemove = function(){
			return $rootScope.usuario.id===$scope.usuario.id;
		};

    $scope.validaSenha = function(){
    	if (!$scope.usuario) {
    		return false;
    	};
    	if (!$scope.usuario.senha) {
    		return false;
    	};
    	if ($scope.usuario.senha === '') {
    		return false;
    	};
    	if ($scope.usuario.senha === null) {
    		return false;
    	};
    	return true;
    };

		$scope.close = function() {
	    $modalInstance.close();
	  };

	  $scope.save = function(){
	  	if (!$scope.usuario.id) {
				Usuario.save($scope.usuario, function(data){
		      toastr.success('Informações salvas com sucesso');
		      $scope.usuario.senha = null;
		      $scope.close();
				}, function(){
					toastr.error('Erro ao atualizar informaçẽos');
				});
	  	}else{
	  		$scope.usuario.authorities = null;
	  		Usuario.update({id:$scope.usuario.id}, $scope.usuario, function(data){
		      toastr.success('Informações salvas com sucesso');
		      $scope.usuario.senha = null;
		      $scope.close();
		    }, function(error){
		    	toastr.error('Erro ao atualizar informaçẽos');
		    });
	  	};
	    
	  };

	  $scope.delete = function(){
	    Usuario.delete({id:$scope.usuario.id}, function(data){
	     toastr.success('Usuario removido com sucesso');
	     $scope.close();
	    }, function(error){
	     toastr.error('Erro ao remover usuario');
	    });
	  };  

	  $scope.openDatePicker = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    $scope.opened = !$scope.opened;
	  };

  });
