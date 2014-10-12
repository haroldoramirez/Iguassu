'use strict';

angular.module('iguassuApp')
  .controller('CandidatoCtrl', function ($log, $modal, $scope, $routeParams, $location, Candidato, Pais) {
   

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
    $scope.getCandidato();
   };


  $scope.save = function(){
    Candidato.save($scope.candidato, function(data){
      $scope.candidato = data;
    });
  };    

  $scope.delete = function(){
    
  };

  $scope.getCandidato = function(){
    $scope.candidatos = Candidato.getAll();
  };

  $scope.editCandidato = function(candidato){
    $location.path('/candidatos/'+candidato.id);
  }

  $scope.limpar = function(){
    $scope.candidato = null;
  }

  $scope.openExperiencia = function(size) {
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

   //Aux
   $scope.getPais = function(){
    $scope.paises = Pais.getAll();
   };

});
