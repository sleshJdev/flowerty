'use strict';

//TODO:maybe unnecessary...
angular.module("flowertyApplication.errorModule").controller("ErrorHandler", ["$scope", "transportShareErrorData",
    function($scope, transportShareErrorDataService){
        $scope.bundle = {
            error: transportShareErrorDataService.getErrorBundle()
        }
    }]);
