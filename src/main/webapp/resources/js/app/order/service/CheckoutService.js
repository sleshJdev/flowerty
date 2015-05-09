'use strict';
/**
 * Created by Катерина on 08.05.2015.
 */

angular.module("flowertyApplication.orderModule").service('checkoutService', ['$http', function($http){
    var self = this;

    self.checkout = function(order, successCallback, errorCallback){

        $http({
            method: 'post',
            url: 'order/save',
            data: order
        })
            .success(successCallback)
            .error(errorCallback);
    };
}]);