'use strict';

/**
 * Created by Rostislav on 01-May-15.
 */

angular.module("flowertyApplication.profileModule")

    .controller("ProfileController", ["$scope", "$http", "$location", "PROFILE_MODULE_CONSTANTS", 'profileService', 'financialService', 'notificationService',
                             function ($scope, $http, $location, PROFILE_MODULE_CONSTANTS, profileService, financialService, notificationService) {

            if (!$scope.current.isLogged) {
                $location.path("/");
                return;
            }

            $scope.profile = {
                user: {},
                fullRole: "",
                phoneListTemplate: PROFILE_MODULE_CONSTANTS.PHONES
            };
            
            $scope.report = {
            		value: null,
            		action: {}
            };
            
            $scope.report.action = function(){
            	financialService.getFinancialReport(
            			function(data){
            				$scope.report.value = data;
            			},
            			function(data){
            				$scope.report.value = null;
            			}
            	);
            }

            profileService.getProfile(
                function (data) {
                    $scope.profile.user = data;
                    $scope.profile = "ROLE_" + $scope.profile.user.role.name;
                },
                function (data) {
                    notificationService.notify('danger', 'Cannot get your profile info!');
                }
            );

        }]);