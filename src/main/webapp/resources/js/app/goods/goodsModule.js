'use strict';

/**
 * Created by Катерина on 17.04.2015.
 */

var goodsModule = angular.module("flowertyApplication.goodsModule", ['ngRoute']);

goodsModule.config(["$routeProvider", function($routeProvider) {
    $routeProvider
        .when("/goods", {
            templateUrl: GOODS_MODULE_PATH + "partial/goods-list.html",
            controller: "GoodsListController"
        });
}]);

goodsModule.controller("GoodsListController", ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {
}]);