'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * remove item from collection
 */
angular.module("flowertyApplication.contactModule")

.service("deleteService", ["$http", "$location", "notificationService", 
                           function($http, $location, notificationService) {
	var me = this;
	
	this.deleteIsChecked = function(checker, collection) {
		var isBreak = true;
		do {
			isBreak = true;
			for (var i = 0; i < collection.length; ++i) {
				if (checker(collection[i])) {
					collection.splice(i, 1);
					isBreak = false;
				}
			}
		} while (!isBreak);
	};
	
	this.deleteContact = function (list) {
	    $http({
	        method: "post",
	        url: "contact/remove",
	        data: list
	    }).success(function (data) {
			 console.log("contact delete successful");
			 notificationService.notify("success", list.length + " contacts success removed!");
		 }).error(function (data) {
			 console.log("contact delete error. details: " + JSON.stringify(data));
		 });
    };
}]);
