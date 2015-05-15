'use strict';

angular.module("flowertyApplication.orderModule").controller("ShowOrdersController", ["$scope", "$http", "$location", "orderSearchService", 'paginationService',
    function($scope, $http, $location, orderSearchService, paginationService) {

        $scope.orders = paginationService.getListBundle();
        $scope.pagination = paginationService.getPagination(orderSearchService.searchOrders);
        $scope.pagination.getPage(1);
    }]);