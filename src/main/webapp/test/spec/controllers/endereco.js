'use strict';

describe('Controller: EnderecoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var EnderecoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EnderecoCtrl = $controller('EnderecoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
