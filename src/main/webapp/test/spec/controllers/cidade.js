'use strict';

describe('Controller: CidadeCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var CidadeCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CidadeCtrl = $controller('CidadeCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
