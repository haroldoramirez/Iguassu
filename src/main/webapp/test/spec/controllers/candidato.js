'use strict';

describe('Controller: CandidatoCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var CandidatoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CandidatoCtrl = $controller('CandidatoCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
