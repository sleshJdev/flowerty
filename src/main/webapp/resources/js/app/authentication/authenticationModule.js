/**
 * Created by Катерина on 03.04.2015.
 */

var APP_PATH = "resources/js/app/";
var AUTHENTICATION_MODULE_PATH = APP_PATH + "authentication/";

var authenticationModule = angular.module("flowertyApplication.authenticationModule", ['ngRoute']);

authenticationModule.config(["$routeProvider", function($routeProvider) {
    $routeProvider.
        when('/login', {
            templateUrl: AUTHENTICATION_MODULE_PATH + 'partial/log-in-form.html',
            controller: 'LogInController'
        }).
        when('/signup', {
            templateUrl: AUTHENTICATION_MODULE_PATH + 'partial/sign-up-form.html',
            controller: 'SignUpController'
        });
}]);

/**
 * Created by Катерина on 19.03.2015.
 */

authenticationModule.controller('LogInController', function($scope, $http) {

    $scope.login = '';
    $scope.password = '';

    $scope.logIn = function() {

        var logged = {
            login : $scope.login,
            password : $scope.password
        };

        var request = $http({
            method: "post",
            url: "login",
            data: {
                loggedInUser: logged
            }
        });

        // Just emulation
        $scope.current.isLogged = true;
        $scope.current.user.login = logged.login;
        $scope.current.user.role = {name : 'ADMIN'};

        request.success(function(data, status, headers, config) {
            console.log("User logged in: " + JSON.stringify({data: data}));

            $scope.current.isLogged = true;
            //$rootScope.current.user = data.user;
            $scope.current.user.login = logged.login;
            $scope.current.user.role = {name : 'ADMIN'};
        });
        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.logOut = function() {

        var logged = {
            login : $scope.login,
            password : $scope.password
        };

        var request = $http({
            method: "post",
            url: "login",
            data: {
                loggedInUser: logged
            }
        });

        // Just emulation
        $scope.current.isLogged = true;
        $scope.current.user.login = logged.login;
        $scope.current.user.role = {name : 'ADMIN'};

        request.success(function(data, status, headers, config) {
            console.log("User logged in: " + JSON.stringify({data: data}));

            $scope.current.isLogged = true;
            //$rootScope.current.user = data.user;
            $scope.current.user.login = logged.login;
            $scope.current.user.role = {name : 'ADMIN'};
        });
        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };
});

/**
 * Created by Катерина on 03.04.2015.
 */

authenticationModule.controller('SignUpController', function($scope, $http) {

    $scope.signUp = function() {

        var request = $http({
            method: "post",
            url: "signup",
            data: {

            }
        });
        request.success(function(data, status, headers, config) {

        });
        request.error(function(data, status, headers, config) {

        });
    };
});