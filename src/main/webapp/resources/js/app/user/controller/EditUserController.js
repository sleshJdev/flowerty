'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller("EditUserController", ['$scope', '$http', '$location', '$routeParams', 'USER_MODULE_CONSTANTS', 'userService', 'notificationService',
    function ($scope, $http, $location, $routeParams, USER_MODULE_CONSTANTS, userService, notificationService) {

        $scope.option = {
            title: USER_MODULE_CONSTANTS.PROCESS_TYPES.EDIT.title,
            actionButton: USER_MODULE_CONSTANTS.PROCESS_TYPES.EDIT.actionButton,
            edit: true
        };

        userService.getUser(
            $routeParams.id,
            function (data) {
                $scope.bundle = data;
            },
            function (data) {
                notificationService.notify('danger', 'Error occured during getting info about this user!');
            }
        );

        $scope.save = function () {

            if ($scope.bundle.user.password != $scope.bundle.passwordConfirm) {
                return;
            }

            for (var i = 0; i < $scope.bundle.roles.length; ++i) {
                if ($scope.bundle.user.role.id === $scope.bundle.roles[i].id) {
                    $scope.bundle.user.role = $scope.bundle.roles[i];
                    break;
                }
            }

            userService.save(
                $scope.bundle.user,
                function (data) {
                    notificationService.notify('success', 'Successfully saved this user!');
                    $location.path("users");
                },
                function (data) {
                    notificationService.notify('danger', 'Error occured during saving this user!');
                }
            );
        };
    }]);

