'use strict';

describe('Controller: BairroCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var BairroCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BairroCtrl = $controller('BairroCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
