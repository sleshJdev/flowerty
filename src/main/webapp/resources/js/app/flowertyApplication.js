
/**
 * Created by Катерина on 19.03.2015.
 */

var APP_PATH = "resources/js/app/";

var app = angular.module('flowertyApplication', ['ngRoute']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/users', {
            templateUrl: APP_PATH + "user/partial/users-list-form.html",
            controller: "UsersController"
        }).
        when('/login', {
            templateUrl: APP_PATH + 'login/partial/log-in-form.html',
            controller: 'LogInController'
        });
}]);

app.controller('ViewController', ['$scope', function($scope) {
    $scope.templates =
        [
            {
                name: 'header.html',
                url: 'resources/partial/header.html'
            },
            {
                name: 'footer.html',
                url: 'resources/partial/footer.html'
            }
        ];
    $scope.templates.header = $scope.templates[0];
    $scope.templates.footer = $scope.templates[1];
}]);

app.controller('LogInController', function($scope, $http) {

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

app.controller("UserActionProcessController", ["$scope", "$http", function($scope, $http) {
    $scope.remove = function(id) {
        $http
            .get("user/remove/" + id)
            .success(function(data, status, headers, config) {
                alert("Remove Ok!");
            })
            .error(function(data, status, headers, config) {
                alert("Remove Error!");
            });
    };

    $scope.edit = function(id) {
        $http
            .get("user/details/" + id)
            .success(function(data, status, headers, config) {
                $scope.userrr = data;
                alert("Edit Ok!" + JSON.stringify({data: data}));
            })
            .error(function(data, status, headers, config) {
                alert("Edit Error!");
            });
    };

    $scope.saveContact = function() {
        alert("Save user ok!");
    }
}]);

/**
 * Created by Катерина on 24.03.2015.
 */

app.controller('UsersController', function($scope, $http) {

    $scope.users = {
        pages : [],
        pagesCount : 3,
        currentPage : 1,
        usersList : []
    };

    $scope.users.pageClass = function(pageNumber){
        return pageNumber == $scope.users.currentPage ? 'active' : '';
    };

    $scope.users.setPagination = function(){
        $scope.users.pages = [];
        for(var i = 1; i <= $scope.users.pagesCount; i++){
            var obj = {
                value : i
            };
            $scope.users.pages.push(obj);
        }
    };

    $scope.users.getPage = function(pageNumber){
        $scope.users.currentPage = pageNumber;
        $scope.users.getPageFromServer();
        $scope.users.setPagination();
    };

    $scope.users.getPageFromServer = function(){
        var request = $http({
            method: "get",
            url: "user/list/" + $scope.users.currentPage
        });

        request.success(function(data, status, headers, config) {
            alert( "Response: " + JSON.stringify({data: data}));
            $scope.users.usersList = data;
            $scope.users.pagesCount = 3;
        });

        request.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });
    };

    $scope.users.getPreviousPage = function(){
        if($scope.users.currentPage !== 1){
            $scope.users.currentPage--;
        }
        $scope.users.getPage($scope.users.currentPage);
    };

    $scope.users.getNextPage = function(){
        if($scope.users.currentPage !== $scope.users.pagesCount){
            $scope.users.currentPage++;
        }
        $scope.users.getPage($scope.users.currentPage);
    };

    $scope.users.getPage(1);

});
