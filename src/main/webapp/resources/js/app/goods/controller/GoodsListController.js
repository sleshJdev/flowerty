'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.goodsModule").controller("GoodsListController",
    ['$scope', '$http', '$location', '$filter', '$localStorage', 'GOODS_MODULE_CONSTANTS', 'goodsListService', 'paginationService', 'cartService',
        function($scope, $http, $location, $filter, $localStorage, GOODS_MODULE_CONSTANTS, goodsListService, paginationService, cartService) {

            $scope.goods = paginationService.getListBundle();

            $scope.goods.makeOrder = cartService.makeOrder;

            $scope.goods.removeFromOrder = cartService.removeFromOrder;

            $scope.goods.less = cartService.less;

            $scope.goods.more = cartService.more;

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
                $scope.pagination = paginationService.getPagination(goodsListService.getGoodsList, false);
                $scope.pagination.getPage(1);
                $scope.pagination.canChangeLimit = false;
            };

            $scope.init();
        }]);
