'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module('flowertyApplication').controller('MainController', function ($scope, $http, $location, sessionService) {

    sessionService.setLoggedUser($scope);

    $scope.current = {
        isLogged: false,
        user: {},
        errorLogin: false
    };

    $scope.current.logOut = function () {

        // Logout logic here

        sessionService.logout();

        $http.post('logout', {})
            .success(function () {
                $scope.current.isLogged = false;
                $scope.user = {};
                $location.path("/");
            })
            .error(function (data) {
                $scope.current.isLogged = false;
                $scope.user = {};
                $location.path("/");
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
