'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	search contact
 */
angular.module("flowertyApplication.contactModule").controller("SearchContactController", ["$scope", "$http", "$location","processContactService", "contactListService", "CONSTANTS",
    function($scope, $http, $location, processContactService, contactListService, CONSTANTS){
        $scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
        $scope.bundle.contact = {};
        $scope.bundle.contact.phones = [];
        $scope.bundle.processType.action = function(contact){
            $http({
                method: "post",
                url: "contact/search",
                data: $scope.bundle.contact
            }).success(function(data, status, headers, config) {
                console.log("search contact success:" + JSON.stringify(contact));//REMOVE_COMMENT
                contactListService.setList(data);
                $location.path("contact-list");
            }).error(function(data, status, headers, config) {
                console.log("error search contact. details: " + JSON.stringify(data))//REMOVE_COMMENT
            });
        };

    }]);