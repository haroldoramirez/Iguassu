'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.createAddress
 * @description
 * # createAddress
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .factory('createAddress', function () {

    return {
      formateEndereco: function (endereco) {
        console.log('formatando endereço');
        var enderecoResult = {};
          if (endereco) {
            enderecoResult = {
              bairro : {
                cidade : {
                  estado : {
                    pais : {

                    }
                  }
                }
              }
            }
           if (endereco.rua) enderecoResult.rua = endereco.rua;
           if (endereco.complemento) enderecoResult.complemento = endereco.complemento;
           if (endereco.numero) enderecoResult.numero = endereco.numero;
           if (endereco.cep) enderecoResult.cep = endereco.cep;
           if (endereco.bairro) enderecoResult.bairro = endereco.bairro;
           if (endereco.cidade) enderecoResult.bairro.cidade = endereco.cidade;
           if (endereco.estado) enderecoResult.bairro.cidade.estado = endereco.estado;
           if (endereco.pais) enderecoResult.bairro.cidade.estado.pais = endereco.pais; 
          };
        return enderecoResult;
      },
      desformateEndereco: function (endereco) {
        var enderecoResult = {};
        console.log('desformatando endereço');
        if (endereco) {
          if (endereco.rua) enderecoResult.rua = endereco.rua;
          if (endereco.complemento) enderecoResult.complemento = endereco.complemento;
          if (endereco.numero) enderecoResult.numero = endereco.numero;
          if (endereco.cep) enderecoResult.cep = endereco.cep;
          if (endereco.bairro) {
            enderecoResult.bairro = endereco.bairro;
            if (endereco.bairro.cidade){
              enderecoResult.cidade = endereco.bairro.cidade;
            }
            if (endereco.bairro.cidade.estado){
              enderecoResult.estado = endereco.bairro.cidade.estado;
            } 
            if (endereco.bairro.cidade.estado.pais){
              enderecoResult.pais = endereco.bairro.cidade.estado.pais;
            }  
          }
        };
        return enderecoResult;
      }
    };
  });
