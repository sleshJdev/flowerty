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
    'flowertyApplication.orderModule',
    //TODO: maybe remove this dependencies, because other dependent from it
    'flowertyApplication.utilModule',
    'flowertyApplication.errorModule'])

.constant("MAIN_MODULE_CONSTANTS", {APP_PATH : "resources/js/app/"})

.config(['$routeProvider', 'MAIN_MODULE_CONSTANTS',
         function($routeProvider, MAIN_MODULE_CONSTANTS) {
    $routeProvider.
        when('/users', {
            templateUrl: MAIN_MODULE_CONSTANTS.APP_PATH + "user/partial/users-list-form.html",
            controller: "UsersController"
        })
        .when('/add-user', {
            templateUrl: MAIN_MODULE_CONSTANTS.APP_PATH + "user/partial/user-edit.html",
            controller: "UserAddController"
        })
        .when('/login', {
            templateUrl: MAIN_MODULE_CONSTANTS.APP_PATH + 'authentication/partial/log-in-form.html',
            controller: 'LogInController'
        })
        .when('/add-order', {
            templateUrl: MAIN_MODULE_CONSTANTS.APP_PATH + 'order/partial/order-edit.html',
            controller: 'OrderAddController'
        })
        .when('/', {
            templateUrl: MAIN_MODULE_CONSTANTS.APP_PATH + 'goods/partial/goods-list.html',
            controller: 'GoodsListController'
        });
}]);