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
      query: {method: 'GET', url: BaseUrl + '/empresas/:pagina/:paginas/:order/?nome=:nome&?cnpj=:cnpj&?rua=:rua&?numero=:numero&?complemento=:complemento&?idBairro=:idBairro&?idCidade=:idCidade&?idEstado=:idEstado&?idPais=:idPais', isArray: true}
    });
  }]);