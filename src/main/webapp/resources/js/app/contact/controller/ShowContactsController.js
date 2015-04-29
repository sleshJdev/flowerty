'use strict';

angular.module("flowertyApplication.contactModule").controller("ShowContactsController", ["$scope", "$http", "$location", "contactListService",
    function($scope, $http, $location, contactListService) {
        $scope.init = function () {
            //if we got here, we need all contacts
            contactListService.setList(null);
            $location.path("contact-list");
        };
        $scope.init();
    }]);
