'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller("UserEditController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.option = {
        edit : true
    };

    $http({
        method: "get",
        url: "user/details/" + $routeParams.id
    }).success(function(data, status, headers, config) {
        $scope.bundle = data;
    }).error(function(data, status, headers, config) {
        console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
    });

    $scope.save = function() {
        $http({
            method: "post",
            url: "user/save",
            data: $scope.bundle.user
        }).success(function(data, status, headers, config) {
            $location.path("users");
        }).error(function(data, status, headers, config) {
        });
    };
}]);

