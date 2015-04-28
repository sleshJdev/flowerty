'use strict';

//TODO:maybe unnecessary...
angular.module("flowertyApplication.errorModule")

    .controller("ErrorHandler", ["$scope", "$location",
        function ($scope, $location) {

            if (!$scope.current.errorMessage) {
                $location.path("/");
            }

            if ("403" == $scope.current.errorMessage) {
                $scope.current.errorMessage = "You don't have the right to do this.";
            }

        }]);

