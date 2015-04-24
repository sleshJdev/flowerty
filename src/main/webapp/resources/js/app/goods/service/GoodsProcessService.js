'use strict';

angular.module("flowertyApplication.goodsModule").service("goodsProcessService", ["$http", function($http) {
    this.addGoods = function(notification, goods, picture, login){
        console.log("add new goods: " + JSON.stringify(goods) + " picture: " + picture.name);//LOG
        var formData = new FormData();
        formData.append("goods", angular.toJson(goods));
        formData.append("picture", picture);
        formData.append("login", login);

        $http.post("goods/add", formData,{
            headers: {'Content-Type': undefined },
            transformRequest: angular.identity
        }).success(function(data, status, headers, config) {
            console.log("goods added success");//LOG
            notification.message = "Goods added success!";
            notification.type = "success";
        }).error(function(data, status, headers, config) {
            console.log("goods added error: " + JSON.stringify(data));//LOG
            notification.message = "Error occured during  creating goods.";
            notification.type = "danger";
        });
    }
}]);