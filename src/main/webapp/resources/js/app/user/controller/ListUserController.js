'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller('ListUserController', ['$scope', '$http', '$location', 'USER_MODULE_CONSTANTS', 'paginationService', 'userListService', 'userDeleteService', 'notificationService', 'stateSaverUserService',
    function ($scope, $http, $location, USER_MODULE_CONSTANTS, paginationService, userListService, userDeleteService, notificationService, stateSaverUserService) {

        function deleteUsers() {
            if (!$scope.bundle.state.checkeds.length) {
                notificationService.notify("warning", "Please select users to delete.");
                return;
            }
            userDeleteService.deleteUsers(
                $scope.bundle.state.checkeds,
                function (data) {
                    console.log("users delete successful");
                    notificationService.notify("success", "Users successfully removed!");
                    $scope.init();
                },
                function (data) {
                    console.log("users delete error. details: " + JSON.stringify(data));
                    notificationService.notify("danger", "Error during deleting users!");
                });
        }

        $scope.bundle = {
            users: {},
            state: stateSaverUserService.state,
            deleteUsers: deleteUsers
        };

        $scope.init = function () {
            $scope.bundle.users = paginationService.getListBundle();
            $scope.bundle.state.reset();
            $scope.pagination = paginationService.getPagination(userListService.getUserList, true);
            $scope.pagination.getPage(1);
        };

        $scope.init();
    }]);