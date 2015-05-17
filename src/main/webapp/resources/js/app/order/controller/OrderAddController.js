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
                    $scope.bundle.order = data;
                    if($scope.bundle.order.id) {
                        notificationService.notify("success", "New order added!");
                        $location.path('edit-order/' + $scope.bundle.order.id);
                    }
                    else {
                        if (checkoutService.canAprooveOrder($scope.bundle.order.items)) {
                            notificationService.notify("warning", "Some flowers are not available now. Please, aproove.");
                            prepareStaff();
                        }
                        else{
                            notificationService.notify("info", "Sorry, but all the items you chose are not available at our warehouse. You can choose other flowers instead.");
                            $location.path("/");
                        }
                    }
                },
                function (data) {
                    notificationService.notify("danger", "Checking out failed!");
                });
        };

        //  Initializes available managers for every job
        var prepareStaff = function () {
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
        };

        orderService.getPreparedOrderCreateBundle(
            function (order) {
                $scope.bundle = {
                    order: order
                };
                orderService.initCartItems($scope.bundle.order, $scope.current.basket);
                prepareStaff();
            },
            function (data) {
                notificationService.notify("danger", "Error occured during creating an order");
            }
        );

    }]);
