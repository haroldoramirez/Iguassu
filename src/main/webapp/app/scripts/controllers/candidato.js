'use strict';

angular.module('iguassuApp')
  .controller('CandidatoCtrl', function ($rootScope, $log, $modal, $scope, $routeParams, $document, $location, Candidato, Pais, toastr) {


   $scope.init = function(){
    if ($routeParams.id) {
      $rootScope.candidato = Candidato.get({id: $routeParams.id});
      $rootScope.cursosDoCandidato = Candidato.getCursos({id: $routeParams.id});
      $rootScope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
    };
    $scope.getPaises();
    $scope.getCandidatos();
   };


  $scope.save = function(){
    var msg = 'cadastrado com sucesso';
    if($scope.candidato.id){
      msg = 'atualizado com sucesso';
    }
    Candidato.save($scope.candidato, function(data){
      $rootScope.candidato = data;
      $location.path('/candidatos/'+$scope.candidato.id);
      $scope.getCandidatos();
      toastr.success(msg,$scope.candidato.nome);
      $document.scrollTopAnimated(0, 700);
    });
  };    

  $scope.openCursosDoCandidato = function(size) {
    var modalInstance = $modal.open({
      templateUrl : 'candidatoCurso.html',
      controller : 'CandidatoCursoCtrl',
      size : 'lg'
    }).result.then(function(/*cursos*/) {
      /*$scope.cursos = cursos;*/
    });
  };

  $scope.openExperienciasDoCandidato = function(size) {
    $rootScope.getEmpresas();
    $rootScope.getCargos();
    var modalInstance = $modal.open({
          templateUrl : 'experienciasDoCandidato.html',
          controller : 'CandidatoExperienciaCtrl',
          size : 'lg'
        }).result.then(function(/*experiencias*/) {
          /*$scope.experienciasDoCandidato = experienciasDoCandidato;*/
      });
    };

  $scope.editCandidato = function(candidato){
    $document.scrollTopAnimated(0, 700);
    $location.path('/candidatos/'+candidato.id);
  }

  $scope.clear = function(){
    $document.scrollTopAnimated(0, 700);
    $rootScope.candidato = {};
    $location.path('/candidatos');
  }

  $scope.openDatePicker = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.opened = !$scope.opened;
  };



});
