/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
'use strict'

/*
 * TODO: need think about that the do separate file for filters, services and
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
		
		DATA_PICKER_BOX : CONTACT_MODULE_PATH + "partial/date-picker-box.html",
		DATE_PICKER		: CONTACT_MODULE_PATH + "partial/date-picker.html",
		
		SEND_EMAIL		: CONTACT_MODULE_PATH + "partial/send-email-form.html",

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

.config(["$routeProvider", "$locationProvider", "CONSTANTS", function($routeProvider, $locationProvider, CONSTANTS) {
	$routeProvider
	.when("/contact-list", {
		templateUrl: CONSTANTS.CONTACTS,
		controller: "ContactListController"
	})
	.when("/contact-edit/:id", {
		templateUrl: CONSTANTS.EDIT_CONTACT,
		controller: "EditContactController"
	})
	.when("/contact-add", {
		templateUrl: CONSTANTS.ADD_CONTACT,
		controller: "AddContactController"
	})
	.when("/contact-search", {
		templateUrl: CONSTANTS.SEARCH_CONTACT,
		controller: "SearchContactController"
	})
	.when("/send-email", {
		templateUrl: CONSTANTS.SEND_EMAIL,
		controller: "SendEmailController"
	});
}])

.filter("flowerFullContactName", function() {
	return function(contact){
		return (!contact.name ? "" : contact.name) + " " + 
		(!contact.surname ? "" : contact.surname) + " " + 
		(!contact.fathername ? "" : contact.fathername);
	}
})

.directive('flowertyDate', function ($filter) {
    return {
    	restrict: "A",
        require: "?ngModel",
        link: function (scope, element, attrs, ngModelCtrl) {
        	element.datepicker({
                format : attrs.format.length > 4 ? attrs.format : (" " + attrs.format + " "), //extra space for fetch only year, month or day
                viewMode : !attrs.viewMode ? "days" : attrs.viewMode,
                minViewMode : !attrs.minViewMode ? "days" : attrs.minViewMode 
            }).on('changeDate', function( e ){
            	switch(attrs.format.toLowerCase().trim()){
            	case "yyyy": 
            		ngModelCtrl.$setViewValue(e.date.getFullYear());
            		break;
            	case "mm":
            		ngModelCtrl.$setViewValue(e.date.getMonth() + 1);
            		break;
            	case "dd":
            		ngModelCtrl.$setViewValue(e.date.getDay() + 1);
            		break;
        		default:
        			ngModelCtrl.$setViewValue($filter('date')(e.date,'yyyy-MM-dd'));
    			break;
            	}
                element.datepicker('hide');
            });
            
        	//TODO: wtf?? this code thrown exception: data[option] is not a function
//            scope.$on('$destroy', function () {
//                element.datepicker('destroy');
//            });
        }
    };
})

.directive('flowertyFileUpload', function () {
	return {
		scope: true,        //create a new scope
		link: function (scope, el, attrs) {
				el.bind('change', function (event) {
				var files = event.target.files;
				for (var i = 0; i < files.length; i++) {
					scope.$emit("fileSelected", { file: files[i] });
				}                                       
			});
		}
	};
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
			console.log("save contact success!")
			$location.path("contacts");
		}).error(function(data, status, headers, config) {
			console.log("save contact error: " + JSON.stringify(data))//REMOVE_COMMENT
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
		console.log("show pop-up to create new phone");
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

/*
 * for pass parameter for search from ContactListController to SendEmailController.
 * he will be pass emails of contacts.
 */
.service("transportService", function() {
	var value = [];
	return {
		getValue: function(){
			return value;
		},
		setValue: function(newValue){
			value = newValue;
		}
	};
})

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
		console.log("error contact details: " + JSON.stringify(data));
	});
}])

.controller("SearchContactController", ["$scope", "$http", "processContactService", "CONSTANTS",
                                        function($scope, $http, processContactService, CONSTANTS){	
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
	$scope.bundle.contact = {};
	$scope.bundle.contact.phones = [];
	$scope.bundle.processType.action = function(contact){
		processDate($scope.bundle.date)
		$http({
			method: "post",
			url: "contact/search",
			data: $scope.bundle.contact
		}).success(function(data, status, headers, config) {
			console.log("search contact success:" + JSON.stringify(contact));//REMOVE_COMMENT
		}).error(function(data, status, headers, config) {
			console.log("error search contact. details: " + JSON.stringify(data))//REMOVE_COMMENT
		});
	};
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

	/*
	 * if part of the date is not used, we will replace it by a '?'.
	 * this says that this part is unnecessary to search.
	 */
	function processDate(date){
		$scope.bundle.contact.birthday = 
			(!!date.year.isUse ? date.year.value : "?") + "-" +
			(!!date.month.isUse ? date.month.value : "?") + "-" + 
			(!!date.day.isUse ? date.day.value : "?");
	};
}])

.controller("SendEmailController", ["$scope", "$http", "$location", "transportService",
                                    function($scope, $http, $location, transportService) {
	$scope.bundle = {
			actions: [],
			email:{
				to: [],
				subject: "you subject",
				text: ""
			},
			templates: [],
			template: {},
			files: []
	};
	
	$http({
		method: "get",
		url: "email/templates",
	}).success(function(data, status, headers, config) {
		$scope.bundle.templates = data;
	}).error(function(data, status, headers, config) {
		console.log("error occured during fetch email templates: " + JSON.stringify(data));//LOG
	});
	
	$scope.bundle.email.to = transportService.getValue();//for test: studentbntu@mail.ru is valid
	$scope.bundle.template = $scope.bundle.templates[0];
	
	$scope.$on("fileSelected", function (event, args) {
		$scope.$apply(function () {            
			$scope.bundle.files.push(args.file);
			console.log("add attchment: " + args.file.name + ", current quantity: " + $scope.bundle.files.length);
		});
	});

	// TODO: add service for this logic
	$scope.bundle.actions.send = function(){
		$scope.bundle.email.text = $scope.bundle.template.value;

		var formData = new FormData();
		formData.append("email", angular.toJson($scope.bundle.email));
		formData.append("template", angular.toJson($scope.bundle.template));
		for (var i = 0; i < $scope.bundle.files.length; i++) {
			formData.append("file", $scope.bundle.files[i]);
		}

		$http.post("email/send", formData, {
			headers: {'Content-Type': undefined },
			transformRequest: angular.identity
		}).success(function (data, status, headers, config) {
			console.log("send email success!");
			$location.path("send-email");
		}).error(function (data, status, headers, config) {
			alert("send email failed!");
		});
	};
	
	$scope.bundle.actions.removeAttachment = function(number){
		$scope.bundle.files.splice(number, 1);
		console.log("remove attachment with number: " + number + ", current quantity: " + $scope.bundle.files.length);
	};
	
	$scope.bundle.actions.removeEmail = function(number){
		$scope.bundle.email.to.splice(number, 1);
		console.log("remove email to send with number: " + number + ", current quantity: " + $scope.bundle.email.to.length);
	};
	
	$scope.bundle.actions.addNewEmail = function(event){
		if(event.which === 13) {//code of enter button
			$scope.bundle.email.to.push($scope.bundle.newEmail);
			$scope.bundle.newEmail = "";
			console.log("add new email: " + $scope.bundle.newEmail + ", current quantity: " + $scope.bundle.email.to.length);
		}
	};
}])

.controller("ContactListController", ["$scope", "$http", "$location", "transportService", "deleteService", 
                                      function($scope, $http, $location, transportService, deleteService) {
	$scope.contacts = {
			currentPage: 1,
			totalPages: [],			
			list: []
	};
	
	/*
	 * grab emails of selected contact and pass they to SendEmailController
	 */
	$scope.contacts.goToEmailSend = function(){
		var receivers = [];
		for(var i = 0; i < $scope.contacts.list.length; ++i){
			var contact = $scope.contacts.list[i];
			if(contact.id < 0){
				receivers.push(contact);
			}
		}
		
		transportService.setValue(receivers);
		$location.path("send-email");//redirect to email form
	}

	/*
	 * remove specific contact(s)
	 */
	$scope.contacts.deleteContact = function(){
		console.log("delete contact");
		deleteService.deleteById($scope.contacts.list);		

		$http({
			method: "post",
			url: "contact/remove",
			data: $scope.contacts.list,
			headers: {
				"Content-Type": "application/json",
				"Accept": "text/plain"
			}
		}).success(function(data, status, headers, config) {
			console.log("contact delete successful");
			$location.path("contacts");
		}).error(function(data, status, headers, config) {
		});
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
		return $scope.contacts.totalPages;
	};

	$scope.contacts.pageClass = function(pageNumber){
		return pageNumber == $scope.contacts.currentPage ? 'active' : '';
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
