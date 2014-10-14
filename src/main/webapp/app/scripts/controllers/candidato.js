'use strict';

angular.module('iguassuApp')
  .controller('CandidatoCtrl', function ($log, $modal, $scope, $routeParams, $document, $location, Candidato, Pais, toastr) {
   

   $scope.candidato = {};

   $scope.init = function(){
    if ($routeParams.id) {
      Candidato.get({id: $routeParams.id}, function(data){
        $scope.candidato = data;
        Candidato.getCursos({id: $routeParams.id}, function(data){
          $scope.cursosDoCandidato = data;
        });
        Candidato.getExperiencias({id: $routeParams.id}, function(data){
          $scope.experiencias = data;
        });
      }); 
    };
    $scope.getPais();
    $scope.getCandidatos();
   };



  $scope.save = function(){
    var msg = 'Cadastrado com sucesso';
    if($scope.candidato.id){
      msg = 'Atualizado com sucesso';
    }
    Candidato.save($scope.candidato, function(data){
      $scope.candidato = data;
      $location.path('/candidatos/'+$scope.candidato.id);
      $scope.getCandidatos();
      toastr.success(msg,$scope.candidato.nome);
      $document.scrollTopAnimated(0, 700);
    });

  };    


  $scope.getCandidatos = function(){
    $scope.candidatos = Candidato.getAll();
    //$document.scrollTopAnimated(0, 700); Fazer ir direto para a tebela
  };

  $scope.editCandidato = function(candidato){
    $document.scrollTopAnimated(0, 700);
    $location.path('/candidatos/'+candidato.id);
  }

  $scope.limpar = function(){
    $document.scrollTopAnimated(0, 700);
    $scope.candidato = {};
    $location.path('/candidatos');
  }

  $scope.openDatePicker = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.opened = !$scope.opened;
  };

   //Aux
  $scope.getPais = function(){
     $scope.paises = Pais.getAll();
  };

  $scope.openCursos = function(size) {
    var modalInstance = $modal.open({
      templateUrl : 'candidatoCurso.html',
      controller : 'CandidatoCursoCtrl',
      size : 'lg',
      resolve : {
        bundle : function() {
          return {
            candidato : $scope.candidato,
            cursos : $scope.cursos
          }
        }
      }
    }).result.then(function(cursos) {
      $scope.cursos = cursos;
    }, function() {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $scope.openExperiencias = function(size) {
    var modalInstance = $modal.open({
      templateUrl : 'experiencia.html',
      controller : 'ExperienciaCtrl',
      size : 'lg',
      resolve : {
        bundle : function() {
          return {
            candidato : $scope.candidato,
            experiencias : $scope.experiencias
          }
        }
      }
    }).result.then(function(experiencias) {
      $scope.experiencias = experiencias;
    }, function() {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

});
