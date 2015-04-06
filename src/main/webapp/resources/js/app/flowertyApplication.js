/**
 * Created by Катерина on 19.03.2015.
 */

var app = angular.module('flowertyApplication', ['ngRoute', 'flowertyApplication.userModule', 'flowertyApplication.authenticationModule']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/users', {
            templateUrl: APP_PATH + "user/partial/users-list-form.html",
            controller: "UsersController"
        })
        .when('/login', {
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

app.controller('MainController', function($scope, $http, $location, sessionService) {

    var user = localStorage.getItem("session") !== null ? localStorage.getItem("session") : {};

    $scope.current = {
        isLogged : sessionService.isLoggedIn(),
        user : user,
        errorLogin : false
    };

    $scope.current.logOut = function(){

        // Logout logic here

        sessionService.logout();

        $http.post('logout', {}).success(function() {
            $scope.current.isLogged = false;
            $scope.user = {};
            $location.path("/");
        }).error(function(data) {
            $scope.current.isLogged = false;
            $scope.user = {};
            $location.path("/");
        });

    };
});
