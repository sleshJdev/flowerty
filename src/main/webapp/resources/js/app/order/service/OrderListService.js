'use strict';

/*
 * contains the list of orders found by search. if the list is null, than we're not searching but
 * browsing all orders
 */
angular.module("flowertyApplication.orderModule").service("orderListService", ['$http', function($http) {

    var service = this;

    //TODO: use LIMIT!!!
    service.getOrderList = function(page, limit, successCallback, errorCallback){
        $http({
            method: "get",
            url: "order/list/" + page
        })
            .success(successCallback)
            .error(function(data) {
                console.log("Exception during getting list of the orders at page " + page + " with limit of " + limit +":\n"
                + JSON.stringify({data: data}));
                errorCallback(data);
            });
    }
}]);