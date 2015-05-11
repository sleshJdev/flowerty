'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * remove item from collection
 */
angular.module("flowertyApplication.contactModule")

.service("deleteService", function() {
	/*
     * removes selected items from collection. does not request to the server
     */
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
	
	/*
     * remove specific contacts. does request to the server
     */
	this.deleteContact = function(deletableCollection, sourceCollection){
        console.log("delete contact");

        $http({
            method: "post",
            url: "contact/remove",
            data: deletableCollection,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain"
            }
        }).success(function(data, status, headers, config) {
        	deleteService.deleteIsChecked($scope.state.ischecked, $scope.contacts.list);
            console.log("contact delete successful");
        }).error(function(data, status, headers, config) {
        	console.log("contact delete error. details: " + JSON.stringify(data));
        });
    };
});
	
	