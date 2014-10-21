'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Autenticacao
 * @description
 * # Autenticacao
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Autenticacao', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/Iguassu/autenticacao', {}, {
    });
  }]);