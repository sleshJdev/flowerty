'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	show contact list
 */
angular.module("flowertyApplication.contactModule").controller("ContactListController", 
		["$scope", "$http", "$location", "transportService", "deleteService", "contactListService", "stateSaverService", "paginationService",
		 function($scope, $http, $location, transportService, deleteService, contactListService, stateSaverService, paginationService) {

             $scope.contacts = paginationService.getListBundle();

             $scope.contacts.state = stateSaverService.state;

             $scope.contacts.state.reset();

             /*
              * grab emails of selected contact and pass they to SendEmailController
              */
             $scope.contacts.goToEmailSend = function () {
                 if ($scope.contacts.state.isempty()) {
                     alert("Please select contacts to send email.");
                 } else {
                     transportService.setValue($scope.contacts.state.checkeds);
                     $location.path("send-email");//redirect to email form
                 }
             };

             /*
              * remove specific contact(s)
              */
             $scope.contacts.deleteContact = function () {
                 deleteService.deleteContact(
                     $scope.contacts.state.ischecked,
                     $scope.contacts.list,
                     function (data) {
                         console.log("contact delete successful");
                         $location.path("contacts");
                     },
                     function (data) {
                     }
                 )
             };

             $scope.init = function () {
                 $scope.pagination = paginationService.getPagination(contactListService.getContactList);
                 $scope.pagination.getPage(1);
             };

             $scope.init();
         }]);