'use strict';

describe('Controller: EncaminhamentoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var EncaminhamentoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EncaminhamentoCtrl = $controller('EncaminhamentoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
