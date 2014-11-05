'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FotoCtrl
 * @description
 * # FotoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('ContratoEncaminhamentoCtrl', function ($scope, $routeParams, $upload, $location, Encaminhamento) {
    $scope.init = function() {
      Encaminhamento.getContrato({id: $routeParams.id}, function(data){
        console.log(data);
        $scope.url = '/Iguassu' + data[0];
        // console.log($scope.url);
      });
    };
  });
