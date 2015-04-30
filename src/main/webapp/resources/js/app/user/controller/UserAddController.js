'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

userModule.controller("UserAddController", ['$scope', '$http', '$location', '$filter', 'USER_MODULE_CONSTANTS',
    function ($scope, $http, $location, $filter, USER_MODULE_CONSTANTS) {

        $scope.option = {
            title: USER_MODULE_CONSTANTS.PROCESS_TYPES.ADD.title,
            actionButton: USER_MODULE_CONSTANTS.PROCESS_TYPES.ADD.actionButton,
            edit: false
        };

        $scope.bundle = {
            user: {
                contact: null
            },
            roles: {}
        };

        $scope.search = {
            user: {
                enteredSurname: '',
                show: false,
                selected: {}
            }
        };

        $http({
            method: "get",
            url: "user/roles"
        }).success(function (data, status, headers, config) {
            $scope.bundle.roles = data;
        }).error(function (data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
        });

        $scope.save = function () {
            $scope.bundle.user.contact = $scope.search.selected;

            for (var i = 0; i < $scope.bundle.roles.length; ++i) {
                if ($scope.bundle.user.role.id === $scope.bundle.roles[i].id) {
                    $scope.bundle.user.role = $scope.bundle.roles[i];
                    break;
                }
            }

            $http({
                method: "post",
                url: "user/add",
                data: $scope.bundle.user
            }).success(function (data, status, headers, config) {
                $location.path("users");
            }).error(function (data, status, headers, config) {
                console.log("Exception details in UserAddController.save() : " + JSON.stringify({data: data}));
            });
        };
    }]);