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
	$scope.bundle = {
			template: CONTACT_MODULE_PATH + "partial/phone-edit.html",
			types: [{name: "CELL"}, {name: "HOME"}],
			contact: {},
			actions: []
	};
	
	$http({
		method: "get",
		url: "contact/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle.contact = data;
		console.log("data: " + JSON.stringify(data));
	}).error(function(data, status, headers, config) {
	});
	
	$scope.bundle.actions.saveContact = function(){
		$http({
			method: "post",
			url: "contact/save",
			data: $scope.bundle.contact
		}).success(function(data, status, headers, config) {
			$location.path("contacts");
		}).error(function(data, status, headers, config) {
		});
	}

	/*
	 * util function
	 * TODO: maybe moved to a separate file. for example util.js or in module
	 */
	getPhoneById = function(id){
		var phones = $scope.bundle.contact.phones;
		for(var i = 0; i < phones.length; ++i){
			if(id == phones[i].id){
				return {
					index: i,
					item: phones[i]
				};
			}
		}
	};
	
	/* 
	 * Edit phone. Show pop-up to editing specific phone.
	 */
	$scope.bundle.actions.editPhone = function(id){
		// find contact by id and make copy
		$scope.bundle.editablePhone = angular.copy(getPhoneById(id).item);
		// show edit dialog
		$("#show-edit-phone-pop-up").click();
	};
	
	/*
	 * Save/Update phone after editing
	 */
	$scope.bundle.actions.savePhone = function(updatedPhone){
		angular.copy(updatedPhone, getPhoneById(updatedPhone.id).item);
	};
	
	$scope.bundle.actions.deletePhone = function(phoneId){
		//TODO: for implement this feature need find way select all checked box
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
            $scope.contacts.list = data.content;
            $scope.contacts.totalPages = data.totalPages;
        }).error(function(data, status, headers, config) {
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