'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	show contact list
 */
angular.module("flowertyApplication.contactModule").controller("ContactListController", 
		["$scope", "$http", "$location", "transportService", "deleteService", "contactListService", "stateSaverService", "paginationService",
		  function($scope, $http, $location, transportService, deleteService, contactListService, stateSaverService, paginationService) {
        
        $scope.bundle = {
        		contacts: paginationService.getListBundle(),
        		state: stateSaverService.state,
        		actions: []
        }
        $scope.bundle.state.reset();
        
        /*
         * grab emails of selected contact and pass they to SendEmailController
         */
        $scope.bundle.actions.goToEmailSend = function(){
            if($scope.contacts.state.isempty()){
            	alert("Please select contacts to send email.");
            }else{
            	transportService.setValue($scope.contacts.state.checkeds);
            	$location.path("send-email");//redirect to email form
            }
        };

        /*
         * remove specific contacts
         */
        $scope.bundle.actions.deleteContact = function(){
            $http({
                method: "post",
                url: "contact/remove",
                data: deletableCollection,
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "text/plain"
                }
            }).success(function(data, status, headers, config) {
            	deleteService.deleteIsChecked($scope.state.ischecked, $scope.contacts.list);
                console.log("contact delete successful");
            }).error(function(data, status, headers, config) {
            	console.log("contact delete error. details: " + JSON.stringify(data));
            });
        };

        function getPageFromServer(){
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
            });
        };

        $scope.init = function () {
            $scope.contacts.getPage(1);
            $scope.pagination.getNextPage = paginationService.getPagination(getPageFromServer);
        };

        $scope.init();
    }]);