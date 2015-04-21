'use strict';

/**
 * Created by Катерина on 17.04.2015.
 */

var goodsModule = angular.module("flowertyApplication.goodsModule", ['ngRoute']);

goodsModule.constant("GOODS_CONSTANTS", (function(){
	var GOODS_MODULE_PATH = "resources/js/app/goods/";
		
	return {
		GOODS: 		GOODS_MODULE_PATH + "partial/goods-list.html",
		ADD_GOODS:	GOODS_MODULE_PATH + "partial/goods-form.html"
	}
})());

goodsModule.config(["$routeProvider", "GOODS_CONSTANTS", 
                    function($routeProvider, GOODS_CONSTANTS) {
    $routeProvider
        .when("/goods", {
            templateUrl: GOODS_CONSTANTS.GOODS,
            controller: "GoodsListController"
        })
        .when("/goods-add",{
        	templateUrl: GOODS_CONSTANTS.ADD_GOODS,
        	controller: "GoodsAddController"
        });
}]);

goodsModule.controller("GoodsListController", ['$scope', '$http', '$location', '$filter', function($scope, $http, $location, $filter) {

    $scope.basket = [];
    $scope.goods = {};
    $scope.goods.goodsArray = [
        [
            {
                imageName : 'flowers-iris.jpg',
                flower : { name : 'Iris' },
                cost : '13 $',
                //  This is the count of items you want to order
                //  Default is 1
                count : 1,
                ordered : false
            },
            {
                imageName : 'orchid_rose.jpg',
                flower : { name : 'Buquet orchid+rose' },
                cost : '50 $',
                //  This is the count of items you want to order
                //  Default is 1
                count : 1,
                ordered : false
            },
            {
                imageName : 'bush-rose.jpg',
                flower : { name : 'Bush rose' },
                cost : '17 $',
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
                cost : '20 $',
                //  This is the count of items you want to order
                //  Default is 1
                count : 1,
                ordered : false
            },
            {
                imageName : 'coral-pion.jpg',
                flower : { name : 'Coral pion' },
                cost : '20 $',
                //  This is the count of items you want to order
                //  Default is 1
                count : 1,
                ordered : false
            }
        ]
    ];
    $scope.goods.makeOrder = function(goodsItem){
        goodsItem.ordered = true;
        $scope.basket.push(goodsItem);
    };

    $scope.goods.less = function(goodsItem){
        if(goodsItem.count > 1) {
            goodsItem.count--;
        }

    };

    $scope.goods.more = function(goodsItem){
        goodsItem.count++;
    };


}]);

goodsModule.directive("flowertyPicturePick", function() {
	return {
		scope: true,
		restrict: "A",
		link: function(scope, element, attributes){
			element.bind("change", function(event){
				var files = event.target.files;
				for(var i = 0; i < files.length; ++i){
					scope.$emit("picturePicked", { picture: files[i] });
				};
			});
		}
	};
});

goodsModule.service("goodsProcessService", ["$http", function($http) {
	this.addGoods = function(goods, picture){
		console.log("add new goods: " + goods + " picture: " + picture.name);//LOG
		var formData = new FormData();
		formData.append("goods", angular.toJson(goods));
		formData.append("picture", picture);
		
		$http.post("goods/add", formData,{
			headers: {'Content-Type': undefined },
			transformRequest: angular.identity
		}).success(function(data, status, headers, config) {
			console.log("goods added success");//LOG
		}).error(function(data, status, headers, config) {
			console.log("goods added error: " + JSON.stringify(data));//LOG
		});
	}
}]);

goodsModule.controller("GoodsAddController", ["$scope", "$http", "$location", "goodsProcessService", 
                                              function($scope, $http, $location, goodsProcessService) {
	$scope.bundle = {
			goods: {
				cost: 0,
				flower: {},
				company: {},
				remain: 0
			},
			actions: [],
			picture: {}
	};
	$scope.bundle.actions.add = function(){
		goodsProcessService.addGoods($scope.bundle.goods, $scope.bundle.picture)
	}
	
	$scope.$on("picturePicked", function(event, args){
		console.log("picture picked event: " + args.picture.name);//LOG
		$scope.picture = args.picture;
	});
}]);