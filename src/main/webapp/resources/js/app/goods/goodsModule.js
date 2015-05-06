'use strict';

/**
 * Created by Катерина on 17.04.2015.
 */

var goodsModule = angular.module("flowertyApplication.goodsModule", ['ngRoute', 'ngStorage']);

goodsModule.constant("GOODS_MODULE_CONSTANTS", (function(){

    var GOODS_MODULE_PATH = "resources/js/app/goods/";

    return {
        GOODS_LIST_FORM: GOODS_MODULE_PATH + "partial/goods-list.html",
        ADD_GOODS:	GOODS_MODULE_PATH + "partial/goods-form.html"
    }
})());

goodsModule.config(["$routeProvider", 'GOODS_MODULE_CONSTANTS', function($routeProvider, GOODS_MODULE_CONSTANTS) {
    $routeProvider
        .when("/goods", {
            templateUrl: GOODS_MODULE_CONSTANTS.GOODS_LIST_FORM,
            controller: "GoodsListController"
        })
        .when("/goods-add",{
        	templateUrl: GOODS_MODULE_CONSTANTS.ADD_GOODS,
        	controller: "GoodsAddController"
        });
}]);
