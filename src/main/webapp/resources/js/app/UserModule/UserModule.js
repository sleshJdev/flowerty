'use strict';

angular.module("FlowertyApplication.UserModule", ['ngRoute'])

.config(["$routeProvider", function($routeProvider) {
	$routeProvider
		.when("/user-list", {
	        templateUrl: USER_MODULE_PATH + "user-list.html",
	        controller: "UsersListController"
	    })
		.when("/user-edit", {
			templateUrl: USER_MODULE_PATH + "user-edit.html",
			controller: "UserEditController"
		})
		.when("/user-delete", {
			templateUrl: USER_MODULE_PATH + "user-list.html",
			controller: "UserDeleteController"
		})
		.when("/edit-mock-user", {
			templateUrl: USER_MODULE_PATH + "user-edit.html",
			controller: "EditMockUserController"
		});
}])

.controller("EditMockUserController", ["$scope", function($scope) {//for test
	var user = {
		"id" : 1,
		"login" : "sergeM",
		"password" : "sergeM",
		"contact" : {
			"id" : 2,
			"name" : "Sergey",
			"surname" : "Sergeev",
			"fathername" : "Sergeevich",
			"birthday" : "1974-06-12",
			"email" : "sergey@mail.com",
			"company" : {
				"id" : 1,
				"name" : "f&j",
				"website" : "www.fj.com"
			}
		}
	};
	
	$scope.user = user;
}])

.controller("UserDeleteController", ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
	$http({
		method: "get",
		url: "user/delete/" + $routeParams.id
	}).success(function(data, status, headers, config) {
			alert("Remove Ok!");
	}).error(function(data, status, headers, config) {
			alert("Remove error: " + JSON.stringify(data));
	});
}])

.controller("UserEditController", ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
	$http({
		method: "get",
		url: "user/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle = data;
		alert(JSON.stringify(data));
	}).error(function(data, status, headers, config) {
		alert("Problem occurred during get details about user with id: " + $routeParams.id + ": " + JSON.stringify(data));
	});
	
    $scope.save = function() {
    	$http({
			method: "post",
			url: "user/save", 
			data: $scope.user
    	}).success(function(data, status, headers, config) {
    		alert("User successfully saved!");
		}).error(function(data, status, headers, config) {
			alert("The problem occurred while saving the user: " + JSON.stringify(data));
		});
    };
}])

.controller('UsersListController', function($scope, $http) {

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


