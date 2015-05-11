'use strict';
/**
 * Created by Катерина on 11.05.2015.
 *
 * Service for getting pages of goods
 */

angular.module("flowertyApplication.goodsModule").service("goodsListService", ["$http", 'GOODS_MODULE_CONSTANTS',
    function($http, GOODS_MODULE_CONSTANTS) {

        var service = this;

        var cart = {};

        service.setCart = function(existingCart){
            cart = existingCart;
        };

        service.getGoodsList = function (page, limit, successCallback, errorCallback) {
            $http({
                method: "get",
                url: "goods/list/" + page
            })
                .success(function (data) {
                    data.content = initGoodsArray(data.content);
                    successCallback(data);
                })
                .error(function (data) {
                    console.log("Exception during getting list of the goods at page " + page + " with limit of " + limit + ":\n"
                    + JSON.stringify({data: data}));
                    errorCallback(data);
                }
            );
        };

        var initGoodsArray = function (goodsArray) {
            if(!cart.items){
                return [];
            }
            var cols = GOODS_MODULE_CONSTANTS.COLUMN_NUMBER;
            var resultMatrix = [];
            var i;
            for (i = 0; i < goodsArray.length; i++) {

                goodsArray[i].count = cart.items[goodsArray[i].id] ?
                    cart.items[goodsArray[i].id].quantity :
                    1;
                if (!(i % cols)) {
                    resultMatrix.push([]);
                }
                resultMatrix[Math.floor(i / cols)].push(goodsArray[i]);
            }
            return resultMatrix;
        };
    }]);