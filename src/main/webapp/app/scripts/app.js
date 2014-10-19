'use strict';

/**
 * @ngdoc overview
 * @name iguassuApp
 * @description
 * # iguassuApp
 *
 * Main module of the application.
 */
angular
  .module('iguassuApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.utils',
    'toastr',
    'duScroll',
    'ui.bootstrap'
 ]).config(function($routeProvider,toastrConfig) {
   $routeProvider
     .when('/candidatos', {
     controller : 'CandidatoCtrl',
     templateUrl : './views/candidato/candidato.html'
   }).when('/candidatos/:id', {
     controller : 'CandidatoCtrl',
     templateUrl : './views/candidato/candidato.html'
   }).when('/empresas', {
          controller : 'EmpresaCtrl',
          templateUrl : './views/empresa/empresa.html'
   }).when('/empresas/:id', {
     controller : 'EmpresaCtrl',
     templateUrl : './views/empresa/empresa.html'
   }).when('/vagas', {
          controller : 'VagaCtrl',
          templateUrl : './views/vaga/vaga.html'
   }).when('/vagas/:id', {
     controller : 'VagaCtrl',
     templateUrl : './views/vaga/vaga.html'
   }).when('/encaminhamentos', {
     controller : 'EncaminhamentoCtrl',
     templateUrl : './views/encaminhamento/encaminhamento.html'
   }).when('/encaminhamentos/:id', {
          controller : 'EncaminhamentoCtrl',
          templateUrl : './views/encaminhamento/encaminhamento.html'
   }).when('/login', {
     controller : '',
     templateUrl : './views/login.html'
   }).when('/configuracoes', {
     controller : '',
     templateUrl : './views/configuracoes.html'
   }).when('/', {
     controller : '',
     templateUrl : './views/home.html'
   }).otherwise({
     redirectTo : '/'
   });
 angular.extend(toastrConfig, {
  allowHtml: true,
  closeButton: true,
  closeHtml: '<button>&times;</button>',
  containerId: 'toast-container',
  extendedTimeOut: 5000,
  iconClasses: {
    error: 'toast-error',
    info: 'toast-info',
    success: 'toast-success',
    warning: 'toast-warning'
  },
  messageClass: 'toast-message',
  positionClass: 'toast-top-right',
  tapToDismiss: true,
  timeOut: 5000,
  titleClass: 'toast-title',
  toastClass: 'toast'
});
 })/*.config(function($httpProvider){

     var interceptor = function ($rootScope, $q, $location) {

           function success(response) {
               return response;
           };

           function error(response) {

               var status = response.status;
               var config = response.config;
               var method = config.method;
               var url = config.url;
               if (status === 403) {
                 $location.path("/login");
               } else{};



               if (status == 401) {
             delete $rootScope.user;
                 if($location.path() != '/login') {
                   $rootScope.redirectUrl = angular.copy($location.url());
                 }
                 $rootScope.redirectStatus = 401;
                 $location.url('/login');
               } else if (status == 403) {
                 $location.url('/403');
               } else if (status == 500) {
                 $location.url('/500');
               } else if (status == 503) {
                 if($location.path() != '/503') {
                   $rootScope.redirectUrl = angular.copy($location.url());
                 }
                 $location.url('/503');
               } else{
                 //skip others
               }



               return $q.reject(response);
           };

           return function (promise) {
               return promise.then(success, error);
           };
       };
       $httpProvider.responseInterceptors.push(interceptor);
 })*/

.run(function($rootScope, $modal, Curso, Empresa, Cargo, Pais, Estado, Cidade, Bairro, Candidato, CategoriasCursos){

  $rootScope.candidato = {};

  $rootScope.endereco = {};

  $rootScope.getCandidatos = function(){
    $rootScope.candidatos = Candidato.getAll();
  };

  $rootScope.getEmpresas = function(){
    $rootScope.empresas = Empresa.getAll();
  };

  $rootScope.getCargos = function(){
    $rootScope.cargos = Cargo.getAll();
  };

  $rootScope.getCursos = function(){
    $rootScope.cursos = Curso.getAll();
  };

  $rootScope.getCategoriasDeCursos = function(){
    $rootScope.categoriasDeCursos = CategoriasCursos.getAll();
  };

  $rootScope.getPaises = function(){
    $rootScope.paises = Pais.getAll();
  };

  $rootScope.getEstados = function(idPais){
    if (idPais)   {
      $rootScope.estados = Estado.getAllByPais({idPais:idPais});
    };
  };

  $rootScope.getCidades = function(idEstado){
    if (idEstado) { 
      $rootScope.cidades = Cidade.getAllByEstado({idEstado:idEstado});
    }
  };

  $rootScope.getBairros = function(idCidade){
    if (idCidade) { 
      $rootScope.bairros = Bairro.getAllByCidade({idCidade:idCidade});
    }
  };

  if(!$rootScope.paises){
    $rootScope.getPaises();
  };

  $rootScope.openBairro = function(cidade, bairro) {
    if (!$rootScope.bairros) {
      $rootScope.getBairros();
    };
    $modal.open({
      templateUrl : 'bairros.html',
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
    }).result.then(function() {
      $rootScope.getBairros();  
    }, function(){
      $rootScope.getBairros();
    });
  };

  $rootScope.openCidade = function(estado,cidade) {
    if (!$rootScope.cidades) {
      $rootScope.getCidades();
    };
    $modal.open({
      templateUrl : 'cidades.html',
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
      $rootScope.getCidades();  
    }, function(cidade){
      $rootScope.getCidades();
    });
  };

  $rootScope.openEstado = function(pais,estado) {
    if (!$rootScope.estados) {
      $rootScope.getEstados();
    };
    $modal.open({
      templateUrl : 'estados.html',
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
    }).result.then(function() {
      $rootScope.getEstados();  
    }, function(){
      $rootScope.getEstados();
    });
  };

  $rootScope.openPais = function(pais) {
    console.log(pais);
    if (!$rootScope.paises) {
      $rootScope.getPaises();
    };
    $modal.open({
      templateUrl : 'paises.html',
      controller : 'PaisCtrl',
      size : 'md',
      resolve : {
        bundle : function(){
          return {
            pais : pais
          }
        }
      }
    }).result.then(function() {
      $rootScope.getPaises();  
    }, function(){
      $rootScope.getPaises();
    });
  };

  $rootScope.openCargos = function() {
    $rootScope.getCargos();
    $rootScope.getEmpresas();
    $modal.open({
      templateUrl : 'cargos.html',
      controller : 'CargoCtrl',
      size : 'md'
    }).result.then(function() {
      $rootScope.getCargos();  
    }, function(){
      $rootScope.getCargos();
    });
  };

  $rootScope.openCursos = function() {
    if (!$rootScope.cursos) {
      $rootScope.getCursos();
    };
    if (!$rootScope.categoriasDeCursos) {
      $rootScope.getCategoriasDeCursos();
    };
    $modal.open({
      templateUrl : 'cursos.html',
      controller : 'CursoCtrl',
      size : 'md'
    }).result.then(function() {
      $rootScope.getCursos();  
    }, function(){
      $rootScope.getCursos();
    });
  };

  $rootScope.openCategoriasCursos = function() {
    if (!$rootScope.categoriasDeCursos) {
      $rootScope.getCategoriasDeCursos();
    };
    $modal.open({
      templateUrl : 'categoriasDeCursos.html',
      controller : 'CategoriaCursoCtrl',
      size : 'sm'
    }).result.then(function() {
      $rootScope.getCategoriasDeCursos();  
    }, function(){
      $rootScope.getCategoriasDeCursos();
    });
  };
   
 });
