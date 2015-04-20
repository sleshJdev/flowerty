'use strict';

/**
 * Created by Катерина on 17.04.2015.
 */

var goodsModule = angular.module("flowertyApplication.goodsModule", ['ngRoute']);

goodsModule.constant("GOODS_MODULE_CONSTANTS", (function(){

    var GOODS_MODULE_PATH = "resources/js/app/goods/";

    return {
        GOODS_LIST_FORM: GOODS_MODULE_PATH + "partial/goods-list.html"
    }
})());

goodsModule.config(["$routeProvider", 'GOODS_MODULE_CONSTANTS', function($routeProvider, GOODS_MODULE_CONSTANTS) {
    $routeProvider
        .when("/goods", {
            templateUrl: GOODS_MODULE_CONSTANTS.GOODS_LIST_FORM,
            controller: "GoodsListController"
        });
}]);
