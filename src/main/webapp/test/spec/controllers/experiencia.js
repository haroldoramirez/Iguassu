'use strict';

describe('Controller: ExperienciaCtrl', function () {

  // load the controller's module
  beforeEach(module('iguassuApp'));

  var ExperienciaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ExperienciaCtrl = $controller('ExperienciaCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
