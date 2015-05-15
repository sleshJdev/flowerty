'use strict';
/**
 * Created by Катерина on 11.05.2015.
 */
angular.module("flowertyApplication.contactModule").service("contactSearchService", ['$http',
    function ($http) {
        var self = this;

        self.contactToSearch = {};

        self.setContactToSearch = function (contact) {
            self.contactToSearch = contact;
        };

        self.searchContact = function (page, limit, successCallback, errorCallback) {
            $http({
                method: "post",
                url: "contact/search/" + page + "/" + limit,
                data: self.contactToSearch
            })
                .success(successCallback)
                .error(function (data) {
                    console.log("error search contact. details: " + JSON.stringify(data));
                    errorCallback(data);
                }
            );
        };
    }]);