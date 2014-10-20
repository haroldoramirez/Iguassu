'use strict';

angular.module('iguassuApp')
  .controller('EnderecoCtrl', function ($scope, $rootScope, $modal, createAddress) {
  
		$rootScope.openPais = function(pais) {
			
	    $modal.open({
	      templateUrl : 'pais.html',
	      controller : 'PaisCtrl',
	      size : 'md',
	      resolve : {
	        bundle : function(){
	          return {
	            pais : pais
	          }
	        }
	      }
	    }).result.then(function(pais) {

	    	$rootScope.getPaises();
	    }, function(){

	    	$rootScope.getPaises();
	    });
	  };	

	  $rootScope.openEstado = function(pais,estado) {

	    $modal.open({
	      templateUrl : 'estado.html',
	      controller : 'EstadoCtrl',
	      size : 'md',
	      resolve : {
	        bundle : function(){
	          return {
	            pais : pais,
	            estado : estado
	          }
	        }
	      }
	    }).result.then(function(estado) {

	      $rootScope.getEstados(pais.id);
	    }, function(){

	      $rootScope.getEstados(pais.id);
	    });
	  };

	  $rootScope.openCidade = function(estado,cidade) {

	    $modal.open({
	      templateUrl : 'cidade.html',
	      controller : 'CidadeCtrl',
	      size : 'md',
	      resolve : {
	        bundle : function(){
	          return {
	            estado : estado,
	            cidade : cidade
	          }
	        }
	      }
	    }).result.then(function(cidade) {

	      $rootScope.getCidades(estado.id);
	    }, function(){
	    	
	      $rootScope.getCidades(estado.id);
	    });
	  };

	  $rootScope.openBairro = function(cidade, bairro) {

	    $modal.open({
	      templateUrl : 'bairro.html',
	      controller : 'BairroCtrl',
	      size : 'md',
	      resolve : {
	        bundle : function(){
	          return {
	            cidade : cidade,
	            bairro : bairro
	          }
	        }
	      }
	    }).result.then(function(bairro) {

	      $rootScope.getBairros(cidade.id);
	    }, function(){

	      $rootScope.getBairros(cidade.id);
	    });
	  };
	  
	});