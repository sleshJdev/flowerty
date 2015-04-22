'use strict';
/**
 * Created by Катерина on 19.03.2015
 */

var authenticationModule = angular.module('flowertyApplication.authenticationModule', ['ngRoute']);

authenticationModule.config(["$routeProvider", function ($routeProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: AUTHENTICATION_MODULE_PATH + 'partial/log-in-form.html',
            controller: 'LogInController'
        })
        .when('/signup', {
            templateUrl: AUTHENTICATION_MODULE_PATH + 'partial/sign-up-form.html',
            controller: 'SignUpController'
        });
}]);

/**
 * Created by Rostislav on 05-Apr-15
 */

authenticationModule.factory('sessionService', function ($http) {
    var session = {};
    session.login = function ($scope, $location) {
        console.log("login()");

        //return $http.post(
        //    "/login", {
        //        username : $scope.user.login,
        //        password : $scope.user.password,
        //        _spring_security_remember_me : !!$scope.rememberMe
        //    }, {
        //        headers: {'Content-Type': 'application/json'}
        //

        return $http.post(
            "login",
            "username=" + $scope.current.user.username +
            "&password=" + $scope.current.user.password +
            "&_spring_security_remember_me=" + !!$scope.rememberMe, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })
            .then(function (data) {
                $scope.current.user.password = undefined;
                session.setLoggedUser($scope);
                $location.path("/");
            }, function (data) {
                $scope.current.isLogged = false;
                $scope.current.user.password = undefined;
                $scope.current.errorLogin = true;
            });
    };
    session.logout = function ($scope, $location) {
        $http.post('logout', {})
            .success(function () {
                $scope.current.isLogged = false;
                $scope.current.user = {};
            });
    };
    session.setLoggedUser = function ($scope) {
        $http({
            method: "get",
            url: "login"
        }).success(function (data, status, headers, config) {
            if (data) {
                $scope.current.isLogged = true;
                $scope.current.user.username = data.username;
                $scope.current.user.role = data.authorities[0].authority;
                $scope.current.errorLogin = false;
            }
        });
    };
    return session;
});

/**
 * Created by Rostislav on 05-Apr-15
 */

authenticationModule.controller('LogInController', function ($scope, $http, $location, sessionService) {
    if ($scope.current.isLogged) {
        $location.path("/");
        return;
    }
    $scope.logIn = function () {
        if ($scope.current.user.username && $scope.current.user.password) {
            sessionService.login($scope, $location);
        } else {
            $scope.current.user.password = undefined;
            $scope.current.errorLogin = true;
        }
    };
});

/**
 * Created by Катерина on 03.04.2015.
 */

authenticationModule.controller('SignUpController', function ($scope, $http) {

    $scope.signUp = function () {

        var request = $http({
            method: "post",
            url: "signup",
            data: {}
        });
        request.success(function (data, status, headers, config) {

        });
        request.error(function (data, status, headers, config) {

        });
    };
});