'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:CategoriacursoCtrl
 * @description
 * # CategoriacursoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('CategoriaCursoCtrl', function ($scope, $modalInstance, CategoriasCursos, toastr) {
  	
	$scope.save = function(){
    var msg = 'Categoria de curso cadastrada com sucesso';
    
    if($scope.categoria.id) {msg = 'Categoria de curso atualizada com sucesso'; var b = true;}
    
    CategoriasCursos.save($scope.categoria, function(data){
     	if(!b){
        $scope.categoriasDeCursos.push(data);
     	}
     	toastr.success(msg);
     	$scope.clear();
    }, function(error){
    	toastr.error(error, 'ERRO AO SALVAR EXPERIENCIA: ');
    	$scope.clear();
    });
  };

  $scope.edit = function(categoria){
    $scope.categoria = categoria;
  };

  $scope.clear = function(){
    $scope.categoria = {};
  };

  $scope.close = function() {
    $modalInstance.close();
  };
    
});