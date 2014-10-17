'use strict';

describe('Service: Curso', function () {

  // load the service's module
  beforeEach(module('iguassuApp'));

  // instantiate service
  var Curso;
  beforeEach(inject(function (_Curso_) {
    Curso = _Curso_;
  }));

  it('should do something', function () {
    expect(!!Curso).toBe(true);
  });

});
