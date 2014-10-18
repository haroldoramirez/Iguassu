'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Endereco
 * @description
 * # Endereco
 * Service in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Pais',['$resource', 'BaseUrl',
  function($resource, BaseUrl){
    return $resource(BaseUrl + '/paises/:id', {}, {
      getAll: {method: 'GET', params:{}, url: BaseUrl + '/paises', isArray: true},
    });
  }])
  .service('Estado',['$resource', 'BaseUrl',
  function($resource, BaseUrl){
    return $resource(BaseUrl + '/estados/:id', {}, {
      getAllByPais: {method: 'GET', params:{idPais:'idPais'}, url: BaseUrl + '/estados/pais/:idPais', isArray: true},
    });
  }])
  .service('Cidade',['$resource', 'BaseUrl',
  function($resource, BaseUrl){
    return $resource(BaseUrl + '/cidades/:id', {}, {
    	getAllByEstado: {method: 'GET', params:{}, url: BaseUrl + '/cidades/estado/:idEstado', isArray: true},
    });
  }])
  .service('Bairro',['$resource', 'BaseUrl',
  function($resource, BaseUrl){
    return $resource(BaseUrl + '/bairros/:id', {}, {
      getAllByCidade: {method: 'GET', params:{}, url: BaseUrl + '/bairros/cidade/:idCidade', isArray: true},
    });
  }]);
