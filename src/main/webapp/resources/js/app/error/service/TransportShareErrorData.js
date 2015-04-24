'use strict';

//use for pass value between error handler and controllers.
//TODO:maybe unnecessary...
angular.module("flowertyApplication.errorModule")

    .service("transportShareErrorData", function () {

        var errorBundle = {
            str : "error!"
        };

        return {
            getErrorBundle: function () {
                return errorBundle;
            },
            setErrorBundle: function (bundle) {
                errorBundle = bundle;
            }
        };
    });
