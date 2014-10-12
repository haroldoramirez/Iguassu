'use strict';

describe('Service: BaseUrl', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var BaseUrl;
  beforeEach(inject(function (_BaseUrl_) {
    BaseUrl = _BaseUrl_;
  }));

  it('should do something', function () {
    expect(!!BaseUrl).toBe(true);
  });

});
