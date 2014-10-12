'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:VagaCtrl
 * @description
 * # VagaCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('VagaCtrl', function ($scope, $http, BaseUrl, $modal, $log) {

  var url = BaseUrl + 'vagas/';

  console.log(BaseUrl);

  $scope.empresas = [];
  $scope.vagas = [];
  
  $scope.isOpen = false;

  $scope.panel = {
    open1 : null,
    open2 : null,
    open3 : null,
    open4 : null,
    open5 : null,
    open6 : null
  }

  $scope.choicesLocale = [ 'Bairros', 'Cidades', 'Estados', 'Paises' ];
  $scope.address = {selected : 'bairros'};

  $scope.choicesSexo = [ 'masculino', 'feminino'];
  $scope.sexo = {selected : 'masculino'};

  $scope.choicesCurso = [ 'conclusao', 'curso', 'categoria'];
  $scope.curso = {selected : 'curso'};

  $scope.choicesSituacaoDoCandidato = ['Disponível', 
                                       'Bloqueado', 
                                       'Encaminhado (a)', 
                                       'Empregado (a)', 
                                       'Empregado (a) pela Iguassu', 
                                       'Empregado (a) pela Iguassu e inadimplente'];

  $scope.choicesSituacaoDoCandidato = ['Disponível', 
                                       'Bloqueado', 
                                       'Encaminhado (a)', 
                                       'Empregado (a)', 
                                       'Empregado (a) pela Iguassu', 
                                       'Empregado (a) pela Iguassu e inadimplente'];

  
  $scope.vaga = {
    id : null,
    observacoes : null,
    situacao : null,
    salario : null,
    preferenciaSexoMasculino : null,
    idadeInicialDoCandidato : null,
    idadeFinalDoCandidato : null,
    estadoCivil : null,
    dataDeCadastro : null,
    cargo : {
      id : null,
      nome : null,
      descricao : null
    },
    empresa : {
      id : null,
      nome : null,
      cnpj : null
    },
    endereco : {
        rua : null,
        numero : null,
        cep : null,
        complemento : null,
        bairro : {
        id : null,
        nome : null,
        cidade : {
          id : null,
          nome : null,
          estado : {
            id : null,
            nome : null,
            pais : {
              id : null,
              nome : null,
            }
          }
        }
      }
    }
  };

  $scope.msg = {
    progress : null,
    type : null,
    msg : null,
    vaga : null
  };

  // funcionalidades

  $scope.closeAlert = function() {
    $scope.msg = {
        progress : null,
        type : null,
        msg : null,
        vaga : {
          id : null,
          observacoes : null,
          situacao : null,
          salario : null,
          preferenciaSexoMasculino : null,
          idadeInicialDoCandidato : null,
          idadeFinalDoCandidato : null,
          estadoCivil : null,
          dataDeCadastro : null,
          cargo : {
            id : null,
            nome : null,
            descricao : null
          },
          empresa : {
            id : null,
            nome : null,
            cnpj : null
          },
          endereco : {
              rua : null,
              numero : null,
              cep : null,
              complemento : null,
              bairro : {
              id : null,
              nome : null,
              cidade : {
                id : null,
                nome : null,
                estado : {
                  id : null,
                  nome : null,
                  pais : {
                    id : null,
                    nome : null,
                  }
                }
              }
            }
          }
        }
      }
    };
  
  
  // CANDIDATO GET & SET
  $scope.postVaga = function() {
    console.log('Limpar candidato');
      $scope.msg.progress = true;
      $scope.closeAlert();
      console.log("Salvando candidato ", $scope.vaga);
      $http.post(server + "vagas/", {
        "id" : $scope.vaga.id,
        "observacoes" : $scope.vaga.observacoes,
        "situacao" : $scope.vaga.situacao,
        "salario" : $scope.vaga.salario,
        "preferenciaSexoMasculino" : $scope.vaga.preferenciaSexoMasculino,
        "idadeInicialDoCandidato" : $scope.vaga.idadeInicialDoCandidato,
        "idadeFinalDoCandidato" : $scope.vaga.idadeFinalDoCandidato,
        "estadoCivil" : $scope.vaga.estadoCivil,
        "dataNasc" : $scope.vaga.dataNasc,
        "dataDeCadastro" : $scope.vaga.dataDeCadastro,
        "cargo": $scope.vaga.cargo,
        "empresa": $scope.vaga.empresa,
        "endereco" : $scope.vaga.endereco
      }).success(function(data, status, headers, config) {
        $scope.limpe();
        $scope.msg = data;
        if ($scope.msg.vaga!=null) {
          $scope.vaga = $scope.msg.vaga;
        }
        console.log($scope.msg);
        $scope.getVaga();
        $scope.msg.progress = null;
      }).error(function(data) {
        console.log('Deu erro no postCidade');
        $scope.msg.progress = null;
    });
  };

  $scope.getVaga = function() {
    $scope.msg.progress = true;
    $http.get(server+"vagas/?").success(function(data, status, headers, config) {
      $scope.vagas = data.vagas;
      console.log($scope.vagas);
      $scope.msg.progress = null;
    }).error(function(data) {
      console.log('Deu erro no getCandidato');
    });
  };

  $scope.editVaga = function(vaga) {
    $scope.msg.progress = true;
    $scope.vaga = vaga;
    $scope.getVaga();
  };

  $scope.deleteVaga = function(id) {
    $scope.msg.progress = true;
    $http.delete(server + "vagas/"+id)
    .success(function(data, status, headers, config) {
      $scope.limpe();
      $scope.vagas = data.vagas;
      $scope.msg.type = data.type;
      $scope.msg.msg = data.msg;
      $scope.msg.vaga = data.vaga;
      $scope.getVaga(false);
      $scope.msg.progress = null;
    }).error(function(){
      console.log('Deu erro no deleteCandidato');
      $scope.msg.progress = null;
    });
  };

  
  $scope.getEmpresa = function() {
    $scope.msg.progress = true;
    $http.get(server+"empresas/?").success(function(data, status, headers, config) {
      $scope.empresas = data.empresas;
      console.log($scope.empresas);
      $scope.msg.progress = null;
    }).error(function(data) {
      console.log('Deu erro no getCandidato');
    });
  };
  
  
  // Cargos
  
  $scope.openCargo = function(size) {
    $scope.msg.progress = true;
      $http.get(server + "cargos").success(
        function(data, status, headers, config) {
          var modalInstance = $modal.open({
            templateUrl : 'cargo.html',
            controller : 'controllerCargo',
            size : size,
            resolve : {
              bundle : function() {
                return {
                  msg : data,
                  cargos : $scope.cargos
                }
              }
            }
          }).result.then(function(contatos) {
            $scope.cargos = cargos;
            $scope.msg.progress = null;
          }, function() {
            $log.info('Modal dismissed at: '
                + new Date());
            $scope.msg.progress = null;
          });
        }).error(function(data) {
          console.log('Deu erro no getBairro');
      $scope.msg.progress = null;
    });
  };
  
  
  $scope.getCargo = function() {
    $scope.msg.progress = true;
    $http.get(server + "cargos/").success(
      function(data, status, headers, config) {
        $scope.cargos = data.cargos;
        $scope.msg.progress = null;
      }).error(function(data) {
        console.log('Deu erro no getCidade');
      $scope.msg.progress = null;
    });
  };

  // address

  $scope.openBairro = function(size) {
    $scope.msg.progress = true;
    $http.get(server + "cidades").success(
        function(data, status, headers, config) {
          $scope.cidades = data.cidades;
          $http.get(server + "bairros").success(
              function(data, status, headers, config) {
                var modalInstance = $modal.open({
                  templateUrl : 'bairro.html',
                  controller : 'controllerBairro',
                  size : size,
                  resolve : {
                    bundle : function() {
                      return {
                        msg : data,
                        cidades : $scope.cidades
                      }
                    }
                  }
                }).result.then(function(contatos) {
                  $scope.contatos = contatos;
                  $scope.msg.progress = null;
                }, function() {
                  $log.info('Modal dismissed at: '
                      + new Date());
                  $scope.msg.progress = null;
                });
              }).error(function(data) {
            console.log('Deu erro no getBairro');
            $scope.msg.progress = null;
          });
        }).error(function(data) {
      console.log('Deu erro no getCidade');
      $scope.msg.progress = null;
    });
  };
  

  $scope.getBairro = function() {
    $scope.msg.progress = true;
    $http.get(server + "bairros/").success(
        function(data, status, headers, config) {
          $scope.bairros = data.bairros;
          $scope.msg.progress = null; 
        }).error(function(data) {
      console.log('Deu erro no getCidade');
      $scope.msg.progress = null;
    });
  };

  $scope.openPais = function(size) {
    $scope.msg.progress = true;
    $http.get(server + "paises/").success(
        function(data, status, headers, config) {
          var modalInstance = $modal.open({
            templateUrl : 'pais.html',
            controller : 'controllerPais',
            size : size,
            resolve : {
              items : function() {
                return data;
              }
            }
          }).result.then(function(items) {
            $scope.paises = items;
            $scope.msg.progress = null;
          }, function() {
            console.log('Abrindo consulta de Pais');
            $scope.msg.progress = null;
          });
        }).error(function(data) {
      console.log('Deu erro no getPais');
      $scope.msg.progress = null;
    });
  };

  $scope.getPais = function() {
    $scope.msg.progress = true;
    $http.get(server + "paises/").success(
        function(data, status, headers, config) {
          $scope.paises = data.paises;
          $scope.msg.progress = null; 
        }).error(function(data) {
      console.log('Deu erro no getPais');
      $scope.msg.progress = null;
    });
  };
    
  
  $scope.limpe = function() {
    console.log('Limpar candidato');
    $scope.vaga = {
        id : null,  
        observacoes : null,
        situacao : null,
        salario : null,
        preferenciaSexoMasculino : null,
        idadeInicialDoCandidato : null,
        idadeFinalDoCandidato : null,
        estadoCivil : null,
        dataDeCadastro : null,
        cargo : {
          id : null,
          nome : null,
          descricao : null
        },
        empresa : {
          id : null,
          nome : null,
          cnpj : null
        },
        endereco : {
            rua : null,
            numero : null,
            cep : null,
            complemento : null,
            bairro : {
            id : null,
            nome : null,
            cidade : {
              id : null,
              nome : null,
              estado : {
                id : null,
                nome : null,
                pais : {
                  id : null,
                  nome : null,
                }
              }
            }
          }
        }
      };
  };
});
