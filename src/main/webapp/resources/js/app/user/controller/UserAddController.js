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

    $scope.dynamicSearch = {
        enteredSurname : '',
        offerContacts : function(entered) {
            $scope.dynamicSearch.offeredContacts = $filter('bySurname')([], entered);
            $scope.bundle.user.contact = $scope.dynamicSearch.offeredContacts[0];
        },
        showResults : function(){
            return $scope.dynamicSearch.offeredContacts && $scope.dynamicSearch.offeredContacts.length > 0;
        },
        selectContact : function(){
            //  Setting empty array hides select element
            $scope.dynamicSearch.offeredContacts = [];
            var contact = $scope.bundle.user.contact;
            $scope.dynamicSearch.enteredSurname = contact.name + ' ' + contact.fathername + ' ' + contact.surname;
        }
    };


    $scope.save = function() {
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