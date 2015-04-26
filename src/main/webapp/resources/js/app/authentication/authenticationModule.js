'use strict';
/**
 * Created by Катерина on 19.03.2015
 */

var authenticationModule = angular.module('flowertyApplication.authenticationModule', ['ngRoute']);

authenticationModule.constant("AUTHENTICATION_MODULE_CONSTANTS", (function(){

    var AUTHENTICATION_MODULE_PATH = "resources/js/app/authentication/";

    return {
        LOG_IN_FORM: AUTHENTICATION_MODULE_PATH + 'partial/log-in-form.html',
        SIGN_UP_FORM: AUTHENTICATION_MODULE_PATH + 'partial/sign-up-form.html'
    }
})());

authenticationModule.config(["$routeProvider", 'AUTHENTICATION_MODULE_CONSTANTS', function ($routeProvider, AUTHENTICATION_MODULE_CONSTANTS) {
    $routeProvider
        .when('/login', {
            templateUrl: AUTHENTICATION_MODULE_CONSTANTS.LOG_IN_FORM,
            controller: 'LogInController'
        })
        .when('/signup', {
            templateUrl: AUTHENTICATION_MODULE_CONSTANTS.SIGN_UP_FORM,
            controller: 'SignUpController'
        });
}]);