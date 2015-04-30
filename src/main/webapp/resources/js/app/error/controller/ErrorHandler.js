'use strict';

//TODO:maybe unnecessary...
angular.module("flowertyApplication.errorModule")
    .controller("ErrorHandler", ["$scope", "$location", "errorDataTransportService",

        function ($scope, $location, errorDataTransportService) {

            var error = errorDataTransportService.getErrorBundle();

            if (!error.status) {
                $location.path("/");
            }

            $scope.error = {
                errorMessage: {}
            };

            switch (error.status.toString()) {
                case '403':
                    $scope.error.errorMessage = "You don't have the right to do this.";
                    break;
                default:
                    $scope.error.errorMessage = error.statusText;
            }
        }]);