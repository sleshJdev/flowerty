'use strict';
/**
 * Created by Катерина on 05.05.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderHistoryController', ['$scope', '$http', '$location', '$routeParams',
    'orderService', function($scope, $http, $location, $routeParams, orderService) {

    orderService.getHistory($routeParams.id,
        function (data) {
            $scope.changes = data;
        }
    );
}]);
