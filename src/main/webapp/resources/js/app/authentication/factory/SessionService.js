'use strict';
/**
 * Created by Rostislav on 05-Apr-15
 */

angular.module('flowertyApplication.authenticationModule').factory('sessionService', function ($http) {
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
            "username=" + $scope.user.login +
            "&password=" + $scope.user.password +
            "&_spring_security_remember_me=" + !!$scope.rememberMe, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}

            }).then(function (data) {
                session.setLoggedUser($scope);
                $location.path("/");
            }, function (data) {
                $scope.current.isLogged = false;
                $scope.current.user = {};
                $scope.current.errorLogin = true;
            });
    };
    session.logout = function () {
        //localStorage.removeItem("session");
    };
    session.setLoggedUser = function ($scope) {
        $http({
            method: "get",
            url: "login"
        }).success(function(data, status, headers, config) {
            if (data) {
                $scope.current.isLogged = true;
                $scope.current.user.name = data.username;
                $scope.current.user.role = data.authorities[0].authority;
                $scope.current.errorLogin = false;
            }
        });
    };
    return session;
});
