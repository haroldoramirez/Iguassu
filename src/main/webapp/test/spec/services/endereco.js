'use strict';

describe('Service: Endereco', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Endereco;
  beforeEach(inject(function (_Endereco_) {
    Endereco = _Endereco_;
  }));

  it('should do something', function () {
    expect(!!Endereco).toBe(true);
  });

});
