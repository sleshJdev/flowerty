
/**
 * Created by Катерина on 19.03.2015.
 */

angular.module('flowertyApp', ['ngRoute']).controller('logInController', function($scope, $http) {

    $scope.login = '';
    $scope.password = '';

    $scope.logIn = function() {

        var logged = {
            'login' : $scope.login,
            'password' : $scope.password
        };

        var request = $http({
            method: "post",
            url: "login",
            data: {
                loggedInUser: logged
            }
        });
        request.success(function(data, status, headers, config) {
            alert( "User logged in: " + JSON.stringify(data));
        });
        request.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });
    };
});