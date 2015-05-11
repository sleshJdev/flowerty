'use strict';
/**
 * Created by Катерина on 10.05.2015.
 *
 * Service for getting search results for order
 */

angular.module("flowertyApplication.orderModule").service('orderSearchService', ['$http', function($http){

    var service = this;

    var orderToSearch = {};

    service.setOrderToSearch = function(order) {
        orderToSearch = order;
    };

    service.searchOrders = function(page, limit, successCallback, errorCallback) {
        $http({
            method: "post",
            url: "order/search/" + page + "/" + limit,
            data: orderToSearch
        })
            .success(successCallback)
            .error(function (data) {
                console.log("error search order. details: " + JSON.stringify(data));
                errorCallback(data);
            });
    };
}]);