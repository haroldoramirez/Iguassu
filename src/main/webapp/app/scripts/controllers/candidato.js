'use strict';

angular.module('iguassuApp')
  .controller('CandidatoCtrl', function ($rootScope, $log, $modal, $scope, $routeParams, $document, $location, Candidato, Pais, toastr) {


  $scope.init = function(){
    if ($routeParams.id) {
      $rootScope.candidato = Candidato.get({id: $routeParams.id});
      $scope.cursosDoCandidato = Candidato.getCursos({id: $routeParams.id});
      $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
    }else{
      $rootScope.candidato = {};
      $scope.cursosDoCandidato = {};
      $scope.experienciasDoCandidato = {};
    };
    if(!$scope.paises){
      $scope.getPaises();
    }
    if(!$scope.candidatos){
      $scope.getCandidatos();
    }
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
    $modal.open({
      templateUrl : 'candidatoCurso.html',
      controller : 'CandidatoCursoCtrl',
      size : 'lg'
    }).result.then(function(/*cursos*/) {
      /*$scope.cursos = cursos;*/
    });
  };

  $scope.openExperiencia = function(experiencia) {
    $rootScope.getEmpresas();
    $rootScope.getCargos();
    $modal.open({
        templateUrl : 'experienciasDoCandidato.html',
        controller : 'CandidatoExperienciaCtrl',
        size : 'md',
        resolve : {
         bundle : function() {
            return {
                experiencia : experiencia
            }
          }
        }
      }).result.then(function() {
        $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
    }, function(){
      $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
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



}).controller('CandidatoExperienciaCtrl', function ($scope, $rootScope, $modalInstance,  $modal, Candidato, toastr, bundle) {

  $scope.experiencia = bundle.experiencia;

  $scope.today = new Date();

  $scope.save = function(){
    var msg = 'Expereiência adicionada com sucesso';
    if($scope.experiencia.id) {msg = 'Experiência atualizada com sucesso'; var b = true;}

    $scope.experiencia.candidato = $rootScope.candidato;
    console.log($scope.experiencia);
    Candidato.saveExperiencia($scope.experiencia, function(data){
    if(!b){
     $scope.experienciasDoCandidato.push(data);
    }
    toastr.success(msg);
    $scope.close();
    }, function(error){
     toastr.error(error, 'ERRO AO SALVAR EXPERIENCIA: ');
    });
  };

  $scope.delete = function(){
    Candidato.deleteExperiencia({id:$scope.experiencia.id}, function(data){
     $scope.experienciasDoCandidato.splice($scope.experienciasDoCandidato.indexOf($scope.experiencia), 1);
     toastr.success('Experiência removida com sucesso');
     $scope.close();
    }, function(error){
     toastr.error(error, 'ERRO AO REMOVER EXPERIENCIA: ');
    });
  };

  $scope.edit = function(experiencia){
    $scope.experiencia = experiencia;
  };

  $scope.clear = function(){
    $scope.experiencia = {};
  };

  $scope.close = function() {
    $modalInstance.close();
  };

  $scope.openDatePickerDataInicio = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.openedDataInicio = !$scope.openedDataInicio;
  };

  $scope.openDatePickerDataTermino = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    scope.openedDataTermino = !$scope.openedDataTermino;
  };

});

