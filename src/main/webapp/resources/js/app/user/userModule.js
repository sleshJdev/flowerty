'use strict';
/**
 * Created by Катерина on 24.03.2015.
 */
var userModule = angular.module("flowertyApplication.userModule", ['ngRoute']);

userModule.constant("USER_MODULE_CONSTANTS", (function () {

    var USER_MODULE_PATH = "resources/js/app/user/";

    return {
        USERS_LIST_FORM: USER_MODULE_PATH + "partial/users-list-form.html",
        USER_EDIT_FORM: USER_MODULE_PATH + "partial/user-edit.html",

        PROCESS_TYPES: {
            ADD: {
                actionButton: "Add",
                title: "Add User"
            },
            EDIT: {
                actionButton: "Edit",
                title: "Edit User"
            }
        }
    }
})());

userModule.config(["$routeProvider", 'USER_MODULE_CONSTANTS', function ($routeProvider, USER_MODULE_CONSTANTS) {
    $routeProvider
        .when("/users", {
            templateUrl: USER_MODULE_CONSTANTS.USERS_LIST_FORM,
            controller: "ListUserController"
        })
        .when("/edit-user/:id", {
            templateUrl: USER_MODULE_CONSTANTS.USER_EDIT_FORM,
            controller: "EditUserController"
        });
}]);
