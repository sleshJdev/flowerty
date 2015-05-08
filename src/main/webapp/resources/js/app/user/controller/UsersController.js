'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.userModule").controller('UsersController', function ($scope, $http, $location, USER_MODULE_CONSTANTS) {

    $scope.users = {
        pagesCount: 1,
        currentPage: 1,
        limit: 3,
        limits: USER_MODULE_CONSTANTS.LIMITS_USERS,
        usersList: []
    };

    $scope.users.pageClass = function (pageNumber) {
        return pageNumber == $scope.users.currentPage ? 'active' : '';
    };

    $scope.users.getPage = function (pageNumber) {
        $scope.users.currentPage = pageNumber;
        $scope.users.getPageFromServer();
    };

    $scope.users.getPageFromServerChangeLimit = function () {
        $scope.users.pagesCount = 1;
        $scope.users.currentPage = 1;
        $scope.users.getPageFromServer();
    };

    $scope.users.deleteUsers = function () {
        var toDeleteIds = [];
        var user;
        for (var i = 0; i < $scope.users.usersList.length; i++) {
            user = $scope.users.usersList[i];
            if (user.checked) {
                toDeleteIds.push(user.id);
            }
        }
        if (toDeleteIds.length <= 0) {
            return true;
        }
        $http({
            method: "post",
            url: "user/delete",
            data: toDeleteIds
        }).success(function (data, status, headers, config) {
            $location.path("users");
        }).error(function (data, status, headers, config) {
            console.log("Exception details in UsersController.delete() : " + JSON.stringify({data: data}));
        });
    };

    $scope.users.getPageFromServer = function () {
        var request = $http({
            method: "get",
            url: "user/list/" + $scope.users.currentPage + '&' + $scope.users.limit
        });

        request.success(function (data, status, headers, config) {
            if (!data.content) {
                $location.path("login");
            } else {
                $scope.users.usersList = data.content;
                $scope.users.pagesCount = data.totalPages;
            }
        });

        request.error(function (data, status, headers, config) {
            console.log("get user list error");
        });
    };

    $scope.users.getPreviousPage = function () {
        if ($scope.users.currentPage > 1) {
            $scope.users.currentPage--;
            $scope.users.getPage($scope.users.currentPage);
        }
    };

    $scope.users.getNextPage = function () {
        if ($scope.users.currentPage < $scope.users.pagesCount) {
            $scope.users.currentPage++;
            $scope.users.getPage($scope.users.currentPage);
        }
    };

    $scope.users.getPagesCount = function () {
        return $scope.users.pagesCount;
    };

    $scope.init = function () {
        $scope.users.getPage(1);
        $scope.pagination.getNextPage = $scope.users.getNextPage;
        $scope.pagination.getPreviousPage = $scope.users.getPreviousPage;
        $scope.pagination.getPage = $scope.users.getPage;
        $scope.pagination.pageClass = $scope.users.pageClass;
        $scope.pagination.getPagesCount = $scope.users.getPagesCount;
    };

    $scope.init();
});