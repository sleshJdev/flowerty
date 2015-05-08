'use strict';
/*
 * @author Eugene Putsykovich(slesh) May 8, 2015
 * 
 * remove item from collection
 */
angular.module("flowertyApplication.contactModule").service("deleteService",
		function() {
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
			}
		});