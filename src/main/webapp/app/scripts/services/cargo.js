'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Cargo
 * @description
 * # Cargo
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Cargo', ['$resource', 'BaseUrl',
  function($resource, BaseUrl){
   return $resource(BaseUrl + '/cargos/:id', {}, {
     getAll: {method: 'GET', url: BaseUrl + '/cargos/', isArray: true}
	});
  }]);
