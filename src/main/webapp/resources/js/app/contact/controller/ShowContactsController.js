'use strict';
/*
 * @author Maria Apr 5, 2015
 *
 *	show contacts after search
 */
angular.module("flowertyApplication.contactModule").controller("ShowContactsController", ["$scope", "$http", "$location", "contactListService", "paginationService",
    function($scope, $http, $location, contactListService, paginationService) {

        $scope.contacts = paginationService.getListBundle();

        $scope.init = function () {
            $scope.pagination = paginationService.getPagination(contactListService.searchContact);
            $scope.pagination.getPage(1);
        };

        $scope.init();
    }]);
