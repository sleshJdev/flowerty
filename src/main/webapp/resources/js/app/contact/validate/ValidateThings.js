/**
 * @author Eugene Putsykovich(slesh) May 9, 2015
 *
 */

angular.module("flowertyApplication.contactModule")

.directive("nameValidate", function() {
	return {
		require: "ngModel",
		restrict: "A",
		link: function($scope, element, attributes){
			console.log("element: " + JSON.stringify(element));
			console.log("attribute: " + JSON.stringify(attribute));
			$scope.$watch(attributes.bundle, function(value) {
				console.log("value: " + value);
			});
		}
	};
});