'use strict';

describe('Controller: CargoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var CargoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CargoCtrl = $controller('CargoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
