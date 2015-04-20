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
        	templateUrl: APP_PATH + 'goods/partial/goods-list.html',
            controller: 'GoodsListController'
        });

    //  for smart urls
    $locationProvider.html5Mode(true);
}]);