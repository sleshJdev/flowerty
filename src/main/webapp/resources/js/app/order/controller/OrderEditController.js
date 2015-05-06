'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.access = {
        canChangeStaff : $scope.current.user.role === 'ROLE_SUPERVISOR'
    };

    $scope.init = function(){
        $http({
            method: "get",
            url: "order/details/" + $routeParams.id
        }).success(function (data, status, headers, config) {
            $scope.bundle = data;
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
        $scope.temp.state = state;
    };

    $scope.orderAction.save = function () {
        $http({
            method: "post",
            url: "order/change/save",
            data: $scope.bundle
        }).success(function (data, status, headers, config) {
            $location.path("users");
        }).error(function (data, status, headers, config) {
        });
    };

    $scope.orderAction.saveStateChanges = function () {
        $scope.orderAltering = {
            state : $scope.temp.state,
            comment : $scope.temp.comment
        };
        $scope.bundle.order.state = $scope.orderAltering.state;
    };

    $scope.temp = {
        state : {},
        comment : ''
    };
}]);
