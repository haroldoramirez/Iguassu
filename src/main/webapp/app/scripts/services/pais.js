'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Pais
 * @description
 * # Pais
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Pais',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/paises/:id', {}, {
        getAll: {method: 'GET', params:{}, url: BaseUrl + '/paises', isArray: true},
      });
    }]);