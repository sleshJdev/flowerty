'use strict';
/**
 * Created by Катерина on 26.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrdersController', ['$scope', '$http', '$location', '$filter', 'orderListService', function($scope, $http, $location, $filter, orderListService) {

    $scope.orders = {
        pages : [],
        pagesCount : 1,
        currentPage : 1,
        ordersList : []
    };

    $scope.orders.pageClass = function(pageNumber){
        return pageNumber == $scope.orders.currentPage ? 'active' : '';
    };

    $scope.orders.getPage = function(pageNumber){
        $scope.orders.currentPage = pageNumber;
        $scope.orders.getPageFromServer();
    };

    $scope.orders.getPageFromServer = function(){
        orderListService.getOrderList($scope.orders.currentPage, undefined,
            function(data) {
                if (!data.content) {
                    $location.path("login");
                } else {
                    $scope.orders.ordersList = data.content;
                    $scope.orders.pagesCount = data.totalPages;
                }
            }
        );
    };

    $scope.orders.getPreviousPage = function(){
        if($scope.orders.currentPage !== 1){
            $scope.orders.currentPage--;
        }
        $scope.orders.getPage($scope.orders.currentPage);
    };

    $scope.orders.getNextPage = function(){
        if($scope.orders.currentPage !== $scope.orders.pagesCount){
            $scope.orders.currentPage++;
        }
        $scope.orders.getPage($scope.orders.currentPage);
    };

    $scope.orders.getPagesCount = function(){
        return $scope.orders.pagesCount;
    };

    $scope.init = function () {
        $scope.orders.getPage(1);
        $scope.pagination.getNextPage = $scope.orders.getNextPage;
        $scope.pagination.getPreviousPage = $scope.orders.getPreviousPage;
        $scope.pagination.getPage = $scope.orders.getPage;
        $scope.pagination.pageClass = $scope.orders.pageClass;
        $scope.pagination.getPagesCount = $scope.orders.getPagesCount;
    };

    $scope.init();
}]);