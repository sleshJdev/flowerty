'use strict';

/**
 * Created by Rostislav on 01-May-15.
 */

angular.module("flowertyApplication.profileModule")

    .controller("ProfileController", ["$scope", "$http", "$location", "PROFILE_MODULE_CONSTANTS", 'profileService', 'notificationService',
        function ($scope, $http, $location, PROFILE_MODULE_CONSTANTS, profileService, notificationService) {

            if (!$scope.current.isLogged) {
                $location.path("/");
                return;
            }

            $scope.profile = {
                user: {},
                phoneListTemplate: PROFILE_MODULE_CONSTANTS.PHONES
            };

            profileService.getProfile(
                function (data) {
                    $scope.profile.user = data;
                },
                function (data) {
                    notificationService.notify('danger', 'Cannot get your profile info!');
                }
            );

        }]);