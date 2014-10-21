'use strict';

describe('Service: Autenticacao', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Autenticacao;
  beforeEach(inject(function (_Autenticacao_) {
    Autenticacao = _Autenticacao_;
  }));

  it('should do something', function () {
    expect(!!Autenticacao).toBe(true);
  });

});
