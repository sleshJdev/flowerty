'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.goodsModule").controller("GoodsListController",
    ['$scope', '$http', '$location', '$filter', '$localStorage', 'GOODS_MODULE_CONSTANTS',
        function($scope, $http, $location, $filter, $localStorage, GOODS_MODULE_CONSTANTS) {

    $scope.goods = {
        pages : [],
        pagesCount : 1,
        currentPage : 1,
        goodsArray : []
    };

    $scope.goods.initGoodsArray = function(goodsArray){
        var cols = GOODS_MODULE_CONSTANTS.COLUMN_NUMBER;
        var resultMatrix = [];
        var i, j, goodsArrayInd = 0, rowIndex;
        for(i = 0; i < goodsArray.length; i++) {

            goodsArray[i].count = $scope.current.basket.items[goodsArray[i].id] ?
                $scope.current.basket.items[goodsArray[i].id].quantity :
                1;
            if(!(i % cols)){
                resultMatrix.push([]);
            }
            resultMatrix[Math.floor(i / cols)].push(goodsArray[i]);
        }
        return resultMatrix;
    };

    $scope.goods.pageClass = function(pageNumber){
        return pageNumber == $scope.goods.currentPage ? 'active' : '';
    };

    $scope.goods.getPage = function(pageNumber){
        $scope.goods.currentPage = pageNumber;
        $scope.goods.getPageFromServer();
    };

    $scope.goods.getPageFromServer = function(){
        var request = $http({
            method: "get",
            url: "goods/list/" + $scope.goods.currentPage
        });

        request.success(function(data, status, headers, config) {
            if (!data.content) {
                $location.path("login");
            } else {
                $scope.goods.goodsArray = $scope.goods.initGoodsArray(data.content);
                $scope.goods.pagesCount = data.totalPages;
            }
        });

        request.error(function(data, status, headers, config) {
            $scope.current.errorMessage = status;
            $location.path("/error");
        });
    };

    $scope.goods.getPreviousPage = function(){
        if($scope.goods.currentPage !== 1){
            $scope.goods.currentPage--;
        }
        $scope.goods.getPage($scope.goods.currentPage);
    };

    $scope.goods.getNextPage = function(){
        if($scope.goods.currentPage !== $scope.goods.pagesCount){
            $scope.goods.currentPage++;
        }
        $scope.goods.getPage($scope.goods.currentPage);
    };

    $scope.goods.getPagesCount = function(){
        return $scope.goods.pagesCount;
    };

    var getOrderItem = function (goodsItem) {
        return {
            goods: goodsItem,
            quantity: goodsItem.count
        };
    };

    $scope.goods.makeOrder = function(goodsItem){

        $scope.current.basket.items[goodsItem.id] = getOrderItem(goodsItem);
        $scope.current.basket.info.itemsCount += goodsItem.count;
        $scope.current.basket.info.fullCost += goodsItem.cost * goodsItem.count;
        $localStorage.cart = $scope.current.basket;
    };

    $scope.goods.removeFromOrder = function(goodsItem) {
        if ($scope.current.basket.items[goodsItem.id]) {
            $scope.current.basket.info.itemsCount -= $scope.current.basket.items[goodsItem.id].quantity;
            $scope.current.basket.info.fullCost -= goodsItem.cost * $scope.current.basket.items[goodsItem.id].quantity;
            delete $scope.current.basket.items[goodsItem.id];
            goodsItem.count = 1;
        }
        $localStorage.cart = $scope.current.basket;
    };

    $scope.goods.less = function(goodsItem){
        if(goodsItem.count > 1) {
            goodsItem.count--;

            //  If it's already in cart, we also change count in it
            if($scope.current.basket.items[goodsItem.id]){
                $scope.current.basket.items[goodsItem.id].quantity--;
                $scope.current.basket.info.itemsCount--;
                $scope.current.basket.info.fullCost -= goodsItem.cost;
                $localStorage.cart = $scope.current.basket;
            }
        }
    };

    $scope.goods.more = function(goodsItem) {
        console.log(goodsItem.remain);
        if (goodsItem.count < goodsItem.remain) {
            goodsItem.count++;

            //  If it's already in cart, we also change count in it
            if($scope.current.basket.items[goodsItem.id]){
                $scope.current.basket.items[goodsItem.id].quantity++;
                $scope.current.basket.info.itemsCount++;
                $scope.current.basket.info.fullCost += goodsItem.cost;
                $localStorage.cart = $scope.current.basket;
            }
        }
    };

    $scope.goods.getGoodsItemClass = function(goodsItem){
        return $scope.current.basket.items[goodsItem.id] ? 'in-cart' : '';
    };

    $scope.init = function() {
        if ($localStorage.cart) {
            $scope.current.basket = $localStorage.cart;
        }
        $scope.goods.getPage(1);
        $scope.pagination.getNextPage = $scope.goods.getNextPage;
        $scope.pagination.getPreviousPage = $scope.goods.getPreviousPage;
        $scope.pagination.getPage = $scope.goods.getPage;
        $scope.pagination.pageClass = $scope.goods.pageClass;
        $scope.pagination.getPagesCount = $scope.goods.getPagesCount;
    };

    $scope.init();
}]);
