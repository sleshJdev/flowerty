'use strict';
/**
 * Created by Катерина on 20.04.2015.
 */

angular.module("flowertyApplication.goodsModule").controller("GoodsListController", ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.goods = {
        pages : [],
        pagesCount : 1,
        currentPage : 1,
        goodsArray : []
    };

/*
    $scope.goods.goodsArray = [
        [
            {
                imageName : 'flowers-iris.jpg',
                flower : { name : 'Iris' },
                cost : 13,
                //  This is the count of items you want to order
                //  Default is 1
                quantity : 1,
                ordered : false
            },
            {
                imageName : 'orchid_rose.jpg',
                flower : { name : 'Buquet orchid+rose' },
                cost : 50,
                //  This is the count of items you want to order
                //  Default is 1
                quantity : 1,
                ordered : false
            },
            {
                imageName : 'bush-rose.jpg',
                flower : { name : 'Bush rose' },
                cost : 17,
                //  This is the count of items you want to order
                //  Default is 1
                count : 1,
                ordered : false
            }
        ],
        [
            {
                imageName : 'violet-pion.jpg',
                flower : { name : 'Pion' },
                cost : 20,
                //  This is the count of items you want to order
                //  Default is 1
                quantity : 1,
                ordered : false
            },
            {
                imageName : 'coral-pion.jpg',
                flower : { name : 'Coral pion' },
                cost : 20,
                //  This is the count of items you want to order
                //  Default is 1
                quantity : 1,
                ordered : false
            }
        ]
    ];
*/
    $scope.goods.initGoodsArray = function(goodsArray){
        var rows = Math.ceil(goodsArray.length / 3);
        var cols = 3;
        var resultMatrix = [];
        var i, j, goodsArrayInd = 0;
        for(i = 0; i < rows && goodsArrayInd < goodsArray.length; i++){
            resultMatrix.push([]);
            for(j = 0; j < cols && goodsArrayInd < goodsArray.length; j++, goodsArrayInd++){
                goodsArray[goodsArrayInd].count = 1;
                resultMatrix[i].push(goodsArray[goodsArrayInd]);
            }
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

    $scope.goods.makeOrder = function(goodsItem){
        goodsItem.ordered = true;
        $scope.current.basket.items.push(goodsItem);
        $scope.current.basket.info.itemsCount += goodsItem.count;
        $scope.current.basket.info.fullCost += goodsItem.cost * goodsItem.count;
    };

    $scope.goods.removeFromOrder = function(goodsItem) {
        var index = $scope.current.basket.items.indexOf(goodsItem);
        if (index !== -1) {
            $scope.current.basket.items.splice(index, 1);
            goodsItem.ordered = false;
            $scope.current.basket.info.itemsCount -= goodsItem.count;
            $scope.current.basket.info.fullCost -= goodsItem.cost * goodsItem.count;
            goodsItem.count = 1;
        }
    };

    $scope.goods.less = function(goodsItem){
        if(goodsItem.count > 1) {
            goodsItem.count--;
            var index = $scope.current.basket.items.indexOf(goodsItem);
            if (index !== -1) {
                $scope.current.basket.info.itemsCount--;
                $scope.current.basket.info.fullCost -= goodsItem.cost;
            }
        }

    };

    $scope.goods.more = function(goodsItem){
        goodsItem.count++;
        var index = $scope.current.basket.items.indexOf(goodsItem);
        if (index !== -1) {
            $scope.current.basket.info.itemsCount++;
            $scope.current.basket.info.fullCost += goodsItem.cost;
        }
    };

    $scope.init = function(){
        $scope.goods.getPage(1);
        $scope.pagination.getNextPage = $scope.goods.getNextPage;
        $scope.pagination.getPreviousPage = $scope.goods.getPreviousPage;
        $scope.pagination.getPage = $scope.goods.getPage;
        $scope.pagination.pageClass = $scope.goods.pageClass;
        $scope.pagination.getPagesCount = $scope.goods.getPagesCount;
    };

    $scope.init();
}]);
