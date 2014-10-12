'use strict';

describe('Directive: InputDate', function () {

  // load the directive's module
  beforeEach(module('iguassuApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<-input-date></-input-date>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the InputDate directive');
  }));
});
