/**
 * Created by Катерина on 19.03.2015.
 */

var app = angular.module('flowertyApplication', ['ngRoute', 'flowertyApplication.userModule', 'flowertyApplication.authenticationModule']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/users', {
            templateUrl: APP_PATH + "user/partial/users-list-form.html",
            controller: "UsersController"
        }).
        when('/login', {
            templateUrl: APP_PATH + 'authentication/partial/log-in-form.html',
            controller: 'LogInController'
        });
}]);

app.controller('ViewController', ['$scope', function($scope) {
    $scope.templates =
        [
            {
                name: 'header.html',
                url: 'resources/template/header.html'
            },
            {
                name: 'footer.html',
                url: 'resources/template/footer.html'
            }
        ];
    $scope.templates.header = $scope.templates[0];
    $scope.templates.footer = $scope.templates[1];
}]);

app.controller('MainController', function($scope) {

    $scope.current = {
        isLogged : false,
        user : {}
    };

    $scope.current.logOut = function(){

        // Logout logic here

        $scope.current.isLogged = false;
        $scope.user = {};
    };
});
