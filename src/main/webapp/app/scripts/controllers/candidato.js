'use strict';

angular.module('iguassuApp')
  .controller('CandidatoCtrl', function ($log, $modal, $scope, $routeParams, $location, Candidato, Pais, toastr) {
   

   $scope.candidato = {};

   $scope.pne = false;

   $scope.init = function(){

    if ($routeParams.id) {
      Candidato.get({id: $routeParams.id}, function(data){
        $scope.candidato = data;
        if($scope.candidato.necessidadeEspecial){
            $scope.pne = true;
        }
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
    if(!$scope.pne||$scope.pne===false){
       $scope.candidato.necessidadeEspecial = null;
    }
    Candidato.save($scope.candidato, function(data){
      $scope.candidato = data;
      $location.path('/candidatos/'+$scope.candidato.id);
      $scope.getCandidatos();
      toastr.success(msg,$scope.candidato.nome);
    });

  };    


  $scope.getCandidatos = function(){
    $scope.candidatos = Candidato.getAll();
  };

  $scope.editCandidato = function(candidato){
    $location.path('/candidatos/'+candidato.id);
  }

  $scope.limpar = function(){
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

});
