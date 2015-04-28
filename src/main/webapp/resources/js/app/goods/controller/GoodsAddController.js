'use strict';

angular.module("flowertyApplication.goodsModule").controller("GoodsAddController", ["$scope", "$http", "$location", "goodsProcessService",
    function($scope, $http, $location, goodsProcessService) {
        $scope.bundle = {
            goods: {
//				cost:
                flower: {},
                company: {}
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
            goodsProcessService.addGoods($scope.notification, $scope.bundle.goods, $scope.bundle.picture, $scope.$parent.current.user.name);
            $scope.notification.status = "show";
        }
        $scope.$on("picturePicked", function(event, args){
            console.log("picture picked event: " + args.picture.name);//LOG
            $scope.bundle.picture = args.picture;
        });
    }]);