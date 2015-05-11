'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * for pass parameter for search from ContactListController to
 * SendEmailController. he will be pass emails of contacts.
 */
angular.module("flowertyApplication.contactModule")

.service("emailService", ["$http", function($http) {
	var me = this;
	
	me.getTemplates = function(){
		$http({
			method: "get",
			url: "email/templates"
		}).success(function(data, status, headers, config) {
			return data;
		}).error(function(data, status, headers, config) {
			console.log("error occured during fetch email templates: " + JSON.stringify(data));//LOG
		});
	}
	
	var value = "";
    return {
        getValue: function(){
            return value;
        },
        setValue: function(newValue){
            value = newValue;
        }
    };
}]);