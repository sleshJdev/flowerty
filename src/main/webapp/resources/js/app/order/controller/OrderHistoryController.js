'use strict';
/**
 * Created by Катерина on 05.05.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderHistoryController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {

    $scope.init = function(){
        $http({
            method: "get",
            url: "order/history/" + $routeParams.id
        }).success(function (data, status, headers, config) {
            $scope.changes = data;
        }).error(function (data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.init();
}]);
