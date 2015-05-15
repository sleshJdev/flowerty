'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.goodsModule").controller("GoodsListController",
    ['$scope', '$http', '$location', '$filter', '$localStorage', 'GOODS_MODULE_CONSTANTS', 'goodsListService', 'paginationService',
        function($scope, $http, $location, $filter, $localStorage, GOODS_MODULE_CONSTANTS, goodsListService, paginationService) {

            $scope.goods = paginationService.getListBundle();

            var getOrderItem = function (goodsItem) {
                return {
                    goods: goodsItem,
                    quantity: goodsItem.count
                };
            };

            $scope.goods.makeOrder = function (goodsItem) {

                $scope.current.basket.items[goodsItem.id] = getOrderItem(goodsItem);
                $scope.current.basket.info.itemsCount += goodsItem.count;
                $scope.current.basket.info.fullCost += goodsItem.cost * goodsItem.count;
                $localStorage.cart = $scope.current.basket;
            };

            $scope.goods.removeFromOrder = function (goodsItem) {
                if ($scope.current.basket.items[goodsItem.id]) {
                    $scope.current.basket.info.itemsCount -= $scope.current.basket.items[goodsItem.id].quantity;
                    $scope.current.basket.info.fullCost -= goodsItem.cost * $scope.current.basket.items[goodsItem.id].quantity;
                    delete $scope.current.basket.items[goodsItem.id];
                    goodsItem.count = 1;
                }
                $localStorage.cart = $scope.current.basket;
            };

            $scope.goods.less = function (goodsItem) {
                if (goodsItem.count > 1) {
                    goodsItem.count--;

                    //  If it's already in cart, we also change count in it
                    if ($scope.current.basket.items[goodsItem.id]) {
                        $scope.current.basket.items[goodsItem.id].quantity--;
                        $scope.current.basket.info.itemsCount--;
                        $scope.current.basket.info.fullCost -= goodsItem.cost;
                        $localStorage.cart = $scope.current.basket;
                    }
                }
            };

            $scope.goods.more = function (goodsItem) {
                console.log(goodsItem.remain);
                if (goodsItem.count < goodsItem.remain) {
                    goodsItem.count++;

                    //  If it's already in cart, we also change count in it
                    if ($scope.current.basket.items[goodsItem.id]) {
                        $scope.current.basket.items[goodsItem.id].quantity++;
                        $scope.current.basket.info.itemsCount++;
                        $scope.current.basket.info.fullCost += goodsItem.cost;
                        $localStorage.cart = $scope.current.basket;
                    }
                }
            };

            $scope.goods.getGoodsItemClass = function (goodsItem) {

                if(!goodsItem){
                    return;
                }
                return $scope.current.basket.items[goodsItem.id] ? 'in-cart' : '';
            };

            $scope.init = function () {
                if ($localStorage.cart && $scope.current.isLogged) {
                    $scope.current.basket = $localStorage.cart;
                }
                goodsListService.setCart($scope.current.basket);
                $scope.pagination = paginationService.getPagination(goodsListService.getGoodsList);
                $scope.pagination.getPage(1);
                $scope.pagination.canChangeLimit = false;
            };

            $scope.init();
        }]);
