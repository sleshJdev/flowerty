'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller("UserDeleteController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
    $http({
        method: "get",
        url: "user/delete/" + $routeParams.id
    }).success(function(data, status, headers, config) {
    }).error(function(data, status, headers, config) {
        console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
    });
}]);
