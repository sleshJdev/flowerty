'use strict';
/**
 * Created by Катерина on 21.04.2015.
 */

angular.module("flowertyApplication.orderModule").controller('OrderEditController', 
			['$scope', '$http', '$location', '$routeParams', 'ORDER_MODULE_CONSTANTS', 'checkoutService', 'orderService', 'staffService', 'notificationService',
    function($scope, $http, $location, $routeParams, ORDER_MODULE_CONSTANTS, checkoutService, orderService, staffService, notificationService) {

        $scope.access = {
            canChangeStaff: $scope.current.user.role === 'ROLE_SUPERVISOR'
        };

        $scope.partial = {
            stateChange: ORDER_MODULE_CONSTANTS.ORDER_STATE_CHANGE_FORM
        };

        $scope.staff = {
            processors: [],
            deliveryManagers: []
        };

        $scope.temp = {
            newState: {},
            comment: ''
        };

        $scope.orderAction = {};

        $scope.orderAction.changeState = function (state) {
            $scope.temp.newState = state;
        };

        $scope.orderAction.save = function () {
            checkoutService.saveChanges(
                $scope.bundle,
                function (data) {
                    notificationService.notify('success', 'Changes saved successfully!');
                    $location.path('orders');
                },
                function (data) {
                    notificationService.notify('danger', 'Cannot apply changes!');
                }
            );
        };

        $scope.orderAction.saveStateChanges = function () {
            $scope.bundle.orderAltering = {
                state: $scope.temp.newState,
                comment: $scope.temp.comment
            };
            $scope.bundle.order.state = $scope.bundle.orderAltering.state;
        };

        orderService.getOrder($routeParams.id,
            function (orderEditBundle) {
                $scope.bundle = orderEditBundle;

                staffService.getStaffForRole('delivery_manager',
                    function (managers) {
                        $scope.staff.deliveryManagers = managers;
                        $scope.bundle.order.delivery = findInArrayById($scope.bundle.order.delivery, $scope.staff.deliveryManagers);
                    }
                );

                staffService.getStaffForRole('orders_processor',
                    function (data) {
                        $scope.staff.processors = data;
                        $scope.bundle.order.staff = findInArrayById($scope.bundle.order.staff, $scope.staff.processors);
                    }
                );
            },
            function (data) {
                notificationService.notify("danger", "Error occured during getting order info!")
            }
        );

        var findInArrayById = function (object, array) {
            if (!object || !array) {

                //  Because we don't want to loose our object
                return object;
            }
            var i;
            for (i = 0; i < array.length; i++) {
                if (array[i].id === object.id) {
                    return array[i];
                }
            }
            return object;
        };

    }]);
