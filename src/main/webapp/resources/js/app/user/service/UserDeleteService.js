'use strict';
/**
 * Created by Катерина on 14.05.2015.
 *
 * Service for deleting users
 */

angular.module("flowertyApplication.userModule").service("userDeleteService", ['$http',
    function($http) {

        var self = this;

        self.deleteUsers = function (usersList, successCallback, errorCallback) {
            var toDeleteIds = [];
            var user;
            for (var i = 0; i < usersList.length; i++) {
                user = usersList[i];
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
            })
                .success(successCallback)
                .error(function (data) {
                    console.log("Exception details in UsersController.delete() : " + JSON.stringify({data: data}));
                    errorCallback(data);
                });
        };

        self.deleteOne = function (id, successCallback, errorCallback) {
            $http({
                method: "get",
                url: "user/delete/" + id
            })
                .success(successCallback)
                .error(function (data, status, headers, config) {
                    console.log("Exception details when deleting user with id " + id + ": " + JSON.stringify({data: data}));//COMMENT HERE
                    errorCallback(data);
                });
        };
    }]);