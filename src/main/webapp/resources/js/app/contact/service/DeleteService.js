'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * remove item from collection
 */
angular.module("flowertyApplication.contactModule")

.service("deleteService", ["$http", function($http) {
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
	
	this.deleteContact = function (list, successCallback, errorCallback) {
	    $http({
	        method: "post",
	        url: "contact/remove",
	        data: angular.toJson(list),
	        headers: {
	            "Content-Type": "application/json",
	            "Accept": "text/plain"
	        }
	    })
	    .success(successCallback)
	    .error(errorCallback);
    };
}]);
