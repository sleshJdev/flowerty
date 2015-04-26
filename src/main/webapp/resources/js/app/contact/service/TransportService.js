'use strict';

/*
 * for pass parameter for search from ContactListController to SendEmailController.
 * he will be pass emails of contacts.
 */
angular.module("flowertyApplication.contactModule").service("transportService", function() {
    var value = "";
    return {
        getValue: function(){
            return value;
        },
        setValue: function(newValue){
            value = newValue;
        }
    };
})