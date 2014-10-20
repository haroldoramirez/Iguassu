'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:EnderecoCtrl
 * @description
 * # EnderecoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('EnderecoCtrl', function ($scope, $rootScope, $modal, createAddress) {
  

		

		$rootScope.endereco = {
			bairro : {
				cidade : {
					estado : {
						pais : {

						}
					}
				}
			}
		}

		
		createAddress.formateEndereco($rootScope.endereco);
		createAddress.desformateEndereco($rootScope.endereco);
		
		// $rootScope.loadPaises = function(pais){
		// 	$rootScope.getPaises();
		// 	$rootScope.endereco = {
		// 		bairro : {
		// 			cidade : {
		// 				estado : {
		// 					pais : pais	
		// 				}
		// 			}
		// 		}
		// 	}
		// };

		$rootScope.loadEstados = function(pais){
			if (pais.id) {
				$rootScope.getEstados(pais.id);
			};
		};

		$rootScope.loadCidades = function(estado){
			$rootScope.getCidades(estado.id);
		};

		$rootScope.loadBairros = function(cidade){
			$rootScope.getBairros(cidade.id);
		};

		$rootScope.openPais = function(pais) {
			var auxPais = null;
			if (pais) {
				auxPais = {nome: pais.nome, id : pais.id};
			};
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
	    	if (pais&&auxPais) { //se editou
					$rootScope.endereco.bairro.cidade.estado.pais.nome = pais.nome;
					$rootScope.endereco.bairro.cidade.estado.pais.id = pais.id;
				}else if (!pais&&auxPais) { // se deletou
		    	$rootScope.endereco.bairro.cidade.estado.pais.nome = null;
		    	$rootScope.endereco.bairro.cidade.estado.pais.id = null;
		   } else if (pais&&!auxPais){ //se salvou novo
					$rootScope.endereco.bairro.cidade.estado.pais.nome = pais.nome;
					$rootScope.endereco.bairro.cidade.estado.pais.id = pais.id;
					$rootScope.endereco.bairro.cidade.estado.nome = null;
					$rootScope.endereco.bairro.cidade.estado.sigla = null;
					$rootScope.endereco.bairro.cidade.estado.id = null;
					$rootScope.getEstados(pais.id);
	    	};
	    	$rootScope.getPaises();
	    }, function(){
	    	if (auxPais) {
	    		$rootScope.endereco.bairro.cidade.estado.pais.nome = auxPais.nome;
					$rootScope.endereco.bairro.cidade.estado.pais.id = auxPais.id;
	    	};				
	    });
	  };	

	  $rootScope.openBairro = function(cidade, bairro) {
	    var oldNome = $rootScope.endereco.bairro.nome;
	    var oldId = $rootScope.endereco.bairro.id;
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
	      $rootScope.endereco.bairro.nome = bairro.nome;
	      $rootScope.endereco.bairro.id = bairro.id;
	      $rootScope.getBairros(cidade.id);
	    }, function(){
	      $rootScope.endereco.bairro.nome = oldNome;
	      $rootScope.endereco.bairro.id = oldId;
	      $rootScope.getBairros(cidade.id);
	    });
	  };

	  $rootScope.openCidade = function(estado,cidade) {
	    var oldNome = $rootScope.endereco.bairro.cidade.nome;
	    var oldId = $rootScope.endereco.bairro.cidade.id;
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
	      $rootScope.endereco.bairro.cidade.nome = cidade.nome;
	      $rootScope.endereco.bairro.cidade.id = cidade.id;
	      $rootScope.getCidades(estado.id);
	      $rootScope.getBairros(cidade.id);  
	    }, function(){
	      $rootScope.endereco.bairro.cidade.nome = oldNome;
	      $rootScope.endereco.bairro.cidade.id = oldId;
	      $rootScope.getCidades(estado.id);
	    });
	  };

	  $rootScope.openEstado = function(pais,estado) {
	    var oldSigla = $rootScope.endereco.bairro.cidade.estado.sigla;
	    var oldNome = $rootScope.endereco.bairro.cidade.estado.nome;
	    var oldId = $rootScope.endereco.bairro.cidade.estado.id;
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
	      $rootScope.endereco.bairro.cidade.estado.sigla = estado.sigla;
	      $rootScope.endereco.bairro.cidade.estado.nome = estado.nome;
	      $rootScope.endereco.bairro.cidade.estado.id = estado.id;
	      $rootScope.getEstados(pais.id);
	      $rootScope.getCidades(estado.id);  
	    }, function(){
	      $rootScope.endereco.bairro.cidade.estado.sigla = oldSigla;
	      $rootScope.endereco.bairro.cidade.estado.nome = oldNome;
	      $rootScope.endereco.bairro.cidade.estado.id = oldId;
	      $rootScope.getEstados(pais.id);
	    });
	  };

	  
	});
