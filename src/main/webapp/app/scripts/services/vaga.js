'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Vaga
 * @description
 * # Vaga
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Vaga', ['$resource', 'BaseUrl',
  function($resource, BaseUrl){
   return $resource(BaseUrl + '/vagas/:id', {}, {
     getAll: {method: 'GET', url: BaseUrl + '/vagas/', isArray: true}
     });
  }]);
