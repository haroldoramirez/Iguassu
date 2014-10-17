'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Candidato
 * @description
 * # Candidato
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Candidato',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/candidatos/:id', {}, {
        getAll: {method: 'GET', url: BaseUrl + '/candidatos/', isArray: true},
        getCursos: {method: 'GET', url: BaseUrl + '/candidatos/:id' + '/cursos', isArray: true},
        saveCurso: {method: 'POST', url: BaseUrl + '/candidatos/cursos', isArray: false},
        deleteCurso: {method: 'DELETE', url: BaseUrl + '/candidatos/cursos/:id'},
        getExperiencias: {method: 'GET', url: BaseUrl + '/candidatos/:id' + '/experiencias', isArray: true},
        saveExperiencia: {method: 'POST', url: BaseUrl + '/candidatos/experiencias', isArray: false},
        deleteExperiencia: {method: 'DELETE', url: BaseUrl + '/candidatos/experiencias/:id'}
      });
    }]);