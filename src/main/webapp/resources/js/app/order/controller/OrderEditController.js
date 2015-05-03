'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.init = function(){
        $http({
            method: "get",
            url: "order/details/" + $routeParams.id
        }).success(function (data, status, headers, config) {
            $scope.bundle = data;
            $scope.order = $scope.bundle.order;
            getDeliveryManagers();
            getOrderProcessors();
        }).error(function (data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.staff = {
        processors : [],
        deliveryManagers : []
    };

    var getDeliveryManagers = function(){

        $http({
            method: "get",
            url: "users/role/delivery_manager"
        }).success(function(data, status, headers, config) {
            $scope.staff.deliveryManagers = data;
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    var getOrderProcessors = function(){

        $http({
            method: "get",
            url: "users/role/orders_processor"
        }).success(function(data, status, headers, config) {
            $scope.staff.processors = data;
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
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
