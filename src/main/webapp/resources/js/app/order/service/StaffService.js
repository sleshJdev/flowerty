'use strict';
/**
 * Created by Катерина on 09.05.2015.
 *
 * Service for getting various staff
 *
 * fFor example, order managers
 */

angular.module("flowertyApplication.orderModule").service('staffService', ['$http', function($http){

    var service = this;

    service.getStaffForRole = function(role, successCallback){
        $http({
            method: "get",
            url: "users/role/" + role
        })
            .success(successCallback)
            .error(function(data, status, headers, config) {
                console.log("Exception during getting staff for role: " + role + " - " + JSON.stringify({data: data}));
            });
    };

}]);
