'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Usuario
 * @description
 * # Usuario
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Usuario',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
	    return $resource(BaseUrl + '/usuarios/:id', {}, {
	      getCurrent: {method: 'GET', url: BaseUrl + '/usuarios/current', isArray: false}
	    });
	  }]);