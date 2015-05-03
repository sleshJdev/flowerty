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

    $scope.initItems = function(){
        var i;
        $scope.order.items = [];
        for(i = 0; i < $scope.current.basket.items.length; i++){
            var basketItem = $scope.current.basket.items[i];
            var orderItem = {};
            orderItem.goods = basketItem;
            orderItem.quantity = basketItem.count;
            $scope.order.items.push(orderItem);
        }
    };

    $scope.orderAction = {};

    $scope.orderAction.checkout = function(){
        console.log('Checkout: ' + JSON.stringify($scope.order));
        $scope.order.customer = $scope.search.customer.selected;
        $scope.order.receiver = $scope.search.receiver.selected;

        //  TODO: create a service
        $http({
            method: 'post',
            url: 'order/save',
            data: $scope.order
        }).success(function(data, status, headers, config){
            $location.path('/');
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
            $scope.order = data;
            $scope.initItems();
            $scope.order.cost = $scope.current.basket.info.fullCost;
            getDeliveryManagers();
            getOrderProcessors();

            //TODO: When added address field to a table, remove
            $scope.order.address = {};

            //  Makes the basket empty
            $scope.current.basket.reset();
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
            $scope.order.delivery = $scope.staff.deliveryManagers[0];
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
            $scope.order.staff = $scope.staff.processors[0];
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    $scope.init();

}]);