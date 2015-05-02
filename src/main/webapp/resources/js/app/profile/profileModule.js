'use strict';

/**
 * Created by Rostislav on 01-May-15.
 */

angular.module("flowertyApplication.profileModule", ["ngRoute"])

    .constant("PROFILE_MODULE_CONSTANTS", (function () {
        var PROFILE_MODULE_PATH = "resources/js/app/profile/";

        return {
            PROFILE: PROFILE_MODULE_PATH + "partial/profile-form.html",
            PHONES: PROFILE_MODULE_PATH + "partial/phone-list-form.html"
        }
    })())

    .config(["$routeProvider", "PROFILE_MODULE_CONSTANTS", function ($routeProvider, PROFILE_MODULE_CONSTANTS) {
        $routeProvider
            .when("/profile", {
                templateUrl: PROFILE_MODULE_CONSTANTS.PROFILE,
                controller: "ProfileController"
            });
    }]);