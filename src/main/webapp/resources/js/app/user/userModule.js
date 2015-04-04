/**
 * Created by Катерина on 24.03.2015.
 */

var APP_PATH = "resources/js/app/";
var USER_MODULE_PATH = APP_PATH + "user/";

var userModule = angular.module("flowertyApplication.userModule", ['ngRoute']);

userModule.config(["$routeProvider", function($routeProvider) {
	$routeProvider
	.when("/users", {
        templateUrl: USER_MODULE_PATH + "partial/users-list-form.html",
        controller: "UsersController"
    })
	.when("/edit-user", {
		templateUrl: USER_MODULE_PATH + "partial/user-edit.html",
		controller: "UserEditController"
	})
	.when("/remove-user", {
		templateUrl: USER_MODULE_PATH + "partial/users-list-form.html",
		controller: "UserDeleteController"
	});
}]);

/*
 * Make separating, paste together tokens and make capitalize first character of first token.
 * Example: UPPER_CASE -> Upper case. I this case, seprator='_'.
 */
userModule.filter("flowerSplit", function() {
	return function(value, separator) {
		var tokens = value.toLowerCase().split(separator);
		var result = "";
		for(var i = 0; i < tokens.length; ++i){
			if(i == 0){
				result = tokens[i].charAt(0).toUpperCase() + tokens[i].substring(1);
				continue;
			}
			result += " " + tokens[i];
		}
		return result;
	}
})

userModule.controller("UserEditController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
	$http({
		method: "get",
		url: "user/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle = data;
		console.log("user details: " + JSON.stringify(data));
	}).error(function(data, status, headers, config) {
		console.log("Problem occurred during get details about user with id: " + $routeParams.id + ": " + JSON.stringify(data));
	});
	
    $scope.save = function() {
    	$http({
			method: "post",
			url: "user/save", 
			data: $scope.bundle.user
    	}).success(function(data, status, headers, config) {
    		console.log("user successfully saved!");
    		$location.path("users");
    	}).error(function(data, status, headers, config) {
			console.log("The problem occurred while saving the user: " + JSON.stringify(data));
		});
    };
}]);

userModule.controller("UserDeleteController", ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
	alert($routeParams.id);
	$http({
		method: "get",
		url: "user/delete/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		console.log("Remove Ok!");
	}).error(function(data, status, headers, config) {
		console.log("Remove error: " + JSON.stringify(data));
	});
}])

/**
 * Created by Катерина on 24.03.2015.
 */

userModule.controller('UsersController', function($scope, $http) {

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
            console.log("Response: " + JSON.stringify({data: data.content}));
            $scope.users.usersList = data.content;
            $scope.users.pagesCount = data.totalElements;
        });

        request.error(function(data, status, headers, config) {
            console.log("Exception details: " + JSON.stringify({data: data}));
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
