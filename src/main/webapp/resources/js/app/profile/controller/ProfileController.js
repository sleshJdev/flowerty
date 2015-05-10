'use strict';

/**
 * Created by Rostislav on 01-May-15.
 */

angular.module("flowertyApplication.profileModule")

    .controller("ProfileController", ["$scope", "$http", "$location", "PROFILE_MODULE_CONSTANTS",
        function ($scope, $http, $location, PROFILE_MODULE_CONSTANTS) {

            if (!$scope.current.isLogged) {
                $location.path("/");
                return;
            }

            $scope.profile = {
                user: {},
                phoneListTemplate: PROFILE_MODULE_CONSTANTS.PHONES
            };
//TODO: service
            var request = $http({
                method: "get",
                url: "user/profile"
            });

            request.success(function (data, status, headers, config) {
                $scope.profile.user = data;
            });

            request.error(function (data, status, headers, config) {
            });

        }]);