'use strict';

angular.module("flowertyApplication.orderModule").controller("OrderSearchResultController", ["$scope", "$http", "$location", "orderSearchService", 'paginationService',
    function($scope, $http, $location, orderSearchService, paginationService) {

        $scope.orders = paginationService.getListBundle();
        $scope.pagination = paginationService.getPagination(orderSearchService.searchOrders, true);
        $scope.pagination.getPage(1);
    }]);