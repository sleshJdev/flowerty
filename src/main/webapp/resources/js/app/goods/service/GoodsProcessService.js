'use strict';

angular.module("flowertyApplication.goodsModule").service("goodsProcessService", ["$http",
    function($http) {
        this.addGoods = function (goods, picture, login, successCallback, errorCallback) {
            console.log("add new goods: " + JSON.stringify(goods) + " picture: " + picture.name);//LOG
            var formData = new FormData();
            formData.append("goods", angular.toJson(goods));
            formData.append("picture", picture);
            formData.append("login", login);

            $http.post("goods/add", formData, {
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
            })
                .success(successCallback)
                .error(errorCallback);
        }
    }]);