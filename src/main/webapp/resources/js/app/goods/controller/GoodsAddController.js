'use strict';

angular.module("flowertyApplication.goodsModule").controller("GoodsAddController", ["$scope", "$http", "$location", "goodsProcessService", "notificationService",
    function($scope, $http, $location, goodsProcessService, notificationService) {
        $scope.bundle = {
            goods: {
//				cost:
                flower: {},
                company: {},
                image: ""
//				remain:
            },
            actions: [],
            picture: {}
        };

        $scope.notification = {
            status: "hide",
            message: "",
            type: ""
        };

        $scope.bundle.actions.add = function(){
        	$scope.bundle.goods.image = $scope.bundle.picture.name;
            goodsProcessService.addGoods($scope.bundle.goods, $scope.bundle.picture, $scope.$parent.current.user.name,
                function (data) {
                    console.log("goods added success");
                    notificationService.notify("success", "Goods added successfully!");
                },
                function (data) {
                    console.log("goods added error: " + JSON.stringify(data));
                    notificationService.notify("danger", "Error occured during creating goods.");
                }
            );
        };

        $scope.$on("picturePicked", function(event, args){
            console.log("picture picked event: " + args.picture.name);//LOG
            $scope.bundle.picture = args.picture;
        });
    }]);