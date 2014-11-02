'use strict';

angular.module('iguassuApp').directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files);
                });
            });
        }
    };
}]).service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file,  uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, {file:file}, {
            transformRequest: angular.identity,
            headers: {'Content-Type':'multipart/form-data'}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]).controller('CandidatoCtrl', function ($rootScope, $log, $http, $modal, $scope, $routeParams, $document, $location, Candidato, Pais, toastr, createAddress, fileUpload) {

  $scope.myFile = {};

//   var formData=new FormData();
// formData.append("file",file.files[0]);


  $scope.uploadFile = function(files){

    var file = files[0];
    console.log('file is ' + file);
    var uploadUrl = 'candidatos/foto/'+$scope.candidato.id;

    $http.post(uploadUrl, files, {
        transformRequest: angular.identity,
        headers: {'Content-Type':'multipart/form-data'}
    }).success(function(){

    }).error(function(){

    });
    // fileUpload.uploadFileToUrl(file, uploadUrl);
  };
  
  $scope.init = function(){
    if ($routeParams.id) {
      Candidato.get({id: $routeParams.id}, function(data){
        $scope.candidato = data;
        $scope.candidato.pathFoto = 'home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images/candidatos/36';
        $scope.endereco = createAddress.desformateEndereco(data.endereco);
      });
      $scope.cursosDoCandidato = Candidato.getCursos({id: $routeParams.id});
      $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
      $rootScope.openAll();
    }else{
      $scope.clear();
    };
    $scope.getCandidatos();
  };

  $scope.goToPhoto = function(){
    $location.path('/candidatos/' + $scope.candidato.id + '/foto');
  };
  
  $scope.save = function(){
    $scope.candidato.endereco = $scope.endereco;
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
      $scope.init();
    });
  };

  $scope.openCurso = function(candidatoCurso) {
    
    if (!$scope.empresas) {
      $scope.getEmpresas();  
    };
    if (!$scope.cursos) {
      $scope.getCursos();  
    };

    $modal.open({
      templateUrl : 'cursosDoCandidato.html',
      controller : 'CandidatoCursoCtrl',
      size : 'md',
      resolve : {
       bundle : function() {
          return {
              candidatoCurso : candidatoCurso,
              candidato : $scope.candidato
          }
        }
      }
    }).result.then(function() {
        $scope.cursosDoCandidato = Candidato.getCursos({id: $routeParams.id});
      }, function(){
        $scope.cursosDoCandidato = Candidato.getCursos({id: $routeParams.id});
    });
  };

  $scope.openExperiencia = function(experiencia) {
    
    if (!$scope.empresas) {
      $scope.getEmpresas();  
    };
    if (!$scope.cargos) {
      $scope.getCargos();  
    };

    $modal.open({
        templateUrl : 'experienciasDoCandidato.html',
        controller : 'CandidatoExperienciaCtrl',
        size : 'md',
        resolve : {
         bundle : function() {
            return {
                experiencia : experiencia,
                candidato : $scope.candidato
            }
          }
        }
      }).result.then(function() {
        $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
      }, function(){
        $scope.experienciasDoCandidato = Candidato.getExperiencias({id: $routeParams.id});
    });
  };

  $scope.edit = function(candidato){
    $document.scrollTopAnimated(0, 700);
    $location.path('/candidatos/'+candidato.id);
  }

  $scope.clear = function(){
    $rootScope.endereco = {};
    $document.scrollTopAnimated(0, 700);
    $rootScope.candidato = {};
    $scope.cursosDoCandidato = {};
    $scope.experienciasDoCandidato = {};
    if ($routeParams.id) {
      $location.path('/candidatos');
    }
  }

  $scope.openDatePicker = function($event) {
    
    $event.preventDefault();
    $event.stopPropagation();   
    $scope.opened = !$scope.opened;
  };

}).controller('CandidatoExperienciaCtrl', function ($scope, $modalInstance,  $modal, Candidato, toastr, bundle) {

  $scope.experiencia = bundle.experiencia;
  $scope.candidato = bundle.candidato;

  $scope.today = new Date();

  $scope.save = function(){
    var msg = 'Expereiência adicionada com sucesso';
    if($scope.experiencia.id) {msg = 'Experiência atualizada com sucesso'; var b = true;}

    $scope.experiencia.candidato = $scope.candidato;

    Candidato.saveExperiencia($scope.experiencia, function(data){
      toastr.success(msg);
      $scope.close();
    }, function(error){
      toastr.error('Erro ao salvar experiência');
    });
  };

  $scope.delete = function(){
    Candidato.deleteExperiencia({id:$scope.experiencia.id}, function(data){
     toastr.success('Experiência removida com sucesso');
     $scope.close();
    }, function(error){
     toastr.error('Erro ao remover experiência');
    });
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
    $scope.openedDataTermino = !$scope.openedDataTermino;
  };

}).controller('CandidatoCursoCtrl', function ($scope, $modalInstance,  $modal, Candidato, toastr, bundle) {

  $scope.candidatoCurso = bundle.candidatoCurso;
  $scope.candidato = bundle.candidato;

  $scope.save = function(){
    var msg = 'Expereiência adicionada com sucesso';
    if($scope.candidatoCurso.id) {msg = 'Experiência atualizada com sucesso';}

    $scope.candidatoCurso.candidato = $scope.candidato;
    
    Candidato.saveCurso($scope.candidatoCurso, function(data){
      toastr.success(msg);
      $scope.close();
    }, function(error){
      toastr.error('Erro ao salvar curso do candidato');
    });
  };

  $scope.delete = function(){
    Candidato.deleteCurso({id:$scope.candidatoCurso.id}, function(data){
     toastr.success('Curso do candidato removido com sucesso');
     $scope.close();
    }, function(error){
      console.log(error);
      toastr.error(error, 'Erro ao remover curso do candidato');
    });
  };

  $scope.handlerPeriodos = function(){
    $scope.candidatoCurso.periodosConcluidos = $scope.candidatoCurso.quantidadeDePeriodos;
    if ($scope.candidatoCurso.situacaoDoCurso=='concluido') {
      $scope.candidatoCurso.periodosConcluidos = $scope.candidatoCurso.quantidadeDePeriodos;
    }else{
      $scope.candidatoCurso.periodosConcluidos = null;
    };
    console.log($scope.candidatoCurso.periodosConcluidos);
  };

  $scope.clear = function(){
    $scope.candidatoCurso = {};
  };

  $scope.close = function() {
    $modalInstance.close();
  };

});

