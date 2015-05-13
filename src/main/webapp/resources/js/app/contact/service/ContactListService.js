'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * Service for getting lists of contacts at certain page with some limit
 */

angular.module("flowertyApplication.contactModule").service("contactListService", ['$http',
    function($http) {

    var me = this;

    me.getContactList = function(page, limit, successCallback, errorCallback){
    	console.log("search contact");
        $http({
            method: "get",
            url: "contact/list/" + page + "/" + limit
        })
            .success(successCallback)
            .error(function(data) {
                console.log("Exception during getting list of the contacts at page " + page + " with limit of " + limit +":\n"
                + JSON.stringify({data: data}));
                errorCallback(data);
            }
        );
    };
}]);