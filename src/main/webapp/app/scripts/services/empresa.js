'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Empresa
 * @description
 * # Empresa
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Empresa', ['$resource', 'BaseUrl',
    function($resource, BaseUrl){
     return $resource(BaseUrl + '/empresas/:id', {}, {
        getAll: {method: 'GET', url: BaseUrl + '/empresas/', isArray: true}
      });
    }]);