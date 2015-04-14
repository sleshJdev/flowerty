'use strict';
/**
 * Created by Катерина on 19.03.2015.
 */

var app = angular.module('flowertyApplication', [
    'ngRoute',
    'flowertyApplication.userModule',
    'flowertyApplication.authenticationModule',
    'flowertyApplication.contactModule',
    //TODO: maybe remove this dependencies, because other dependent from it
    'flowertyApplication.utilModule',
    'flowertyApplication.errorModule'])
 
.config(['$routeProvider', '$locationProvider',  
         function($routeProvider, $locationProvider) {
    $routeProvider.
        when('/users', {
            templateUrl: APP_PATH + "user/partial/users-list-form.html",
            controller: "UsersController"
        })
        .when('/add-user', {
            templateUrl: APP_PATH + "user/partial/user-edit.html",
            controller: "UserAddController"
        })
        .when('/login', {
            templateUrl: APP_PATH + 'authentication/partial/log-in-form.html',
            controller: 'LogInController'
        })
        .when('/', {
        	templateUrl: 'resources/template/welcome.html'
        });

    //  for smart urls
    $locationProvider.html5Mode(true);
}]);

app.controller('ViewController', ['$scope', function ($scope) {
    $scope.templates =
        [
            {
                name: 'header.html',
                url: 'resources/template/header.html'
            },
            {
                name: 'footer.html',
                url: 'resources/template/footer.html'
            },
            {
                name: 'pagination.html',
                url: 'resources/template/pagination.html'
            }
        ];
    $scope.templates.header = $scope.templates[0];
    $scope.templates.footer = $scope.templates[1];
    $scope.templates.pagination = $scope.templates[2];
}]);

app.controller('MainController', function ($scope, $http, $location, sessionService) {

    sessionService.getLoggedUser($scope);

    $scope.current = {
        isLogged: false,
        user: {},
        errorLogin: false
    };

    $scope.current.logOut = function () {

        // Logout logic here

        sessionService.logout();

        //delete $window.sessionStorage.token;
        //$cookieStore.remove("token");

        $http.post('logout', {}).success(function() {
            $scope.current.isLogged = false;
            $scope.user = {};
        }).error(function(data) {
            $scope.current.isLogged = false;
            $scope.user = {};
        });
    };

    $scope.pagination = {
        getPagesCount : function(){},
        pageClass : function(page){},
        getPage : function(page){},
        getPreviousPage : function(){},
        getNextPage : function(){}
    }
});
