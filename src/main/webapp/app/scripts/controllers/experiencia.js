'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:ExperienciaCtrl
 * @description
 * # ExperienciaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('ExperienciaCtrl', function ($scope, $http, $modalInstance, bundle, $modal) {
    $scope.experiencias = bundle.experiencias;
    $scope.candidato = bundle.candidato;
  });
