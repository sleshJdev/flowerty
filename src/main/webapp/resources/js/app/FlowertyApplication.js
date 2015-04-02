'use strict';

var APP_PATH = "resources/js/app/";
var USER_MODULE_PATH = APP_PATH + "UserModule/";

console.log("APP_PATH: " + APP_PATH);
console.log("USER_MODULE_PATH: " + USER_MODULE_PATH);

angular.module('FlowertyApplication', [
	"ngRoute", 
	"FlowertyApplication.UserModule"])

.config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/login', {
                templateUrl: 'partial/_logInForm.html',
                controller: 'logInController'
            }).
            otherwise({
                redirectTo: '/'
            });
}])

.controller('ViewResolverController', [ '$scope', function($scope) {
	$scope.headerTemplateUrl = "resources/partial/header.html";
	$scope.footerTemplateUrl = "resources/partial/footer.html";
} ])

.controller('logInController', function($scope, $http) {

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
        	console.log( "User logged in: " + JSON.stringify(data));
        });
        request.error(function(data, status, headers, config) {
        	console.log( "Exception details: " + JSON.stringify({data: data}));
        });
    };
});
