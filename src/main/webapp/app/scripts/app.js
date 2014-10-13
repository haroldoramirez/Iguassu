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
 ]).config(function($routeProvider) {
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
 })*/.run(function($rootScope){
   $rootScope.isCollapsed = false;

   $rootScope.isOpen = false;

   $rootScope.panel = {
     open1 : null,
     open2 : null,
     open3 : null,
     open4 : null,
     open5 : null,
     open6 : null
   }
 });
