'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.search = {
        customer : {
            enteredSurname : '',
            show : false,
            selected : {}
        },
        receiver : {
            enteredSurname : '',
            show : false,
            selected : {}
        }
    };

    $scope.staff = {
        processors : [],
        deliveryManagers : []
    };

    $scope.initItems = function(basket){
        var i;
        var basketItem;
        $scope.bundle.order.items = [];
        for(basketItem in basket){
            $scope.bundle.order.items.push(basket[basketItem]);
        }
    };

    $scope.orderAction = {};

    $scope.orderAction.checkout = function(){
        console.log('Checkout: ' + JSON.stringify($scope.bundle.order));
        $scope.bundle.order.customer = $scope.search.customer.selected;
        $scope.bundle.order.receiver = $scope.search.receiver.selected;

        //  TODO: create a service
        $http({
            method: 'post',
            url: 'order/save',
            data: $scope.bundle.order
        }).success(function(data, status, headers, config){

            //  Makes the basket empty
            $scope.current.resetBasket();
            $location.path("users");
        }).error(function(data, status, headers, config){
            console.log("Exception details in OrderAddController.save() : " + JSON.stringify({data: data}));
        });
    };

    $scope.init = function(){

        //  TODO: get it from factory

        $http({
            method: "get",
            url: "order/create/bundle"
        }).success(function(data, status, headers, config) {
            $scope.bundle = {
                order : data
            };
            $scope.initItems($scope.current.basket.items);
            $scope.bundle.order.cost = $scope.current.basket.info.fullCost;
            getDeliveryManagers();
            getOrderProcessors();
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    var getDeliveryManagers = function(){

        $http({
            method: "get",
            url: "users/role/delivery_manager"
        }).success(function(data, status, headers, config) {
            $scope.staff.deliveryManagers = data;
            $scope.bundle.order.delivery = $scope.staff.deliveryManagers[0];
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
            $scope.bundle.order.staff = $scope.staff.processors[0];
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    $scope.init();

}]);