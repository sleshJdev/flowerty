'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * for pass parameter for search from ContactListController to
 * SendEmailController. he will be pass emails of contacts.
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
});