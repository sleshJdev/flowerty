'use strict';
/**
 * Created by Катерина on 22.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderAddController', ['$scope', '$http', '$location',
    'checkoutService', 'orderService', 'staffService', 'notificationService', '$localStorage', "VALIDATE_DATE",
    function($scope, $http, $location, checkoutService, orderService, staffService, notificationService, $localStorage, VALIDATE_DATE) {

        $scope.search = {
            customer: {
                enteredSurname: '',
                show: false,
                selected: {},
                loading : false
            },
            receiver: {
                enteredSurname: '',
                show: false,
                selected: {},
                loading : false
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
            console.log($scope.bundle.order);
            checkoutService.checkout($scope.bundle.order,
                function (data) {
                    console.log('Checkout order completed succesfully: ' + JSON.stringify($scope.bundle.order));

                    $scope.bundle.order = data;
                    $scope.bundle.order.deliveryDate = VALIDATE_DATE.correctFormat($scope.bundle.order.deliveryDate);
                    if($scope.bundle.order.id) {
                        notificationService.notify("success", "New order added!");
                        //  Makes the basket empty
                        $scope.current.resetBasket();
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

        //  initializes available managers for every job
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
                if(!$scope.current.basket.items.length){
                    $localStorage.cart ? $scope.current.basket = $localStorage.cart : $location.path("/");
                }
                orderService.initCartItems($scope.bundle.order, $scope.current.basket);
                prepareStaff();
            },
            function (data) {
                notificationService.notify("danger", "Error occured during creating an order");
            }
        );

    }]);
