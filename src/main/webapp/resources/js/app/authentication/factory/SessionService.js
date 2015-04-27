'use strict';

/**
 * Created by Rostislav on 05-Apr-15
 */

authenticationModule.factory('sessionService', function ($http) {
    var session = {};
    session.login = function ($scope, $location) {
        console.log("login()");

        //return $http.post(
        //    "/login", {
        //        username : $scope.user.login,
        //        password : $scope.user.password,
        //        _spring_security_remember_me : !!$scope.rememberMe
        //    }, {
        //        headers: {'Content-Type': 'application/json'}
        //

        return $http.post(
            "login",
            "username=" + $scope.current.user.username +
            "&password=" + $scope.current.user.password +
            "&_spring_security_remember_me=" + !!$scope.rememberMe, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })
            .then(function (data) {
                $scope.current.user.password = undefined;
                fillLoggedUser(data.data, $scope);
                $location.path("/");
            }, function (data) {
                $scope.current.isLogged = false;
                $scope.current.user.password = undefined;
                $scope.current.errorLogin = true;
            });
    };
    session.logout = function ($scope, $location) {
        $http.post('logout', {})
            .success(function () {
                $scope.current.isLogged = false;
                $scope.current.user = {};
            });
    };
    session.setLoggedUser = function ($scope) {
        $http({
            method: "get",
            url: "login"
        }).success(function (data, status, headers, config) {
            if (data) {
                fillLoggedUser(data, $scope);
            }
        });
    };
    return session;
});

function fillLoggedUser(data, $scope) {
    $scope.current.isLogged = true;
    $scope.current.user.username = data.username;
    $scope.current.user.role = data.authorities[0].authority;
    $scope.current.errorLogin = false;
}
