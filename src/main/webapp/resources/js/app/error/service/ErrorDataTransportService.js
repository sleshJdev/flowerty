'use strict';

/**
 * Created by Rostislav on 28-Apr-15.
 */

angular.module("flowertyApplication.errorModule").service('errorDataTransportService', function () {

    var errorBundle = {};

    return {
        getErrorBundle: function () {
            return errorBundle;
        },
        setErrorBundle: function (bundle) {
            errorBundle = bundle;
        }
    };
});