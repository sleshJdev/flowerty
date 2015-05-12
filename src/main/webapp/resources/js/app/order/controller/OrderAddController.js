'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location',
    'checkoutService', 'orderService', 'staffService', 'notificationService',
    function($scope, $http, $location, checkoutService, orderService, staffService, notificationService) {

        $scope.search = {
            customer: {
                enteredSurname: '',
                show: false,
                selected: {}
            },
            receiver: {
                enteredSurname: '',
                show: false,
                selected: {}
            }
        };

        $scope.staff = {
            processors: [],
            deliveryManagers: []
        };

        $scope.orderAction = {};

        $scope.orderAction.checkout = function () {

            $scope.bundle.order.customer = $scope.search.customer.selected;
            $scope.bundle.order.receiver = $scope.search.receiver.selected;

            checkoutService.checkout($scope.bundle.order,
                function (data) {
                    console.log('Checkout order completed succesfully: ' + JSON.stringify($scope.bundle.order));

                    //  Makes the basket empty
                    $scope.current.resetBasket();
                    notificationService.notify("success", "New order added!");
                    $location.path('/orders');
                },
                function (data) {
                    console.log('Cannot checkout order: ' + JSON.stringify($scope.bundle.order) +
                    '\nException details : ' + JSON.stringify({data: data}));
                    notificationService.notify("danger", "Checking out failed!");
                    $location.path('/add-order');
                });
        };

        orderService.getPreparedOrderCreateBundle(
            function (order) {
                $scope.bundle = {
                    order: order
                };
                orderService.initCartItems($scope.bundle.order, $scope.current.basket);
                staffService.getStaffForRole('delivery_manager',
                    function (data) {
                        $scope.staff.deliveryManagers = data;
                        $scope.bundle.order.delivery = $scope.staff.deliveryManagers[0];
                    }
                );
                staffService.getStaffForRole('orders_processor',
                    function (data) {
                        $scope.staff.processors = data;
                        $scope.bundle.order.staff = $scope.staff.processors[0];
                    }
                )
            }
        );

    }]);
