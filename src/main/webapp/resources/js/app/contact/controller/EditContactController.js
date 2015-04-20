'use strict';

angular.module("flowertyApplication.contactModule").controller("EditContactController", ["$scope", "$http", "$location", "$routeParams", "processContactService", "deleteService", "CONSTANTS",
    function($scope, $http, $location, $routeParams, processContactService, deleteService, CONSTANTS){
        $scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.EDIT;
        $scope.bundle.processType.action = $scope.bundle.actions.saveContact;

        $http({
            method: "get",
            url: "contact/details/" + $routeParams.id
        }).success(function(data, status, headers, config) {
            $scope.bundle.contact = data;
        }).error(function(data, status, headers, config) {
        });
    }]);

