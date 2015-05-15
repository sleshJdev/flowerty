/**
 * @author Eugene Putsykovich(slesh) May 14, 2015
 *
 *	validate the date. it is more or less current.
 */

angular.module("flowertyApplication.utilModule")

.constant("VALIDATE_DATE", (function(){
	var format = "YYYY-MM-DD";
	return{
		validate : function(dateString, isCheckOnPast){
			var now = moment().format(format);
	        var dateToCheck = moment(dateString).format(format);
	        
	        return isCheckOnPast ? moment(dateToCheck).isBefore(now) : moment(dateToCheck).isAfter(now);
		}
	};
})())

.directive("flowertyValidatePastDate", ["VALIDATE_DATE", function(VALIDATE_DATE) {
	return {
        require: 'ngModel',
        link: function (scope, element, attributes, ngModelCtrl) {
        	scope.$watch(attributes.ngModel, function(newValue, oldValue, scope){
        		console.log("new value: " + newValue + ", old value: " + oldValue);
        		ngModelCtrl.$setValidity("isPastDate", VALIDATE_DATE.validate(newValue, true));
        	});
        	
        	ngModelCtrl.$parsers.unshift(function(viewValue){
        		console.log("parsers");
        		ngModelCtrl.$setValidity("isPastDate", VALIDATE_DATE.validate(viewValue, true));
        		
    			return viewValue;
        	});
        	
        	ngModelCtrl.$formatters.unshift(function(modelValue){
        		console.log("formatters");
        		ngModelCtrl.$setValidity("isPastDate", VALIDATE_DATE.validate(modelValue, true));
        		
    			return modelValue;
        	});
        }
    };
}])

.directive('flowertyValidateFutureDate', ["VALIDATE_DATE", function (VALIDATE_DATE) {
    return {
        require: 'ngModel',
        link: function (scope, element, attributes, ngModelCtrl) {
        	ngModelCtrl.$parsers.unshift(function(viewValue){
        		ngModelCtrl.$setValidity("isFutureDate", VALIDATE_DATE.validate(viewValue, false));
        		
    			return viewValue;
        	});
        }
    };
}]);

