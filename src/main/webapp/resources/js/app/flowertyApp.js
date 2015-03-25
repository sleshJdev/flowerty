
var app = angular.module('flowertyApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/login', {
                templateUrl: '/WEB-INF/views/home/partial/_logInForm.html',
                controller: 'logInController'
            }).
            when('/showUsers', {
                templateUrl: '/WEB-INF/views/home/partial/_usersListForm.html',
                controller: 'usersController'
            }).
            otherwise({
                redirectTo: '/'
            });
    alert("route");
    }]);