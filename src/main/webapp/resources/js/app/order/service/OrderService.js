'use strict';
/**
 * Created by Катерина on 09.05.2015.
 *
 * Service for getting empty prepared order with all the auto-completed fields
 */

angular.module("flowertyApplication.orderModule").service('orderCommonService', ['$http', function($http){

    var service = this;

    service.getPreparedOrderCreateBundle = function(successCallback, errorCallback){
        $http({
            method: "get",
            url: "order/create/bundle"
        })
            .success(successCallback)
            .error(function(data) {
                console.log("Exception during getting order create bundle: " + JSON.stringify({data: data}));
                errorCallback(data);
            });
    };

    service.initCartItems = function(order, cart){
        var basketItem;
        order.items = [];
        for(basketItem in cart.items){
            cart.items[basketItem].goods.remain -= cart.items[basketItem].quantity;
            order.items.push(cart.items[basketItem]);
        }
        order.cost = cart.info.fullCost;
    };

    service.getOrder = function(id, successCallback, errorCallback){
        $http({
            method: "get",
            url: "order/details/" + id
        })
            .success(successCallback)
            .error(function (data) {
                console.log("Exception details: " + JSON.stringify({data: data}));
                errorCallback(data);
            });
    };

    service.getHistory = function(id, successCallback, errorCallback){
        $http({
            method: "get",
            url: "order/history/" + id
        })
            .success(successCallback)
            .error(function (data, status, headers, config) {
                console.log("Exception during getting history of the order with id = " + id + ":\n" + JSON.stringify({data: data}));
                errorCallback(data);
            });
    }

}]);
