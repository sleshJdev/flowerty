'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */
userModule.controller("AddUserController", ['$scope', '$http', '$location', '$filter', 'USER_MODULE_CONSTANTS', 'userService', 'notificationService',
    function ($scope, $http, $location, $filter, USER_MODULE_CONSTANTS, userService, notificationService) {

        $scope.option = {
            title: USER_MODULE_CONSTANTS.PROCESS_TYPES.ADD.title,
            actionButton: USER_MODULE_CONSTANTS.PROCESS_TYPES.ADD.actionButton,
            edit: false
        };

        $scope.bundle = {
            user: {
                contact: null
            },
            roles: {},
            passwordConfirm: undefined
        };

        $scope.error = {
            emptyContact: false,
            emptyLogin: false,
            emptyRole: false,
            emptyPassword: false
        };

        $scope.search = {
            user: {
                enteredSurname: '',
                show: false,
                selected: {},
                loading : false
            }
        };

        userService.getRoles(
            function (data) {
                $scope.bundle.roles = data;
            }
        );

        $scope.save = function () {

            $scope.bundle.user.contact = $scope.search.user.selected;

            for (var i = 0; i < $scope.bundle.roles.length; ++i) {
                if ($scope.bundle.user.role.id === $scope.bundle.roles[i].id) {
                    $scope.bundle.user.role = $scope.bundle.roles[i];
                    break;
                }
            }


            userService.save(
                $scope.bundle.user,
                function (data) {
                    if (!data) {
                        notificationService.notify('danger', 'Login already exists!');
                    } else {
                        notificationService.notify('success', 'Successfully added this user!');
                        $location.path("users");
                    }
                },
                function (data) {
                    notificationService.notify('danger', 'Error occured during saving this user!');
                }
            );
        };
    }]);