'use strict';

angular.module("flowertyApplication.contactModule").controller("AddContactController", ["$scope", "$http", "$location", "$routeParams", "processContactService", "CONSTANTS",
    function($scope, $http, $location, $routeParams, processContactService, CONSTANTS){
        $scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.ADD;
        $scope.bundle.processType.action = $scope.bundle.actions.saveContact;
        $scope.bundle.contact = {};
        $scope.bundle.contact.phones = [];
    }]);
