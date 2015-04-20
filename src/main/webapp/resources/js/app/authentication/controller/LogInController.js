'use strict';
/**
 * Created by Rostislav on 05-Apr-15
 */

angular.module('flowertyApplication.authenticationModule').controller('LogInController', function ($scope, $http, $location, sessionService) {
    $scope.logIn = function () {
        sessionService.login($scope, $location);
    };
});