'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	search contact
 */
angular.module("flowertyApplication.contactModule")

.controller("SearchContactController", ["$scope", "$http", "$location","processContactService", "contactSearchService", "CONSTANTS",
                                        function($scope, $http, $location, processContactService, contactSearchService, CONSTANTS){
    function buildContactDocument(sourceContact){
    	return {
    		      name: 			sourceContact.name,
    		      surname: 			sourceContact.surname,
    		      fathername: 		sourceContact.fathername,
    		      birthday: 		sourceContact.birthday,
    		      email: 			sourceContact.email,
    		      
    		      day: 				sourceContact.date.day,
    		      month: 			sourceContact.date.month,
    		      year: 			sourceContact.date.year,
    		      
    		      birthdayBefore: 	sourceContact.date.birthdayBefore,
    		      birthdayAfter:  	sourceContact.date.birthdayAfter,
    		      
    		      country: 			sourceContact.address.country,
    		      town: 			sourceContact.address.town,
    		      street: 			sourceContact.address.street,
    		      house: 			sourceContact.address.house,
    		      flat: 			sourceContact.address.flat
	      };
    }
	
    function action(contactToSearch){
        contactSearchService.setContactToSearch(buildContactDocument(contactToSearch));        
        $location.path("show-contacts");
    }
    
	$scope.bundle = processContactService.bundle;
	$scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
	$scope.bundle.processType.action = action;
	
	$scope.bundle.contact = {
			name: "",
			surname: "",
			fathername: "",
			birthday: "",
			email: "",
	      
			birthdayBefore: "",
			birthdayAfter:  "",
	      
			address: {
				country: "",
				town: "",
				street: "",
				house: "",
				flat: ""
			},
			
			date: {
					day: "",
		    		month: "",
		    		year: "",
		    		birthdayBefore: "",
		    		birthdayAfter: ""
			}
    };
}]);