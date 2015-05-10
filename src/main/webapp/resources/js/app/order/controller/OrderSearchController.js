'use strict';

angular.module("flowertyApplication.orderModule").controller("OrderSearchController", ["$scope", "$http", "$location", "orderListService",
    'orderSearchService',
    function($scope, $http, $location, orderListService, orderSearchService){

        $scope.bundle = {
            actions: [],
            order: {}
        };

        $scope.bundle.actions.search = function(){
            orderSearchService.setOrderToSearch($scope.bundle.order);
            $location.path("search-order-results");
        };
    }]);