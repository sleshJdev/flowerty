'use strict';
/**
 * Created by Катерина on 11.04.2015.
 */

var orderModule = angular.module("flowertyApplication.orderModule", ['ngRoute']);

orderModule.constant('ORDER_MODULE_CONSTANTS', (function(){

    var ORDER_MODULE_PATH = "resources/js/app/order/";

    return {
        ORDER_EDIT_FORM : ORDER_MODULE_PATH + "partial/order-edit.html",
        ORDER_LIST_FORM : ORDER_MODULE_PATH + "partial/order-list-form.html",
        ORDER_HISTORY_FORM : ORDER_MODULE_PATH + "partial/order-history-form.html"
    }
})());

orderModule.config(["$routeProvider", 'ORDER_MODULE_CONSTANTS', function($routeProvider, ORDER_MODULE_CONSTANTS) {
    $routeProvider
        .when("/orders", {
            templateUrl: ORDER_MODULE_CONSTANTS.ORDER_LIST_FORM,
            controller: "OrdersController"
        })
        .when("/edit-order/:id", {
            templateUrl: ORDER_MODULE_CONSTANTS.ORDER_EDIT_FORM,
            controller: "OrderEditController"
        })
        .when("/order-history/:id", {
            templateUrl: ORDER_MODULE_CONSTANTS.ORDER_HISTORY_FORM,
            controller: "OrderHistoryController"
        });
}]);
