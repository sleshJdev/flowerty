/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
'use strict'

/*
 * TODO: 1. need think about that the do separate file for filters, services and
 * etc, because over-head code obtained
 */

angular.module("flowertyApplication.contactModule", ["ngRoute"])

.constant("CONSTANTS", (function(){
	var CONTACT_MODULE_PATH = "resources/js/app/contact/";
	
	return {
		CONTACTS : CONTACT_MODULE_PATH + "partial/contact-list-form.html",
		EDIT_CONTACT: CONTACT_MODULE_PATH + "partial/contact-form.html",
		ADD_CONTACT: CONTACT_MODULE_PATH + "partial/contact-form.html",
		EDIT_PHONE: CONTACT_MODULE_PATH + "partial/phone-form.html",
		
		PHONE_TYPES: [{name: "CELL"}, {name: "HOME"}],
		
		PROCESS_TYPES : { 
			ADD:{
				name: "add", 
				titleContact: "Add Contact",
				titlePhone: "Add phone"
			}, 
			EDIT:{ 
				name: "edit",
				titleContact: "Edit Contact",
				titlePhone: "Edit phone"
			} 
		}
	}
})())

.config(["$routeProvider", "CONSTANTS", function($routeProvider, CONSTANTS) {
	$routeProvider
		.when("/contacts", {
			templateUrl: CONSTANTS.CONTACTS,
			controller: "ContactListController"
		})
		.when("/edit-contact/:id", {
			templateUrl: CONSTANTS.EDIT_CONTACT,
			controller: "EditContactController"
		})
		.when("/add-contact", {
			templateUrl: CONSTANTS.ADD_CONTACT,
			controller: "AddContactController"
		})
}])

.filter("flowerFullContactName", function() {
	return function(cortege){
		return (!cortege.name ? "" : cortege.name) + " " + 
			   (!cortege.surname ? "" : cortege.surname) + " " + 
			   (!cortege.fathername ? "" : cortege.fathername);
	}
})

.service("deleteService", function(){
	this.deleteById = function(collection){
		var isBreak = true;
		do{
			isBreak = true;
			for(var i = 0; i < collection.length; ++i){
				if(collection[i].id < 0){
					collection.splice(i, 1);
					isBreak = false;
				}
			}
		}while(!isBreak);
	}
})

.service("processContactService", ["$http", "$location", "deleteService", "CONSTANTS",
                                   function($http, $location, deleteService, CONSTANTS) {
	var me = this;
	
	me.bundle = {
			template: CONSTANTS.EDIT_PHONE,
			types: CONSTANTS.PHONE_TYPES,
			contact: {},
			actions: []
	};

	/*
	 * save/update contact after editing/creating
	 */
	me.bundle.actions.saveContact = function(contact){
		$http({
			method: "post",
			url: "contact/save",
			data: contact
		}).success(function(data, status, headers, config) {
			$location.path("contacts");
		}).error(function(data, status, headers, config) {
		});
	}

	/*
	 * delete phone. remove all phone at which id < 0
	 */
	me.bundle.actions.deletePhone = function(){
		console.log("delete phone");
		deleteService.deleteById(me.bundle.contact.phones);
		$location.path("contacts");
	};
	
	/*
	 * add new phone
	 */
	me.bundle.actions.addPhone = function(){
		console.log("show pop to create new phone");
		me.bundle.phone = {};
	}
	
	/* 
	 * edit phone. Show pop-up to editing specific phone.
	 */
	me.bundle.actions.editPhone = function(editablePhone){
		console.log("edit phone");
		me.bundle.phone = angular.copy(editablePhone);
		me.bundle.originPhone = editablePhone;
	};
	
	/*
	 * save/update phone after creating/editing
	 */
	me.bundle.actions.savePhone = function(newPhone){
		if(!newPhone.id){
			me.bundle.contact.phones.push(newPhone)
			console.log("add new phone");
		}else{
			angular.copy(newPhone, me.bundle.originPhone);
			console.log("update phone");
		}
	};
}])

.controller("AddContactController", ["$scope", "$http", "$location", "$routeParams", "processContactService", "CONSTANTS",
                                     function($scope, $http, $location, $routeParams, processContactService, CONSTANTS){
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.ADD;
	$scope.bundle.contact = {};
	$scope.bundle.contact.phones = [];
}]) 

.controller("EditContactController", ["$scope", "$http", "$location", "$routeParams", "processContactService", "deleteService",  "CONSTANTS",
                                      function($scope, $http, $location, $routeParams, processContactService, deleteService, CONSTANTS){
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.EDIT;
	
	$http({
		method: "get",
		url: "contact/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle.contact = data;
	}).error(function(data, status, headers, config) {
	});
}])

.controller("ContactListController", ["$scope", "$http", "deleteService", function($scope, $http, deleteService) {
	$scope.contacts = {
			currentPage: 1,
			totalPage: [],			
			list: []
	} 
	
	$scope.contacts.deleteContact = function(){
		console.log("delete contact");
		deleteService.deleteById($scope.contacts.list);		
		
		$http({
			method: "post",
			url: "contact/remove",
			data: $scope.contacts.list
		}).success(function(data, status, headers, config) {
			console.log("contact delete successful");
			$location.path("contacts");
		}).error(function(data, status, headers, config) {
			console.log("contact delete error: " + JSON.stringify(data))
		})
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
