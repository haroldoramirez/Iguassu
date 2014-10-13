'use strict';

describe('Service: Encaminhamento', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Encaminhamento;
  beforeEach(inject(function (_Encaminhamento_) {
    Encaminhamento = _Encaminhamento_;
  }));

  it('should do something', function () {
    expect(!!Encaminhamento).toBe(true);
  });

});
