'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller("UserEditController", ['$scope', '$http', '$location', '$routeParams', 'USER_MODULE_CONSTANTS',
    function ($scope, $http, $location, $routeParams, USER_MODULE_CONSTANTS) {

        $scope.option = {
            title: USER_MODULE_CONSTANTS.PROCESS_TYPES.EDIT.title,
            actionButton: USER_MODULE_CONSTANTS.PROCESS_TYPES.EDIT.actionButton,
            edit: true
        };

        $http({
            method: "get",
            url: "user/details/" + $routeParams.id
        }).success(function (data, status, headers, config) {
            $scope.bundle = data;
        }).error(function (data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
        });

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

            $http({
                method: "post",
                url: "user/save",
                data: $scope.bundle.user
            }).success(function (data, status, headers, config) {
                $location.path("users");
            }).error(function (data, status, headers, config) {
            });
        };
    }]);

