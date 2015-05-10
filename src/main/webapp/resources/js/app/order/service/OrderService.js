'use strict';
/**
 * Created by Катерина on 09.05.2015.
 *
 * Service for getting empty prepared order with all the auto-completed fields
 */

angular.module("flowertyApplication.orderModule").service('orderService', ['$http', function($http){

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
        for(basketItem in cart){
            order.items.push(cart[basketItem]);
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

    service.findInArrayById = function(object, array){
        if(!object || !array){

            //  Because we don't want to loose our object
            return object;
        }
        var i;
        for(i = 0; i < array.length; i++){
            if(array[i].id === object.id){
                return array[i];
            }
        }
        return object;
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
