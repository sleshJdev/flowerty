'use strict';

angular.module("flowertyApplication.contactModule").controller("SearchContactController", ["$scope", "$http", "processContactService", "CONSTANTS",
    function($scope, $http, processContactService, CONSTANTS){
        $scope.bundle = processContactService.bundle;
        $scope.bundle.processType = CONSTANTS.PROCESS_TYPES.SEARCH;
        $scope.bundle.contact = {};
        $scope.bundle.contact.phones = [];
        $scope.bundle.processType.action = function(contact){
            $http({
                method: "post",
                url: "contact/search",
                data: $scope.bundle.contact
            }).success(function(data, status, headers, config) {
                console.log("search contact success:" + JSON.stringify(contact));//REMOVE_COMMENT
            }).error(function(data, status, headers, config) {
                console.log("error search contact. details: " + JSON.stringify(data))//REMOVE_COMMENT
            });
        };
        $scope.bundle.date = {
            year: {
                value: "",
                isUse: true
            },
            month: {
                value: "",
                isUse: true
            },
            day: {
                value: "",
                isUse: true
            }
        };

        /*
         * if part of the date is not used, we will replace it by a '?'.
         * this says that this part is unnecessary to search.
         */
        $scope.bundle.dateListener = function(date){
            $scope.bundle.contact.birthday =
                (!!date.year.isUse ? date.year.value : "?") + "-" +
                (!!date.month.isUse ? date.month.value : "?") + "-" +
                (!!date.day.isUse ? date.day.value : "?");
        };
    }]);