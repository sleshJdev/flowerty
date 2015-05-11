'use strict';
/*
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	edit contact
 */
angular.module("flowertyApplication.contactModule").controller("EditContactController", 
		["$scope", "$http", "$location", "$routeParams", "processContactService", "deleteService", "stateSaverService", "CONSTANTS",
		 function($scope, $http, $location, $routeParams, processContactService, deleteService, stateSaverService, CONSTANTS){
        
		$scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.EDIT;
        $scope.bundle.processType.action = $scope.bundle.actions.saveContact;
        $scope.bundle.state = stateSaverService.state;
        $scope.bundle.state.reset();

        $scope.bundle.actions.getContact(
            $routeParams.id,
            function(data) {
                $scope.bundle.contact = data;
            },
            function(data) {
                console.log("error contact details: " + JSON.stringify(data));
            }
        );
    }]);