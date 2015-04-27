'use strict';

angular.module("flowertyApplication.contactModule").controller("ContactListController", ["$scope", "$http", "$location", "transportService", "deleteService",
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
            var emails = [];
            for(var i = 0; i < $scope.contacts.list.length; ++i){
                var contact = $scope.contacts.list[i];
                if(contact.id < 0){
                    emails.push(contact.email);
                }
            }

            transportService.setValue(emails);
            $location.path("send-email");//redirect to email form
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
                $scope.current.errorMessage = status;
                $location.path("/error");
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