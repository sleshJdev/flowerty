'use strict';
/**
 * Created by Катерина on 11.05.2015.
 *
 * Service for notifying
 */

angular.module("flowertyApplication").service('notificationService', [
    function () {

        var self = this;

        var notification = {
            status: "hide",
            message: "",
            type: ""
        };

        self.getNotificationBundle = function() {
            return notification;
        };

        self.notify = function (type, message) {
            notification.type = type;
            notification.message = message;
            notification.status = "show";
        };

    }]);