
/**
 * Created by Катерина on 19.03.2015.
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
 * Created by Rostislav on 05-Apr-15.
 */

authenticationModule.factory('sessionService', function ($http) {
    var session = {};
    session.login = function (data, $scope, $location) {
        return $http.post("/login", "username=" + data.login +
        "&password=" + data.password, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function (data) {
            localStorage.setItem("session", {});
            $scope.current.isLogged = true;
            $scope.current.user.login = $scope.user.login;
            $scope.current.errorLogin = false;
            $location.path("/");
        }, function (data) {
            $scope.current.isLogged = false;
            $scope.current.errorLogin = true;
            $location.path("/login");
        });
    };
    session.logout = function () {
        localStorage.removeItem("session");
    };
    session.isLoggedIn = function () {
        return localStorage.getItem("session") !== null;
    };
    return session;
});

/**
 * Created by Rostislav on 05-Apr-15.
 */

authenticationModule.controller('LogInController', function ($scope, $http, $location, sessionService) {

    $scope.logIn = function () {

        var logged = {
            login: $scope.login,
            password: $scope.password
        };

        console.log("user to log: " + JSON.stringify(logged));

        sessionService.login($scope.user, $scope, $location);
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