'use strict';

/*
 * contains the list of orders found by search. if the list is null, than we're not searching but
 * browsing all orders
 */
angular.module("flowertyApplication.orderModule").service("orderListService", function() {
    var list = null;
    return {
        getList: function(){
            return list;
        },
        setList: function(newList){
            list = newList;
        }
    };
})