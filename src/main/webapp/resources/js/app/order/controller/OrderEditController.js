'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.option = {
        edit: true
    };

    $scope.init = function(){
        $http({
            method: "get",
            url: "order/details/" + $routeParams.id
        }).success(function (data, status, headers, config) {
            $scope.bundle = data;
            $scope.order = $scope.bundle.order;
        }).error(function (data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.init();

    $scope.orderAction = {};

    $scope.orderAction.changeState = function (state) {
        $scope.order.state = state;
    };

    $scope.orderAction.save = function () {
        $http({
            method: "post",
            url: "order/save",
            data: $scope.order
        }).success(function (data, status, headers, config) {
            $location.path("users");
        }).error(function (data, status, headers, config) {
        });
    };

}]);

