'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

userModule.controller("UserAddController", ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.option = {
        edit : false
    };

    $scope.bundle = {
        user : {
            contact : null
        }
    };

    $scope.search = {
        user : {
            enteredSurname : '',
            show : false,
            selected : {}
        }
    };

    $scope.save = function() {
        $scope.bundle.user.contact = $scope.search.selected;
        $http({
            method: "post",
            url: "user/add",
            data: $scope.bundle.user
        }).success(function(data, status, headers, config) {
            $location.path("users");
        }).error(function(data, status, headers, config) {
            console.log("Exception details in UserAddController.save() : " + JSON.stringify({data: data}));
        });
    };
}]);