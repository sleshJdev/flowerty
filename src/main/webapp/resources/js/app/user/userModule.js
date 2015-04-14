'use strict';
/**
 * Created by Катерина on 24.03.2015.
 */
var userModule = angular.module("flowertyApplication.userModule", ['ngRoute']);

userModule.config(["$routeProvider", function($routeProvider) {
	$routeProvider
        .when("/users", {
            templateUrl: USER_MODULE_PATH + "partial/users-list-form.html",
            controller: "UsersController"
        })
        .when("/edit-user/:id", {
            templateUrl: USER_MODULE_PATH + "partial/user-edit.html",
            controller: "UserEditController"
        })
        .when("/remove-user", {
            templateUrl: USER_MODULE_PATH + "partial/users-list-form.html",
            controller: "UserDeleteController"
        });
}]);

userModule.controller("UserEditController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
	$http({
		method: "get",
		url: "user/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle = data;
	}).error(function(data, status, headers, config) {
		console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
	});
	
    $scope.save = function() {
    	$http({
			method: "post",
			url: "user/save", 
			data: $scope.bundle.user
    	}).success(function(data, status, headers, config) {
    		$location.path("users");
    	}).error(function(data, status, headers, config) {
		});
    };
}]);

userModule.controller("UserDeleteController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
	$http({
		method: "get",
		url: "user/delete/" + $routeParams.id
	}).success(function(data, status, headers, config) {
	}).error(function(data, status, headers, config) {
		console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
	});
}]);

userModule.controller('UsersController', function($scope, $http, $location) {

    $scope.users = {
        pages : [],
        pagesCount : 1,
        currentPage : 1,
        usersList : []
    };

    $scope.users.pageClass = function(pageNumber){
        return pageNumber == $scope.users.currentPage ? 'active' : '';
    };

    $scope.users.getPage = function(pageNumber){
        $scope.users.currentPage = pageNumber;
        $scope.users.getPageFromServer();
    };

    $scope.users.getPageFromServer = function(){
        var request = $http({
            method: "get",
            url: "user/list/" + $scope.users.currentPage
        });

        request.success(function(data, status, headers, config) {
            if (!data.content) {
                $location.path("login");
            } else {
                $scope.users.usersList = data.content;
                $scope.users.pagesCount = data.totalPages;
            }
        });

        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));//COMMENT HERE
            $location.path("/");
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

    $scope.users.getPagesCount = function(){
        return $scope.users.pagesCount;
    };
    
    $scope.users.delete = function(){
        var toDeleteIds = [];
        console.log("users to del : " + JSON.stringify({users: $scope.users.usersList}));
        var user;
        for(var i = 0; i < $scope.users.usersList.length; i++){
            user = $scope.users.usersList[i];
            if(user.checked){
                toDeleteIds.push(user.id);
            }
        }
        if(toDeleteIds.length <= 0){
            return true;
        }
        $http({
            method: "post",
            url: "user/delete",
            data: toDeleteIds
        }).success(function(data, status, headers, config) {
            $location.path("users");
        }).error(function(data, status, headers, config) {
            console.log("Exception details in UsersController.delete() : " + JSON.stringify({data: data}));
        });
    };

    $scope.init = function () {
        $scope.users.getPage(1);
        $scope.pagination.getNextPage = $scope.users.getNextPage;
        $scope.pagination.getPreviousPage = $scope.users.getPreviousPage;
        $scope.pagination.getPage = $scope.users.getPage;
        $scope.pagination.pageClass = $scope.users.pageClass;
        $scope.pagination.getPagesCount = $scope.users.getPagesCount;
    };

    $scope.init();
});

userModule.controller("UserAddController", ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.bundle = {
        user : {}
    };

    $scope.save = function() {
        $http({
            method: "post",
            url: "user/add",
            data: $scope.bundle.user
        }).success(function(data, status, headers, config) {
            $location.path("users");
        }).error(function(data, status, headers, config) {
            console.log("Exception details in UserAddController.save() : " + JSON.stringify({data: data}));
        });
    };
}]);
