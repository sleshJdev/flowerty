/**
 * Created by Катерина on 03.04.2015.
 */

var APP_PATH = "resources/js/app/";
var AUTHENTICATION_MODULE_PATH = APP_PATH + "authentication/";

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

authenticationModule.factory('sessionService', function ($http) {
    var session = {};
    session.login = function (data, $scope, $location) {
        return $http.post("/login", "username=" + data.login +
        "&password=" + data.password, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function (data) {
            alert("login successful");
            localStorage.setItem("session", {});
            $scope.current.isLogged = true;
            $scope.current.user.login = $scope.account.login;
            $scope.current.errorLogin = false;
            $location.path("/");
        }, function (data) {
            alert("error logging in");
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
 * Created by Катерина on 19.03.2015.
 */

authenticationModule.controller('LogInController', function ($scope, $http, $location, sessionService) {

    $scope.logIn = function () {

        var logged = {
            login: $scope.login,
            password: $scope.password
        };

        console.log("user to log: " + JSON.stringify(logged));

        //var request = $http({
        //    method: "post",
        //    url: "login",
        //    data: {
        //        login: $scope.login,
        //        password: $scope.password
        //    }
        //});

        sessionService.login($scope.account, $scope, $location);

        // Just emulation
        //$scope.current.isLogged = true;
        //$scope.current.user.login = logged.login;
        //$scope.current.user.role = {name: 'ADMIN'};

        //request.success(function (data, status, headers, config) {
        //    console.log("User logged in: " + JSON.stringify({data: data}));
        //
        //    $scope.current.isLogged = true;
        //    //$rootScope.current.user = data.user;
        //    $scope.current.user.login = logged.login;
        //    $scope.current.user.role = {name: 'ADMIN'};
        //});
        //request.error(function (data, status, headers, config) {
        //    console.log("Exception details: " + JSON.stringify({data: data}));
        //});
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