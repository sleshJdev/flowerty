'use strict';

angular.module("flowertyApplication.orderModule").controller("OrderSearchController", ["$scope", "$http", "$location", "orderListService", "CONSTANTS",
    function($scope, $http, $location, orderListService, CONSTANTS){
        $scope.bundle = {
            actions: [],
            order: {}
        };
        $scope.bundle.actions.search = function(user){
            $http({
                method: "post",
                url: "order/search",
                data: $scope.bundle.order
            }).success(function(data, status, headers, config) {
                    console.log("search order success:" + JSON.stringify($scope.bundle.order));//REMOVE_COMMENT
                    orderListService.setList(data);
                    $location.path("orders");
                }).error(function(data, status, headers, config) {
                    console.log("error search order. details: " + JSON.stringify(data))//REMOVE_COMMENT
                });
        };

    }]);