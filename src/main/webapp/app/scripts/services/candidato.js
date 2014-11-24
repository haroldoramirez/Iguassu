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
        query: {method: 'POST', url: BaseUrl + '/candidatos/:pagina', isArray: true},
        renovarContrato: {method: 'GET', url: BaseUrl + '/candidatos/:id/renovar/contrato', isArray: false},
        getLancamentos: {method: 'GET', url: BaseUrl + '/candidatos/:id/lancamentos', isArray: true},
        getContrato: {method: 'GET', url: BaseUrl + '/candidatos/:id/contrato', isArray:true},
        getCursos: {method: 'GET', url: BaseUrl + '/candidatos/:id/cursos', isArray: true},
        saveCurso: {method: 'POST', url: BaseUrl + '/candidatos/cursos', isArray: false},
        deleteCurso: {method: 'DELETE', url: BaseUrl + '/candidatos/cursos/:id'},
        getExperiencias: {method: 'GET', url: BaseUrl + '/candidatos/:id/experiencias', isArray: true},
        saveExperiencia: {method: 'POST', url: BaseUrl + '/candidatos/experiencias', isArray: false},
        deleteExperiencia: {method: 'DELETE', url: BaseUrl + '/candidatos/experiencias/:id'}
      });
    }]);