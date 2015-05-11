'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	show contact list
 */
angular.module("flowertyApplication.contactModule")

.controller("ContactListController", ["$scope", "$http", "$location", "emailService", "deleteService", "contactListService", "stateSaverService", "paginationService",
                                  		function($scope, $http, $location, emailService, deleteService, contactListService, stateSaverService, paginationService) {
	/*
	 * grab emails of selected contact and pass they to SendEmailController
	 */
	function sendEmail() {
		if ($scope.bundle.state.isempty()) {
			alert("Please select contacts to send email.");
		} else {
			emailService.setValue($scope.bundle.state.checkeds);
	     	$location.path("send-email");//redirect to email form 
		};
	};
	
	/*
	 * remove specific contacts
	 */
	 function deleteContact() {
		 deleteService.deleteContact(
				 $scope.bundle.state.checkeds,
				 function (data) {
					 console.log("contact delete successful");
					 $location.path("contact-list");
				 },
				 function (data) {
					 console.log("contact delete error. details: " + JSON.stringify(data));
				 }
		)
		deleteService.deleteIsChecked($scope.bundle.state.ischecked, $scope.bundle.state.checkeds);
	 };
	
	 $scope.bundle = {
			contacts: paginationService.getListBundle(),
			state: stateSaverService.state,
			deleteContact: deleteContact,
			sendEmail: sendEmail
	};
	$scope.bundle.state.reset();
	
	(function(){
		$scope.pagination = paginationService.getPagination(contactListService.getContactList);
		$scope.pagination.getPage(1);
	})();
 }]);
