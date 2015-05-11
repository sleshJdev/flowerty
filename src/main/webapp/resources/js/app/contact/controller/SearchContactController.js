'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	search contact
 */
angular.module("flowertyApplication.contactModule").controller("SearchContactController", ["$scope", "$http", "$location","processContactService", "contactSearchService", "CONSTANTS",
    function($scope, $http, $location, processContactService, contactSearchService, CONSTANTS){

        $scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
        $scope.bundle.contact = {};
        $scope.bundle.contact.phones = [];
        $scope.bundle.processType.action = function(){

            contactSearchService.setContactToSearch($scope.bundle.contact);
            $location.path("show-contacts");
        };
    }]);