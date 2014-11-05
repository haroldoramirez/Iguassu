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
          'ui.bootstrap',
          'angularFileUpload'
 ]).config(function($routeProvider,toastrConfig) {
   $routeProvider
     .when('/candidatos', {
     controller : 'CandidatoCtrl',
     templateUrl : './views/candidato/candidato.html'
   }).when('/candidatos/:id', {
     controller : 'CandidatoCtrl',
     templateUrl : './views/candidato/candidato.html'
   }).when('/candidatos/:id/contrato', {
     controller : 'ContratoCadastroCandidatoCtrl',
     templateUrl : './views/contrato.html'
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
   }).when('/encaminhamentos/:id/contrato', {
      controller : 'ContratoEncaminhamentoCtrl',
      templateUrl : './views/contrato.html'
   }).when('/login', {
     controller : 'LoginCtrl',
     templateUrl : './views/login.html'
   }).when('/configuracoes', {
     controller : 'ConfiguracoesCtrl',
     templateUrl : './views/configuracoes.html'
   }).when('/usuarios', {
     controller : 'UsuarioCtrl',
     templateUrl : './views/usuario/usuario.html'
   }).when('/financeiro', {
     controller : 'FinanceiroCtrl',
     templateUrl : './views/financeiro/financeiro.html'
   }).when('/', {
     controller : 'HomeCtrl',
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
 }).config(function($httpProvider){
  
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
        $location.path('/login');
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

 }).run(function($rootScope, $modal, $location, Usuario, Curso, Empresa, Cargo, Pais, Estado, Cidade, Bairro, Vaga, Candidato, CategoriasCursos, Encaminhamento){
  
  $rootScope.usuario = Usuario.getCurrent();

  $rootScope.candidato = {};

  $rootScope.endereco = {};
  
  $rootScope.getCandidatos = function(){
    $rootScope.candidatos = Candidato.getAll();
  };

  $rootScope.getEncaminhamentos = function(){
    $rootScope.encaminhamentos = Encaminhamento.getAll();
  };                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  

  $rootScope.getEmpresas = function(){
    $rootScope.empresas = Empresa.query();
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
    $rootScope.estados = Estado.getAllByPais({idPais:idPais});
  };

  $rootScope.getCidades = function(idEstado){
    $rootScope.cidades = Cidade.getAllByEstado({idEstado:idEstado});
  };

  $rootScope.getBairros = function(idCidade){
    $rootScope.bairros = Bairro.getAllByCidade({idCidade:idCidade});
  };

  $rootScope.getVagas = function(){
    $rootScope.vagas = Vaga.getAll();
  };

  $rootScope.sair = function(){
    $rootScope.usuario = {};
    $location.path("/");
  };

  if(!$rootScope.paises){
    $rootScope.getPaises();
  };

  
  $rootScope.today = new Date();
  
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
    $rootScope.getCursos();
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
    $rootScope.getCategoriasDeCursos();
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
