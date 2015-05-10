'use strict';
/**
 * Created by Катерина on 26.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrdersController', ['$scope', '$http', '$location', '$filter', 'orderListService', 'orderPaginationService',
    function($scope, $http, $location, $filter, orderListService, orderPaginationService) {

    $scope.orders = orderPaginationService.getOrdersListBundle();

    $scope.init = function () {
        $scope.pagination = orderPaginationService.getPagination(orderListService.getOrderList);
        $scope.pagination.getPage(1);
    };

    $scope.init();
}]);