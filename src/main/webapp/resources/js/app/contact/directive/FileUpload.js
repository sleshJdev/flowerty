'use strict';

angular.module("flowertyApplication.contactModule").directive('flowertyFileUpload', function () {
    return {
        scope: true,        //create a new scope
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
                var files = event.target.files;
                for (var i = 0; i < files.length; i++) {
                    scope.$emit("fileSelected", { file: files[i] });
                }
            });
        }
    };
});