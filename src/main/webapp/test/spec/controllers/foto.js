'use strict';

describe('Controller: FotoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var FotoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FotoCtrl = $controller('FotoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
