'use strict';
/**
 * Created by Катерина on 14.05.2015.
 *
 * Service for getting list of users
 */

angular.module("flowertyApplication.userModule").service("userListService", ['$http', function($http) {

    var self = this;

    self.getUserList = function(page, limit, successCallback, errorCallback){
        $http({
            method: "get",
            url: "user/list/page=" + page + '&limit=' + limit
        })
            .success(successCallback)
            .error(function (data) {
                console.log("Exception during getting list of the users at page " + page + " with limit of " + limit +":\n"
                + JSON.stringify({data: data}));
                errorCallback(data);
            }
        );
    }
}]);