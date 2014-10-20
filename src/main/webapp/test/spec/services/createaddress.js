'use strict';

describe('Service: createAddress', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var createAddress;
  beforeEach(inject(function (_createAddress_) {
    createAddress = _createAddress_;
  }));

  it('should do something', function () {
    expect(!!createAddress).toBe(true);
  });

});
