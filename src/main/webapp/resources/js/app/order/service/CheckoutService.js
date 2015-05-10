'use strict';
/**
 * Created by Катерина on 08.05.2015.
 *
 * Service for checking out
 */

angular.module("flowertyApplication.orderModule").service('checkoutService', ['$http', function($http){

    var service = this;

    service.checkout = function(order, successCallback, errorCallback){
        saveOrder(order, 'order/save', successCallback, errorCallback);
    };

    service.saveChanges = function(order, successCallback, errorCallback){
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
}]);