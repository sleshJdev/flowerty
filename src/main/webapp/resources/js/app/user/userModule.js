'use strict';
/**
 * Created by Катерина on 24.03.2015.
 */
var userModule = angular.module("flowertyApplication.userModule", ['ngRoute']);

userModule.constant("USER_MODULE_CONSTANTS", (function(){

    var USER_MODULE_PATH = "resources/js/app/user/";

    return {
        USERS_LIST_FORM: USER_MODULE_PATH + "partial/users-list-form.html",
        USER_EDIT_FORM: USER_MODULE_PATH + "partial/user-edit.html"
    }
})());

userModule.config(["$routeProvider", 'USER_MODULE_CONSTANTS', function($routeProvider, USER_MODULE_CONSTANTS) {
	$routeProvider
        .when("/users", {
            templateUrl: USER_MODULE_CONSTANTS.USERS_LIST_FORM,
            controller: "UsersController"
        })
        .when("/edit-user/:id", {
            templateUrl: USER_MODULE_CONSTANTS.USER_EDIT_FORM,
            controller: "UserEditController"
        })
        .when("/remove-user", {
            templateUrl: USER_MODULE_CONSTANTS.USER_EDIT_FORM,
            controller: "UserDeleteController"
        });
}]);

userModule.filter('bySurname', ['$http', function($http){
    return function(offeredContacts, enteredSurname){
        if(enteredSurname.length < 3){
            return [];
        }

        console.log('Searching by surname: ' + enteredSurname);

        //  Getting search by surname results from server
        $http({
            method: "get",
            url: "user/search/" + enteredSurname,
            data: {}
        }).success(function(data, status, headers, config) {
            return data.content;
        }).error(function(data, status, headers, config) {
            console.log("Exception details in bySurname filter : " + JSON.stringify({data: data}));
            //return [];

            //  Just emulation
            var offered = [
                {
                    name : 'Пётр',
                    surname : 'Первый',
                    fathername : 'Петрович'
                },
                {
                    name : 'Николай',
                    surname : 'Басков',
                    fathername : 'Николаевич'
                },
                {
                    name : 'Александр',
                    surname : 'Пушкин',
                    fathername : 'Сергеевич'
                },
                {
                    name : 'Катерина',
                    surname : 'Петрова',
                    fathername : 'Ивановна'
                },
                {
                    name : 'Наталья',
                    surname : 'Иванова',
                    fathername : 'Ивановна'
                }
            ];
            return offered.slice(enteredSurname.length - 3);
        });
        //  Just emulation
        var offered = [
            {
                name : 'Пётр',
                surname : 'Первый',
                fathername : 'Петрович'
            },
            {
                name : 'Николай',
                surname : 'Басков',
                fathername : 'Николаевич'
            },
            {
                name : 'Александр',
                surname : 'Пушкин',
                fathername : 'Сергеевич'
            },
            {
                name : 'Катерина',
                surname : 'Петрова',
                fathername : 'Ивановна'
            },
            {
                name : 'Наталья',
                surname : 'Иванова',
                fathername : 'Ивановна'
            }
        ];
        return offered.slice(enteredSurname.length - 3);
    }
}]);
