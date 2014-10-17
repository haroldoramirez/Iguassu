'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:CursoCtrl
 * @description
 * # CursoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('CursoCtrl', function ($scope, $modalInstance, Curso, toastr) {
  	
  $scope.save = function(){
    var msg = 'Curso cadastrado com sucesso';
    if($scope.curso.id) {msg = 'Curso atualizado com sucesso';}
    
    Curso.save($scope.curso, function(data){
     	$scope.getCursos();
     	toastr.success(msg);
     	$scope.clear();
    }, function(error){
    	toastr.error('Erro ao salvar curso');
    	$scope.clear();
    });
  };

  $scope.edit = function(curso){
    $scope.curso = curso;
  };

  $scope.clear = function(){
    $scope.curso = {};
  };

  $scope.close = function() {
    $modalInstance.close();
  };
    
});