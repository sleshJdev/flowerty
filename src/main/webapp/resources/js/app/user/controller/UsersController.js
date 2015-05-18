'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller('UsersController', ['$scope', '$http', '$location', 'USER_MODULE_CONSTANTS', 'paginationService', 'userListService', 'userDeleteService', 'notificationService',
    function ($scope, $http, $location, USER_MODULE_CONSTANTS, paginationService, userListService, userDeleteService, notificationService) {

        $scope.users = paginationService.getListBundle();

        $scope.users.deleteUsers = function () {
            userDeleteService.deleteUsers(
                $scope.users.list,
                function (data) {
                    $location.path("users");
                },
                function (data) {
                    notificationService.notify('danger', 'Cannot delete selected users!');
                }
            );
        };

        $scope.init = function () {
            $scope.pagination = paginationService.getPagination(userListService.getUserList, true);
            $scope.pagination.getPage(1);
        };

        $scope.init();
    }]);