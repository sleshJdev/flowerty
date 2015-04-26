'use strict';

//TODO:maybe unnecessary...
angular.module("flowertyApplication.errorModule")

    .controller("ErrorHandler", ["$scope",
        function ($scope) {

            if ("403".equals($scope.errorMessage)) {
                $scope.errorMessage = "You don't have the right to do this."
            }

        }]);

