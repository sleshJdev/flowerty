'use strict';

angular.module("flowertyApplication.orderModule").controller("ShowOrdersController", ["$scope", "$http", "$location", "orderListService",
    function($scope, $http, $location, orderListService) {
        $scope.init = function () {
            //if we got here, we need all contacts
            orderListService.setList(null);
            $location.path("orders");
        };
        $scope.init();
    }]);