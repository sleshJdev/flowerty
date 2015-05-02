'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.option = {
        edit : false
    };

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
            orderItem.flower = basketItem;
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
            $location.path('');
        }).error(function(data, status, headers, config){
            console.log("Exception details in OrderAddController.save() : " + JSON.stringify({data: data}));
        });
    };

    $scope.init = function(){

        //  TODO: get it from factory


        //TODO: search in users by role "ORDERS_PROCESSOR"
        $http({
            method: "get",
            url: "tempsearch/user/list/1"
        }).success(function(data, status, headers, config) {
            $scope.staff.processors = data.content;
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });


        //TODO: search in users by role "DELIVERY_MANAGER"
        $http({
            method: "get",
            url: "tempsearch/user/list/1"
        }).success(function(data, status, headers, config) {
            $scope.staff.deliveryManagers = data.content;
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });

        $http({
            method: "get",
            url: "order/create/bundle"
        }).success(function(data, status, headers, config) {
            $scope.order = data;
            $scope.initItems();
            $scope.order.cost = $scope.current.basket.info.fullCost;
            $scope.order.staff = $scope.staff.processors[0];
            $scope.order.delivery = $scope.staff.deliveryManagers[0];

            //  Makes the basket empty
            $scope.current.basket.reset();
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });
    };

    $scope.init();

}]);