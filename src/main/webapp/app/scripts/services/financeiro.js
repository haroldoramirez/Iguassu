'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Candidato
 * @description
 * # Candidato
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Financeiro',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/lancamentos/:id', {}, {
        getAll: {method: 'GET', url: BaseUrl + '/lancamentos/', isArray: true},
        delete: {method: 'DELETE', url: BaseUrl + '/lancamentos/:id'}
      });
    }]);