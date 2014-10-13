'use strict';

describe('Service: Cargo', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Cargo;
  beforeEach(inject(function (_Cargo_) {
    Cargo = _Cargo_;
  }));

  it('should do something', function () {
    expect(!!Cargo).toBe(true);
  });

});
