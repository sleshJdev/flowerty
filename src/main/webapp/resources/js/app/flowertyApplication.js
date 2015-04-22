'use strict';
/**
 * Created by Катерина on 19.03.2015.
 */

var app = angular.module('flowertyApplication', [
    'ngRoute',
    'flowertyApplication.userModule',
    'flowertyApplication.authenticationModule',
    'flowertyApplication.contactModule',
    'flowertyApplication.goodsModule',
    //TODO: maybe remove this dependencies, because other dependent from it
    'flowertyApplication.utilModule',
    'flowertyApplication.errorModule'])
 
.config(['$routeProvider', function($routeProvider) {
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
        	templateUrl: APP_PATH + 'goods/partial/goods-list.html',
            controller: 'GoodsListController'
        });
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

    sessionService.setLoggedUser($scope);

    $scope.current = {
        isLogged: false,
        user: {},
        errorLogin: false
    };

    $scope.current.logOut = function () {
        sessionService.logout($scope, $location);
    };

    $scope.pagination = {
        getPagesCount : function(){},
        pageClass : function(page){},
        getPage : function(page){},
        getPreviousPage : function(){},
        getNextPage : function(){}
    }
});
