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
	    	getAll: {method: 'GET', url: BaseUrl + '/usuarios', isArray: true},
	      getCurrent: {method: 'GET', url: BaseUrl + '/usuarios/current', isArray: false},
	      update: {method: 'PUT', url: BaseUrl + '/usuarios/:id', isArray: false}
	    });
	  }]);