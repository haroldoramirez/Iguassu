'use strict';

/**
 * @ngdoc function
 * @name iguassuApp.controller:FotoCtrl
 * @description
 * # FotoCtrl
 * Controller of the iguassuApp
 */
angular.module('iguassuApp')
  .controller('FotoCtrl', function ($scope, $routeParams, $upload, $location, $timeout, fileUpload) {
    $scope.onFileSelect = function($files) {
    for (var i = 0; i < $files.length; i++) {
      var file = $files[i];
      $scope.upload = $upload.upload({
        url: 'candidatos/'+$routeParams.id+'/foto',
        file: file,
      }).progress(function(evt) {
        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
      }).success(function(data, status, headers, config) {
        console.log(data);
         $location.absUrl() == 'http://http://localhost:8080/Iguassu/app/#/candidatos/' + $routeParams.id;
        $location.url('/candidatos/' + $routeParams.id);
      }); 
    }
  };

    // $scope.uploadFile = function(){
    //   var file = $scope.myFile;
    //   console.log('file is ' + JSON.stringify(file) + ' ' + $routeParams.id);
    //   var uploadUrl = 'candidatos/'+$routeParams.id+'/foto';
    //   fileUpload.uploadFileToUrl(file, uploadUrl);
    // };
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
            headers: {'Content-Type': 'multipart/form-data'}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);
