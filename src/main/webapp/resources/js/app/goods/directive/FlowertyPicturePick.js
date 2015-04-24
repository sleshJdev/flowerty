'use strict';

angular.module("flowertyApplication.goodsModule").directive("flowertyPicturePick", function() {
    return {
        scope: true,
        restrict: "A",
        link: function(scope, element, attributes){
            element.bind("change", function(event){
                var files = event.target.files;
                for(var i = 0; i < files.length; ++i){
                    scope.$emit("picturePicked", { picture: files[i] });
                }
            });
        }
    };
});

