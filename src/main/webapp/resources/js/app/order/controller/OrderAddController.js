'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.option = {
        edit : false
    };

    $scope.order = {
        customer : {},
        description : '',
        manager : $scope.current.user,
        cost : $scope.current.basket.info.fullCost,
        staff : {},
        delivery : {},
        receiver : {},
        state :
        {
            description : 'NEW'
        },
        items: $scope.current.basket.goods
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

    $scope.order.checkout = function(){
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
/*

        $scope.staff.processors = [
            {
                login : 'Nick Manchkin'
            },
            {
                login : 'Adam Lambert'
            },
            {
                login : 'Nikole Clark'
            }
        ];
        $scope.order.staff = $scope.staff.processors[0];
        $scope.staff.deliveryManagers = [
            {
                login : 'Luk Kolm'
            },
            {
                login : 'Mark Sherzinger'
            },
            {
                login : 'Dan Poltrat'
            }
        ];

*/

        $http({
            method: "get",
            url: "tempsearch/user/list/3"
        }).success(function(data, status, headers, config) {
            $scope.order.manager = data.content[0];
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });

        $http({
            method: "get",
            url: "tempsearch/user/list/1"
        }).success(function(data, status, headers, config) {
            $scope.staff.processors = data.content;
            $scope.order.staff = $scope.staff.processors[0];
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });

        $http({
            method: "get",
            url: "tempsearch/user/list/2"
        }).success(function(data, status, headers, config) {
            $scope.staff.deliveryManagers = data.content;
            $scope.order.delivery = $scope.staff.deliveryManagers[0];
        }).error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
            $location.path("add-order");
        });

    };

    $scope.init();

}]);