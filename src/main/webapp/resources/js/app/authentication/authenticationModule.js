
/**
 * Created by Катерина on 19.03.2015.
 */

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


authenticationModule.controller('LogInController', function($scope, $http) {

    $scope.logIn = function() {

        var request = $http({
            method: "post",
            url: "login",
            data: {
                login : $scope.login,
                password : $scope.password
            }
        });

        // Just emulation
        $scope.current.isLogged = true;
        $scope.current.user.login = $scope.login;
        $scope.current.user.role = {name : 'ADMIN'};

        request.success(function(data, status, headers, config) {
            console.log("User logged in: " + JSON.stringify({data: data}));

            $scope.current.isLogged = true;
            //$rootScope.current.user = data.user;
            $scope.current.user.login = $scope.login;
            $scope.current.user.role = {name : 'ADMIN'};
        });
        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.logOut = function() {

        var request = $http({
            method: "post",
            url: "login",
            data: {
                login : $scope.login,
                password : $scope.password
            }
        });

        // Just emulation
        $scope.current.isLogged = true;
        $scope.current.user.login = $scope.login;
        $scope.current.user.role = {name : 'ADMIN'};

        request.success(function(data, status, headers, config) {
            console.log("User logged in: " + JSON.stringify({data: data}));

            $scope.current.isLogged = true;
            //$rootScope.current.user = data.user;
            $scope.current.user.login = $scope.login;
            $scope.current.user.role = {name : 'ADMIN'};
        });
        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
        });
    };
});

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