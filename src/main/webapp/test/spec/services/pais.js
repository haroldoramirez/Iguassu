'use strict';

describe('Service: Pais', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Pais;
  beforeEach(inject(function (_Pais_) {
    Pais = _Pais_;
  }));

  it('should do something', function () {
    expect(!!Pais).toBe(true);
  });

});
