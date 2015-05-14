'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * for pass parameter for search from ContactListController to
 * SendEmailController. he will be pass emails of contacts.
 */
angular.module("flowertyApplication.contactModule").service("emailService", ["$http", "notificationService",
    function($http, notificationService) {
        function getTemplates(successCallback) {
            $http({
                method: "get",
                url: "email/templates"
            })
                .success(successCallback)
                .error(function (data, status, headers, config) {
                    console.log("error occured during fetch email templates: " + JSON.stringify(data));
                    notificationService.notify("danger", "Please, select phones to be deleted!");
                });
        }

        function send(email, files, template, successCallback, errorCallback) {
            var formData = new FormData();
            formData.append("email", angular.toJson(email));
            formData.append("template", angular.toJson(template));
            for (var i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
            }

            $http.post("email/send", formData, {
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
            })
                .success(successCallback)
                .error(errorCallback);
        }

        var value = "";
        return {
            getValue: function () {
                return value;
            },
            setValue: function (newValue) {
                value = newValue;
            },
            getTemplates: getTemplates,
            send: send
        };
    }]);