'use strict';
/**
 * Created by Катерина on 11.05.2015.
 */
angular.module("flowertyApplication.contactModule")

.service("contactSearchService", ['$http', function ($http) {

    var service = this;

    var contactToSearch = {};

    service.setContactToSearch = function(contact) {
        contactToSearch = contact;
    };

    service.searchContact = function (page, limit, successCallback, errorCallback) {
        $http({
            method: "post",
            url: "contact/search/" + page + "/" + limit,
            data: contactToSearch
        })
            .success(successCallback)
            .error(function(data) {
                console.log("error search contact. details: " + JSON.stringify(data));
                errorCallback(data);
            }
        );
    };

}]);