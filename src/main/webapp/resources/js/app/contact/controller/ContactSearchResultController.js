'use strict';
/*
 * @author Maria Apr 5, 2015
 *
 *	show contacts after search
 */
angular.module("flowertyApplication.contactModule").controller("ContactSearchResultController", ["$scope", "$http", "$location", "contactSearchService", "paginationService",
    function($scope, $http, $location, contactSearchService, paginationService) {

        $scope.bundle = {
            contacts : paginationService.getListBundle()
        };
        $scope.pagination = paginationService.getPagination(contactSearchService.searchContact);
        $scope.pagination.getPage(1);
    }]);
