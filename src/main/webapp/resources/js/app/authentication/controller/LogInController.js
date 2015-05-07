'use strict';

/**
 * Created by Rostislav on 05-Apr-15
 */

authenticationModule.controller('LogInController', function ($scope, $http, $location, AuthServerProvider) {
    if ($scope.current.isLogged) {
        $location.path("/");
        return;
    }
    $scope.logIn = function () {
        if ($scope.current.user.username && $scope.current.user.password) {
            AuthServerProvider.login($scope, $location);
        } else {
            $scope.current.user.password = undefined;
            $scope.current.errorLogin = true;
        }
    };
});