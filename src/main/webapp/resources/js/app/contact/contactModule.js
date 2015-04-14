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
		CONTACTS 		: CONTACT_MODULE_PATH + "partial/contact-list-form.html",
		EDIT_CONTACT	: CONTACT_MODULE_PATH + "partial/contact-form.html",
		ADD_CONTACT		: CONTACT_MODULE_PATH + "partial/contact-form.html",
		SEARCH_CONTACT	: CONTACT_MODULE_PATH + "partial/contact-form.html",
		EDIT_PHONE		: CONTACT_MODULE_PATH + "partial/phone-form.html",
		PHONES			: CONTACT_MODULE_PATH + "partial/phone-list-form.html",
		DATE_PICKER		: CONTACT_MODULE_PATH + "partial/date-picker.html",
		
		PHONE_TYPES: [{name: "CELL"}, {name: "HOME"}],
		
		PROCESS_TYPES : { 
			ADD:{
				name: "Add new contact", 
				titleContact: "Add Contact",
				titlePhone: "Add phone",//init below
				isShowPhones: true,
				widthClass: "col-md-6",
				action: []//main action
			}, 
			EDIT:{ 
				name: "Save contact",
				titleContact: "Edit Contact",				
				titlePhone: "Add phone",//init below
				isShowPhones: true,
				widthClass: "col-md-6",
				action: []//main action					
			}, 
			SEARCH:{ 
				name: "Search contact",
				titleContact: "Search Contact",
				titlePhone: "",//not use
				isShowPhones: false,
				widthClass: "col-md-10",
				action: []//main action
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
		.when("/search-contact", {
			templateUrl: CONSTANTS.SEARCH_CONTACT,
			controller: "SearchContactController"
		});
}])

.filter("flowerFullContactName", function() {
	return function(contact){
		return (!contact.name ? "" : contact.name) + " " + 
			   (!contact.surname ? "" : contact.surname) + " " + 
			   (!contact.fathername ? "" : contact.fathername);
	}
})

/*
 * remove item from collection, if his id < 0
 */
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

/*
 * provides a method for crete/edit/remove contact and phones
 */
.service("processContactService", ["$http", "$location", "deleteService", "CONSTANTS",
                                   function($http, $location, deleteService, CONSTANTS) {
	var me = this;
	
	me.bundle = {
			phoneTemplate: CONSTANTS.EDIT_PHONE,
			phoneListTemplate: CONSTANTS.PHONES, 
			datePickerTemplate: CONSTANTS.DATE_PICKER, 
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
	};
	
	/*
	 * add new phone
	 */
	me.bundle.actions.addPhone = function(){
		console.log("show pop to create new phone");
		me.bundle.phone = {};
		me.bundle.processType.titlePhone = "Add phone";
	};
	
	/* 
	 * edit phone. Show pop-up to editing specific phone.
	 */
	me.bundle.actions.editPhone = function(editablePhone){
		console.log("edit phone");
		me.bundle.phone = angular.copy(editablePhone);
		me.bundle.originPhone = editablePhone;
		me.bundle.processType.titlePhone = "Edit phone";
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
	$scope.bundle.processType.action = $scope.bundle.actions.saveContact;
	$scope.bundle.contact = {};
	$scope.bundle.contact.phones = [];
}]) 

.controller("EditContactController", ["$scope", "$http", "$location", "$routeParams", "processContactService", "deleteService", "CONSTANTS",
                                      function($scope, $http, $location, $routeParams, processContactService, deleteService, CONSTANTS){
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.EDIT;
	$scope.bundle.processType.action = $scope.bundle.actions.saveContact;
	
	$http({
		method: "get",
		url: "contact/details/" + $routeParams.id
	}).success(function(data, status, headers, config) {
		$scope.bundle.contact = data;
	}).error(function(data, status, headers, config) {
	});
}])

.controller("SearchContactController", ["$scope", "$http", "processContactService", "CONSTANTS",
                                        function($scope, $http, processContactService, CONSTANTS){	
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
	$scope.bundle.processType.action = search;
	$scope.bundle.contact = {};
	$scope.bundle.contact.phones = [];
	$scope.bundle.date = {
			year: {
				value: "",
				isUse: true
			},
			month: {
				value: "",
				isUse: true
			},
			day: {
				value: "",
				isUse: true
			}
	};
	$scope.bundle.dateListener = function(date){
		bundle.contact = date.year.value + "-"+date.month.value+"-"+date.day.value;
	};
	
	var search = function(contact){
		bundle.contact.birthday = bundle.date.year + "-" + bundle.date.month + "-" + bundle.date.day;
		$http({
			method: "post",
			url: "contact/serach"
		}).success(function(data, status, headers, config) {
			consloe.log(JSON.stringify(data));//REMOVE COMMENT
		}).error(function(data, status, headers, config) {
			console.log("error occured during search contact. details: " + JSON.stringify(data))
		});
	};
}])

.controller("ContactListController", ["$scope", "$http", "$location", "deleteService", 
                                      function($scope, $http, $location, deleteService) {
	$scope.contacts = {
			currentPage: 1,
			totalPage: [],			
			list: []
	};
	
	/*
	 * remove spicific contact(s)
	 */
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
	};
		
    $scope.contacts.getPageFromServer = function(){
        $http({
            method: "get",
            url: "contact/list/" + $scope.contacts.currentPage
        }).success(function(data, status, headers, config) {
			if (!data.content) {
				$location.path("login");
			} else {
				$scope.contacts.list = data.content;
				$scope.contacts.totalPages = data.totalPages;
			}
        }).error(function(data, status, headers, config) {
			$location.path("/");
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

    $scope.contacts.getPagesCount = function(){
        return $scope.contacts.pagesCount;
    };

    $scope.init = function () {
        $scope.contacts.getPage(1);
        $scope.pagination.getNextPage = $scope.contacts.getNextPage;
        $scope.pagination.getPreviousPage = $scope.contacts.getPreviousPage;
        $scope.pagination.getPage = $scope.contacts.getPage;
        $scope.pagination.pageClass = $scope.contacts.pageClass;
        $scope.pagination.getPagesCount = $scope.contacts.getPagesCount;
    };

    $scope.init();
}]);
