'use strict';

describe('Controller: FerramentasCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var FerramentasCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FerramentasCtrl = $controller('FerramentasCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
