'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FotoCtrl
 * @description
 * # FotoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('FotoCtrl', function ($scope, $routeParams, $location) {
    $scope.id = $routeParams.id;
    console.log($scope.id);
    $scope.getFoto = function(id){
    	console.log('FUNCIONOU');
    	$location.path('/candidatos');
    }
  }).directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]).service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);
