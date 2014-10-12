'use strict';

describe('Controller: EstadoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var EstadoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EstadoCtrl = $controller('EstadoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
