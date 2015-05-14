/**
 * @author Eugene Putsykovich(slesh) May 14, 2015
 *
 *	validate the date. it is more or less current.
 */

angular.module("flowertyApplication.utilModule")

.directive("flowertyValidatePastDate", function() {
	return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
        	
            scope.$watch(function () {
                return ngModel.$modelValue;
            }, function () {
            	var now = Date.now();
                var dateToCheck = Date.parse(ngModel.$modelValue);
                var result = (dateToCheck < now);
                ngModel.$setValidity("isPastDate", result);
            });
            
            attrs.$observe("flowertyValidatePastDate", function () {
            	var now = Date.now();
                var dateToCheck = Date.parse(ngModel.$modelValue);
                var result = (dateToCheck > now);
                ngModel.$setValidity("isPastDate", result);
            });
        }
    };
})

.directive('flowertyValidateFutureDate', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
        	
            scope.$watch(function () {
                return ngModel.$modelValue;
            }, function () {
            	var now = Date.now();
                var dateToCheck = Date.parse(ngModel.$modelValue);
                var result = (dateToCheck > now);
                ngModel.$setValidity("isFutureDate", result);
            });
            
            attrs.$observe("flowertyValidateFutureDate", function () {
            	var now = Date.now();
                var dateToCheck = Date.parse(ngModel.$modelValue);
                var result = (dateToCheck > now);
                ngModel.$setValidity("isFutureDate", result);
            });
        }
    };
});

