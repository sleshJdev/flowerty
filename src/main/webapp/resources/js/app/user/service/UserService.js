'use strict';
/**
 * Created by Катерина on 14.05.2015.
 *
 * Service for manipulating with user
 */

angular.module("flowertyApplication.userModule").service("userService", ['$http',
    function($http) {

        var self = this;

        self.getUser = function (id, successCallback, errorCallback) {
            $http({
                method: "get",
                url: "user/details/" + id
            })
                .success(successCallback)
                .error(function (data) {
                    console.log("Exception details during getting user info: " +
                    JSON.stringify({data: data}));
                    errorCallback(data);
                })
        };

        self.save = function (user, successCallback, errorCallback) {

            $http({
                method: "post",
                url: "user/save",
                data: user
            })
                .success(successCallback)
                .error(function (data) {
                    errorCallback(data);
                });
        };

        self.getRoles = function (successCallback) {
            $http({
                method: "get",
                url: "user/roles"
            })
                .success(successCallback)
                .error(function (data) {
                    console.log("Exception details during getting roles: " + JSON.stringify({data: data}));
                });
        }

    }]);