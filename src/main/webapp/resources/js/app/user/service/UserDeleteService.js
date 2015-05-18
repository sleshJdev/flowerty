'use strict';
/**
 * Created by Катерина on 14.05.2015.
 *
 * Service for deleting users
 */

angular.module("flowertyApplication.userModule").service("userDeleteService", ['$http',
    function($http) {

        var self = this;

        self.deleteUsers = function (list, successCallback, errorCallback) {
            $http({
                method: "post",
                url: "user/remove",
                data: angular.toJson(list),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "text/plain"
                }
            })
                .success(successCallback)
                .error(errorCallback);
        };
    }]);