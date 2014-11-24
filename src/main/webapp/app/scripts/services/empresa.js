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
      query: {method: 'POST', url: BaseUrl + '/empresas/:pagina', isArray: true},
      // &?rua=:rua&?numero=:numero&?complemento=:complemento&?idBairro=:idBairro&?idCidade=:idCidade&?idEstado=:idEstado&?idPais=:idPais
      getAll: {method: 'GET', url: BaseUrl + '/empresas', isArray: true}
    });
  }]);