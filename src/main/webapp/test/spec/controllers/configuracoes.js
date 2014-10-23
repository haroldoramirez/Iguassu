'use strict';

describe('Controller: ConfiguracoesCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var ConfiguracoesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConfiguracoesCtrl = $controller('ConfiguracoesCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
