'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FotoCtrl
 * @description
 * # FotoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('ContratoCadastroCandidatoCtrl', function ($scope, $routeParams, $upload, $location, Candidato) {
    $scope.init = function() {
      Candidato.getContrato({id: $routeParams.id}, function(data){
        console.log(data);
        $scope.url = '/Iguassu' + data[0];
        // console.log($scope.url);
      });
    };
  });
