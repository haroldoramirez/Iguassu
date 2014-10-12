'use strict';

describe('Controller: PaisCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var PaisCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PaisCtrl = $controller('PaisCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
