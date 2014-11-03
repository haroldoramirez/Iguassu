'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.Candidato
 * @description
 * # Candidato
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .service('Financeiro',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/lancamentos/:id', {}, {
        getAll: {method: 'GET', url: BaseUrl + '/lancamentos/', isArray: true},
        query: {method: 'POST', url: BaseUrl + '/lancamentos/:pagina', isArray: true},
        queryData: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?data=:data', isArray: true},
        queryDataDeVencimento: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?dataDeVencimento=:dataDeVencimento', isArray: true},
        queryDataDePagamento: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?dataDePagamento=:dataDePagamento', isArray: true},
        queryDataEDataDeVencimento: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?data=:data&?dataDeVencimento=:dataDeVencimento', isArray: true},
        queryDataEDataDePagamento: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?data=:data&?dataDePagamento=:dataDePagamento', isArray: true},
        queryDataDePagamentoEDataDeVencimento: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?dataDeVencimento=:dataDeVencimento&?dataDePagamento=:dataDePagamento', isArray: true},
        queryAll: {method: 'GET', url: BaseUrl + '/lancamentos/:pagina/?data=:data&?dataDeVencimento=:dataDeVencimento&?dataDePagamento=:dataDePagamento', isArray: true},
        delete: {method: 'DELETE', url: BaseUrl + '/lancamentos/:id'}
      });
    }]);