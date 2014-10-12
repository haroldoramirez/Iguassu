'use strict';

describe('Controller: LancamentoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var LancamentoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LancamentoCtrl = $controller('LancamentoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
