/**
 * @author Eugene Putsykovich(slesh) May 9, 2015
 *
 */

angular.module("flowertyApplication.contactModule")

.directive("flowertyValidate", ["$compile", "VALIDATE_MESSAGES", 
                            	function($compile, VALIDATE_MESSAGES) {
	var template = "<span class='glyphicon form-control-feedback' aria-hidden='true' data-ng-class='info.icon'></span>" +
				   "<small>{{info.message}}</small>";
	
	return {
		require: "ngModel",
		restrict: "A",
		scope:true,
		scope:{
			info: "=validateInfo"
		},

		link: function(scope, element, attributes, ngModelCtrl){
			
			var link = $compile(template);
            var content = link(scope);
			element.parent().prepend(content);
			element.parent().addClass("has-feedback");
			
			function setState(state, icon, message){
				scope.info.state = state,
				scope.info.icon = icon,
				scope.info.message = message;
			}
			
			function validate(currentValue){
				var message = "";
				if(ngModelCtrl.$error.minlength){
					message = VALIDATE_MESSAGES["minlength"](attributes.name, attributes.minlength);
				};
				if(ngModelCtrl.$error.maxlength){
					message = VALIDATE_MESSAGES["maxlength"](attributes.name, attributes.maxlength);
				};
				if(ngModelCtrl.$error.required){
					message = VALIDATE_MESSAGES["required"](attributes.name);
				};
				if(ngModelCtrl.$error.pattern){
					message = VALIDATE_MESSAGES["pattern"](attributes.name);
				}
				if(ngModelCtrl.$error.email){
					message = VALIDATE_MESSAGES["email"]();
				}
				
				var isInvalid = ngModelCtrl.$dirty && ngModelCtrl.$invalid;
				
				
				console.log("is invalid: " + isInvalid);
				console.log("message: " + message);
				console.log("pattern: " + ngModelCtrl.$error.pattern);
				
				
				if(isInvalid){
					setState("has-error", "glyphicon-remove", message)
				} else {
					setState("has-success", "glyphicon-ok", "")
				};
				
				if(!currentValue){
					setState("", "", "")
				}
				
				element.parent().removeClass("has-error");
				element.parent().removeClass("has-success");
				element.parent().addClass(scope.info.state);
			}
			
			ngModelCtrl.$parsers.unshift(function (viewValue) {
				validate(viewValue);
					
				return viewValue;
            });

            ngModelCtrl.$formatters.unshift(function (modelValue) {
            	validate(modelValue);
            	
            	return modelValue;
            });
		}
	};
}]);