/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
angular.module("flowertyApplication.contactModule", ["ngRoute"])

.config(["$routeProvider", function($routeProvider) {
	$routeProvider
		.when("/contacts", {
			templateUrl: CONTACT_MODULE_PATH + "partial/contact-list-form.html",
			controller: "ContactListController"
		})
		.when("/edit-contact/:id", {
			templateUrl: CONTACT_MODULE_PATH + "partial/contact-edit.html",
			controller: "ContactEditController"
		})
}])

.controller("ContactEditController", ["$scope", "$http", "$location", "$routeParams", function($scope, $http, $location, $routeParams){
	$http({
		method: "get",
		url: "contact/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.contact = data;
		console.log(JSON.stringify(data)); //COMMENT HERE
		console.log("user for editing obtained successful") //COMMENT HERE
	}).error(function(data, status, headers, config) {
		
		console.log("error: " + JSON.stringify(data)) //COMMENT HERE
	});
	
	$scope.save = function(){
		console.log("contact to save: " + JSON.stringify($scope.contact)); //COMMENT HERE
		$http({
			method: "post",
			url: "contact/save",
			data: $scope.contact
		}).success(function(data, status, headers, config) {
			$location.path("contacts");
			console.log("contact save successful!");// COMMENT HERE
		}).error(function(data, status, headers, config) {
			console.log("error: " + JSON.stringify(data));// COMMENT HERE
		});
	}
}])

.controller("ContactListController", ["$scope", "$http", function($scope, $http) {
	$scope.contacts= {
			currentPage: 1,
			totalPage: [],			
			list: []
	} 
		
    $scope.contacts.getPageFromServer = function(){
        $http({
            method: "get",
            url: "contact/list/" + $scope.contacts.currentPage
        }).success(function(data, status, headers, config) {
            console.log("response: " + JSON.stringify(data));//COMMENT HERE
            $scope.contacts.list = data.content;
            $scope.contacts.totalPages = data.totalPages;
        }).error(function(data, status, headers, config) {
            console.log("exception details: " + JSON.stringify({data: data}));//COMMENT HERE
        });
    };
    
    $scope.contacts.getPage = function(pageNumber){
    	$scope.contacts.currentPage = pageNumber;
    	$scope.contacts.getPageFromServer();
    };

    $scope.contacts.getPreviousPage = function(){
        if($scope.contacts.currentPage > 1){
            $scope.contacts.currentPage--;
        }
        $scope.contacts.getPageFromServer();
    };

    $scope.contacts.getNextPage = function(){
        if($scope.contacts.currentPage < $scope.contacts.totalPages){
            $scope.contacts.currentPage++;
        }
        $scope.contacts.getPageFromServer();
    };
    
    $scope.contacts.getPage(1);
}]);