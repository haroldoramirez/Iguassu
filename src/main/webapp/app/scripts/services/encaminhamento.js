'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Encaminhamento
 * @description
 * # Encaminhamento
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Encaminhamento', ['$resource', 'BaseUrl',
    function($resource, BaseUrl){
     return $resource(BaseUrl + '/encaminhamentos/:id', {}, {
     	getContrato: {method: 'GET', url: BaseUrl + '/encaminhamentos/:id/contrato', isArray:true},
      getAll: {method: 'GET', url: BaseUrl + '/encaminhamentos/', isArray: true}
    });
  }]);