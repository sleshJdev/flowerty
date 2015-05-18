'use strict';
/**
 * Created by Катерина on 08.05.2015.
 *
 * Service for checking out
 */

angular.module("flowertyApplication.orderModule").service('checkoutService', ['$http', function($http){

    var self = this;

    self.checkout = function(order, successCallback, errorCallback){
        saveOrder(order, 'order/save', successCallback, errorCallback);
    };

    self.saveChanges = function(order, successCallback, errorCallback){
        saveOrder(order, 'order/change/save', successCallback, errorCallback);
    };

    var saveOrder = function (order, url, successCallback, errorCallback) {
        $http({
            method: "post",
            url: url,
            data: order
        })
            .success(successCallback)
            .error(errorCallback);
    };

    /**
     *
     * @param items array of order's items
     * @returns {boolean} if at least one item is available at warehouse
     */
    self.canAprooveOrder = function (items) {
        var i = 0;
        for(i = 0; i < items.length; i++){
            if(items[i].quantity){
                return true;
            };
        };
        return false;
    };

}]);