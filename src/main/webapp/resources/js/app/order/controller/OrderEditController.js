'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', ['$scope', '$http', '$location', '$routeParams', 'ORDER_MODULE_CONSTANTS', function($scope, $http, $location, $routeParams, ORDER_MODULE_CONSTANTS) {

    $scope.access = {
        canChangeStaff : $scope.current.user.role === 'ROLE_SUPERVISOR'
    };

    $scope.partial = {
        stateChange : ORDER_MODULE_CONSTANTS.ORDER_STATE_CHANGE_FORM
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

    var findInArrayById = function(object, array){
        if(!object || !array){

            //  Because we don't want to loose our object
            return object;
        }
        var i;
        for(i = 0; i < array.length; i++){
            if(array[i].id === object.id){
                return array[i];
            }
        }
        return object;
    };

    var getDeliveryManagers = function(){

        $http({
            method: "get",
            url: "users/role/delivery_manager"
        }).success(function(data, status, headers, config) {
            $scope.staff.deliveryManagers = data;
            $scope.bundle.order.delivery = findInArrayById($scope.bundle.order.delivery, $scope.staff.deliveryManagers);
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
            $scope.bundle.order.staff = findInArrayById($scope.bundle.order.staff, $scope.staff.processors);
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    $scope.init();

    $scope.orderAction = {};

    $scope.orderAction.changeState = function (state) {
        $scope.temp.newState = state;
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
        $scope.bundle.orderAltering = {
            state : $scope.temp.newState,
            comment : $scope.temp.comment
        };
        $scope.bundle.order.state = $scope.bundle.orderAltering.state;
    };

    $scope.temp = {
        newState : {},
        comment : ''
    };
}]);
