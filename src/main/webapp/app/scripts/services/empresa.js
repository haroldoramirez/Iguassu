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
      queryByCity: {method: 'POST', url: BaseUrl + '/empresas/:pagina/&?idCidade=:idCidade', isArray: true},
      queryByState: {method: 'POST', url: BaseUrl + '/empresas/:pagina/&?idEstado=:idEstado', isArray: true},
      queryByCountry: {method: 'POST', url: BaseUrl + '/empresas/:pagina/&?idPais=:idPais', isArray: true},
      // &?rua=:rua&?numero=:numero&?complemento=:complemento&?idBairro=:idBairro&?idCidade=:idCidade&?idEstado=:idEstado&?idPais=:idPais
      getAll: {method: 'GET', url: BaseUrl + '/empresas', isArray: true}
    });
  }]);