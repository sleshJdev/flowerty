'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location', 'checkoutService', function($scope, $http, $location, checkoutService) {

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
        $scope.bundle.order.customer = $scope.search.customer.selected;
        $scope.bundle.order.receiver = $scope.search.receiver.selected;

        checkoutService.checkout($scope.bundle.order,
            function(data, status, headers, config){
                console.log('Checkout order completed succesfully: ' + JSON.stringify($scope.bundle.order));

                //  Makes the basket empty
                $scope.current.resetBasket();
                $location.path('orders');
                alert('success!!!');
            },
            function(data, status, headers, config){
                console.log('Cannot checkout order: ' + JSON.stringify($scope.bundle.order) +
                '\nException details : ' + JSON.stringify({data: data}));
                alert('error!!!');
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