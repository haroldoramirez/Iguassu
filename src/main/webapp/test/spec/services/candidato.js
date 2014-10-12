'use strict';

describe('Service: Candidato', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Candidato;
  beforeEach(inject(function (_Candidato_) {
    Candidato = _Candidato_;
  }));

  it('should do something', function () {
    expect(!!Candidato).toBe(true);
  });

});
