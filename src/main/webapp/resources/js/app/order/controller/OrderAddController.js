'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.order = {
        customer : {},
        description : '',
        manager : $scope.current.user,
        cost : $scope.current.basket.info.fullCost,
        processor : {},
        deliveryManager : {},
        receiver : {},
        state : 'New',
        basket : $scope.current.basket
    };

    $scope.search = {
        customer : {
            enteredSurname : '',
            show : false,
            selected : {}
        },
        receiver : {
            enteredSurname : '',
            show : false,
            selected : {}
        }
    };

    $scope.staff = {
        processors : [],
        deliveryManagers : []
    };

    $scope.order.checkout = function(){
        //  TODO: checkout logic
        console.log('Checkout: ' + JSON.stringify($scope.order));
        $scope.order.customer = $scope.search.customer.selected;
        $scope.order.receiver = $scope.search.receiver.selected;
    };

    $scope.init = function(){
        //  TODO: get it from factory
        $scope.staff.processors = [
            {
                login : 'Nick Manchkin'
            },
            {
                login : 'Adam Lambert'
            },
            {
                login : 'Nikole Clark'
            }
        ];
        $scope.order.processor = $scope.staff.processors[0];
        $scope.staff.deliveryManagers = [
            {
                login : 'Luk Kolm'
            },
            {
                login : 'Mark Sherzinger'
            },
            {
                login : 'Dan Poltrat'
            }
        ];
        $scope.order.deliveryManager = $scope.staff.deliveryManagers[0];

    };

    $scope.init();

}]);