'use strict';
/**
 * Created by Катерина on 17.05.2015.
 *
 * Service for adding goods to cart, removing from cart etc.
 */

angular.module("flowertyApplication.goodsModule").service("cartService", [ '$localStorage',
    function ($localStorage) {

        var self = this;

        var getOrderItem = function (goodsItem) {
            return {
                goods: goodsItem,
                quantity: goodsItem.count
            };
        };

        self.makeOrder = function (goodsItem, cart) {

            cart.items[goodsItem.id] = getOrderItem(goodsItem);
            cart.info.itemsCount += goodsItem.count;
            cart.info.fullCost += goodsItem.cost * goodsItem.count;
            $localStorage.cart = cart;
        };

        self.removeFromOrder = function (goodsItem, cart) {
            if (cart.items[goodsItem.id]) {
                cart.info.itemsCount -= cart.items[goodsItem.id].quantity;
                cart.info.fullCost -= goodsItem.cost * cart.items[goodsItem.id].quantity;
                delete cart.items[goodsItem.id];
                goodsItem.count = 1;
            }
            $localStorage.cart = cart;
        };

        self.less = function (goodsItem, cart) {
            if (goodsItem.count > 1) {
                goodsItem.count--;

                //  If it's already in cart, we also change count in it
                if (cart.items[goodsItem.id]) {
                    cart.items[goodsItem.id].quantity--;
                    cart.info.itemsCount--;
                    cart.info.fullCost -= goodsItem.cost;
                    $localStorage.cart = cart;
                }
            }
        };

        self.more = function (goodsItem, cart) {
            if (goodsItem.count < goodsItem.remain) {
                goodsItem.count++;

                //  If it's already in cart, we also change count in it
                if (cart.items[goodsItem.id]) {
                    cart.items[goodsItem.id].quantity++;
                    cart.info.itemsCount++;
                    cart.info.fullCost += goodsItem.cost;
                    $localStorage.cart = cart;
                }
            }
        };
    }]);