'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.order = {
        customer : {},
        description : '',
        manager : {},
        cost : 0,
        processor : {},
        deliveryManager : {},
        reciever : {},
        state : {},
        bascket : []
    };
}]);

