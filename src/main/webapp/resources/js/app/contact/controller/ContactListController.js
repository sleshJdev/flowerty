'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	show contact list
 */
angular.module("flowertyApplication.contactModule").controller("ContactListController", 
		["$scope", "$http", "$location", "transportService", "deleteService", "contactListService", "stateSaverService",
		 function($scope, $http, $location, transportService, deleteService, contactListService, stateSaverService) {
        
		$scope.contacts = {
            currentPage: 1,
            totalPages: [],
            list: [],
            state: stateSaverService.state
        };
        $scope.contacts.state.reset();
        
        /*
         * grab emails of selected contact and pass they to SendEmailController
         */
        $scope.contacts.goToEmailSend = function(){
            if($scope.contacts.state.isempty()){
            	alert("Please select contacts to send email.");
            }else{
            	transportService.setValue($scope.contacts.state.checkeds);
            	$location.path("send-email");//redirect to email form
            }
        };

        /*
         * remove specific contact(s)
         */
        $scope.contacts.deleteContact = function(){
            console.log("delete contact");
            
            $http({
                method: "post",
                url: "contact/remove",
                data: $scope.contacts.state.checkeds,
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "text/plain"
                }
            }).success(function(data, status, headers, config) {
            	deleteService.deleteIsChecked($scope.contacts.state.ischecked, $scope.contacts.list);
                console.log("contact delete successful");
//                $location.path("contacts");
            }).error(function(data, status, headers, config) {
            	console.log("contact delete error. details: " + JSON.stringify({data: data}));
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
            });
        };

        $scope.contacts.getPage = function(pageNumber){
            $scope.contacts.currentPage = pageNumber;
            var list = contactListService.getList();
            if (list) {
                $scope.contacts.list = list.content;
                $scope.contacts.totalPages = list.totalPages;
            }  else {
                $scope.contacts.getPageFromServer();
            }
        };

        $scope.contacts.getPreviousPage = function(){
            if($scope.contacts.currentPage > 1) {
                $scope.contacts.currentPage--;
                $scope.contacts.getPageFromServer();
            }
        };

        $scope.contacts.getNextPage = function(){
            if($scope.contacts.currentPage < $scope.contacts.totalPages){
                $scope.contacts.currentPage++;
                $scope.contacts.getPageFromServer();
            }
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