'use strict';
/**
 * Created by Катерина on 14.05.2015.
 *
 * Service for manipulating with user's profile
 */

angular.module("flowertyApplication.profileModule").service("profileService", ['$http',
    function ($http) {

        var self = this;

        self.getProfile = function (successCallback, errorCallback) {

            $http({
                method: "get",
                url: "user/profile"
            })
                .success(successCallback)
                .error(function (data) {
                    errorCallback(data);
                });
        }
    }]);
