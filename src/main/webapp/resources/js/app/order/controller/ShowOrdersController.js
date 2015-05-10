'use strict';

angular.module("flowertyApplication.orderModule").controller("ShowOrdersController", ["$scope", "$http", "$location", "orderSearchService", 'orderPaginationService',
    function($scope, $http, $location, orderSearchService, orderPaginationService) {

        $scope.orders = orderPaginationService.getOrdersListBundle();

        $scope.init = function () {
            $scope.pagination = orderPaginationService.getPagination(orderSearchService.searchOrders);
            $scope.pagination.getPage(1);
        };

        $scope.init();
    }]);