/**
 * Created by Катерина on 03.04.2015.
 */

var APP_PATH = "resources/js/app/";
var USER_MODULE_PATH = APP_PATH + "login/";

var loginModule = angular.module("flowertyApplication.loginModule", ['ngRoute']);

userModule.config(["$routeProvider", function($routeProvider) {
    $routeProvider.
        when('/login', {
            templateUrl: USER_MODULE_PATH + 'partial/log-in-form.html',
            controller: 'LogInController'
        });
}]);

/**
 * Created by Катерина on 19.03.2015.
 */

loginModule.controller('LogInController', function($scope, $http) {

    $scope.login = '';
    $scope.password = '';

    $scope.logIn = function() {

        var logged = {
            'login' : $scope.login,
            'password' : $scope.password
        };

        var request = $http({
            method: "post",
            url: "login",
            data: {
                loggedInUser: logged
            }
        });
        request.success(function(data, status, headers, config) {
            alert( "User logged in: " + JSON.stringify(data));
        });
        request.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });
    };
});