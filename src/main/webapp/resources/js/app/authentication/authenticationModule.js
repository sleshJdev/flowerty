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

        return $http.post(
            "login",
            "username=" + $scope.user.login +
            "&password=" + $scope.user.password +
            "&_spring_security_remember_me=" + !!$scope.rememberMe, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}

            }).then(function (data) {
                session.setLoggedUser($scope);
                $location.path("/");
            }, function (data) {
                $scope.current.isLogged = false;
                $scope.current.user = {};
                $scope.current.errorLogin = true;
            });
    };
    session.logout = function () {
        //localStorage.removeItem("session");
    };
    session.setLoggedUser = function ($scope) {
        $http({
            method: "get",
            url: "login"
        }).success(function(data, status, headers, config) {
            if (data) {
                $scope.current.isLogged = true;
                $scope.current.user.name = data.username;
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
    $scope.logIn = function () {
        sessionService.login($scope, $location);
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