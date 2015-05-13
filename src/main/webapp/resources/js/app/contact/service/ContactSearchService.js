'use strict';
/**
 * Created by Катерина on 11.05.2015.
 */
angular.module("flowertyApplication.contactModule")

.service("contactSearchService", ['$http', function ($http) {
    var me = this;

    me.contactToSearch = {};
    me.isSearch = false;
    
    me.setContactToSearch = function(contact) {
    	me.contactToSearch = contact;
        me.isSearch = true;
    };

    me.searchContact = function (page, limit, successCallback, errorCallback) {
    	console.log("search contact: " + JSON.stringify(me.contactToSearch));
        $http({
            method: "post",
            url: "contact/search/" + page + "/" + limit,
            data: me.contactToSearch
        })
            .success(successCallback)
            .error(function(data) {
                console.log("error search contact. details: " + JSON.stringify(data));
                errorCallback(data);
            }
        );
    };
}]);