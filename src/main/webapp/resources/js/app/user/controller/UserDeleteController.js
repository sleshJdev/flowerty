'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */
//TODO: service
angular.module("flowertyApplication.userModule").controller("UserDeleteController", ['$scope', '$http', '$location', '$routeParams', 'userDeleteService', 'notificationService',
    function($scope, $http, $location, $routeParams, userDeleteService, notificationService) {

        userDeleteService.deleteOne(
            $routeParams.id,
            function (data) {
                notificationService.notify('success', 'Successfully removed this user!');
            },
            function (data, status, headers, config) {
                notificationService.notify('danger', 'Error during removing this user!');
            }
        );
    }]);
