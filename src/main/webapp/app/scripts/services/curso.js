'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Curso
 * @description
 * # Curso
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
.service('Curso', ['$resource', 'BaseUrl',
  function($resource, BaseUrl){
   return $resource(BaseUrl + '/cursos/:id', {}, {
     getAll: {method: 'GET', url: BaseUrl + '/cursos/', isArray: true}
	});
}])
.service('CategoriasCursos', ['$resource', 'BaseUrl',
  function($resource, BaseUrl){
   return $resource(BaseUrl + '/categoriascursos/:id', {}, {
     getAll: {method: 'GET', url: BaseUrl + '/categoriascursos/', isArray: true}
	});
}]);
