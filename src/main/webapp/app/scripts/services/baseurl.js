'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.BaseUrl
 * @description
 * # BaseUrl
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp')
  .factory('BaseUrl', function($location) {
     return 'http://' + $location.host() + ':8080/Iguassu' ;
   });